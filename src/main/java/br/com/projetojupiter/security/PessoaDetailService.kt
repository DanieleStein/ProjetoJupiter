package br.com.projetojupiter.security

import br.com.projetojupiter.repository.CriadorRepository
import br.com.projetojupiter.repository.UsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class PessoaDetailService(
    private val usuarioRepository: UsuarioRepository,
    private val criadorRepository: CriadorRepository
) : UserDetailsService {



    override fun loadUserByUsername(email: String): UserDetails {

        val usuario = usuarioRepository.findByEmail(email)
        val criador = criadorRepository.findByEmail(email)

        return if (criador.isPresent) {
            CriadorDetails(criador.get())
        } else if (usuario.isPresent) {
            UsuarioDetails(usuario.get())
        } else {
            throw ResponseStatusException(HttpStatus.FORBIDDEN)
        }

    }
}