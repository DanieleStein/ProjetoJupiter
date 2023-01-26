package br.com.projetojupiter.service

import br.com.projetojupiter.email.EmailMensagens
import br.com.projetojupiter.email.SendEmailService
import br.com.projetojupiter.model.Usuario
import br.com.projetojupiter.model.UsuarioLogin
import br.com.projetojupiter.repository.PedidoRepository
import br.com.projetojupiter.repository.UsuarioRepository
import br.com.projetojupiter.response.MensagemResponse
import org.apache.commons.codec.binary.Base64
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val pedidoRepository: PedidoRepository,
    private val sendEmailService: SendEmailService,
    private val emailMensagens: EmailMensagens
) {

    fun cadastrarUsuario(usuario: Usuario): Usuario? {
        val encoder = BCryptPasswordEncoder()
        val senhaEncoder = encoder.encode(usuario.senha)
        usuario.senha = senhaEncoder
        sendEmailService.enviar(
            usuario.email,
            emailMensagens.createTitle(usuario),
            emailMensagens.mensagemToNewUsuario(usuario)
        )
        return usuarioRepository.save(usuario)
    }

    fun logar(user: UsuarioLogin): UsuarioLogin? {
        val encoder = BCryptPasswordEncoder()
        val usuario = usuarioRepository.findByEmail(user.email)
        if (usuario.isPresent) {
            if (encoder.matches(user.senha, usuario.get().senha)) {
                val auth = user.email + ":" + user.senha
                val encodedAuth = Base64
                    .encodeBase64(auth.toByteArray(Charset.forName("US-ASCII")))
                val authHeader = "Basic " + String(encodedAuth)
                user.token = authHeader
                return user
            }
        }
        return null
    }

    fun ehUsuarioAtivo(email: String?): MensagemResponse? {
        val usuario = usuarioRepository.findByEmail(email)
        if (!usuario.isPresent) {
            return MensagemResponse("Usuário não encontrado")
        }
        val pedido = pedidoRepository.findByUsuario(usuario.get())
        if (pedido.isEmpty) {
            return MensagemResponse("Não existe pedido para este usuário")
        }
        val hoje = LocalDate.now()
        val dataInicial = pedido.get()
            .dataInicial
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val dataFinalPedido = dataInicial.plusMonths(pedido.get().periodoContratadoMeses.toLong())
        val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return if (hoje.isBefore(dataFinalPedido) || hoje.isEqual(dataFinalPedido)) {
            MensagemResponse(
                String.format(
                    "Ativo - Data Compra: %s - Data Final: %s - Hoje: %s - Periodo: %s meses",
                    dataInicial.format(dtf),
                    dataFinalPedido.format(dtf),
                    hoje.format(dtf),
                    pedido.get().periodoContratadoMeses
                )
            )
        } else MensagemResponse(
            String.format(
                "Vencido - Data Compra: %s - Data Final: %s - Hoje: %s - Periodo: %s meses",
                dataInicial.format(dtf),
                dataFinalPedido.format(dtf),
                hoje.format(dtf),
                pedido.get().periodoContratadoMeses
            )
        )
    }

}