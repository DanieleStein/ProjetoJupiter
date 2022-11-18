package br.com.projetojupiter.service;


import br.com.projetojupiter.email.EmailMensagens;
import br.com.projetojupiter.email.SendEmailService;
import br.com.projetojupiter.model.Pedido;
import br.com.projetojupiter.model.Usuario;
import br.com.projetojupiter.model.UsuarioLogin;
import br.com.projetojupiter.repository.PedidoRepository;
import br.com.projetojupiter.repository.UsuarioRepository;
import br.com.projetojupiter.response.MensagemResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private SendEmailService sendEmailService;

    public Usuario cadastrarUsuario(Usuario usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncoder = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncoder);
        this.sendEmailService.enviar(usuario.getEmail(), EmailMensagens.createTitle(usuario), EmailMensagens.mensagemToNewUsuario(usuario));
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.get().getEmail());

        if (usuario.isPresent()){
            if (encoder.matches(user.get().getSenha(),usuario.get().getSenha())){
                String auth = user.get().getEmail() + ":" + user.get().getSenha();

                byte[] encodedAuth = Base64
                        .encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

                String authHeader = "Basic " + new String(encodedAuth);

                user.get().setToken(authHeader);

                return user;

            }
        }

        return null;

    }

    public MensagemResponse ehUsuarioAtivo(String email){

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (!usuario.isPresent()){
           return new MensagemResponse("Usuário não encontrado");
        }

        Optional<Pedido> pedido = pedidoRepository.findByUsuario(usuario.get());
        if (pedido.isEmpty()){
            return new MensagemResponse("Não existe pedido para este usuário");
        }

        LocalDate hoje = LocalDate.now();

        LocalDate dataInicial = pedido.get()
                .getDataInicial()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate dataFinalPedido = dataInicial.plusMonths(pedido.get().getPeriodoContratadoMeses());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (hoje.isBefore(dataFinalPedido) || hoje.isEqual(dataFinalPedido)){
            return new MensagemResponse(
                    String.format("Ativo - Data Compra: %s - Data Final: %s - Hoje: %s - Periodo: %s meses",
                            dataInicial.format(dtf),
                            dataFinalPedido.format(dtf),
                            hoje.format(dtf),
                            pedido.get().getPeriodoContratadoMeses()
                    )
            );
        }

        return new MensagemResponse(
                String.format("Vencido - Data Compra: %s - Data Final: %s - Hoje: %s - Periodo: %s meses",
                                dataInicial.format(dtf),
                                dataFinalPedido.format(dtf),
                                hoje.format(dtf),
                                pedido.get().getPeriodoContratadoMeses()
                )
        );
    }

}
