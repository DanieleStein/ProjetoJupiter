package br.com.projetojupiter.security;

import br.com.projetojupiter.model.Criador;
import br.com.projetojupiter.model.Usuario;
import br.com.projetojupiter.repository.CriadorRepository;
import br.com.projetojupiter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PessoaDetailService implements UserDetailsService  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CriadorRepository criadorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        Optional<Criador> criador = criadorRepository.findByEmail(email);

        if(criador.isPresent()) {
            return new CriadorDetails(criador.get());
        }
        else if (usuario.isPresent()){
            return new UsuarioDetails(usuario.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
