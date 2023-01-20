package br.com.projetojupiter.service

import br.com.projetojupiter.model.Criador
import br.com.projetojupiter.model.CriadorLogin
import br.com.projetojupiter.repository.CriadorRepository
import org.apache.commons.codec.binary.Base64
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.nio.charset.Charset

@Service
class CriadorService(
    private val repository: CriadorRepository
) {

    fun cadastrarCriador(criador: Criador): Criador? {

        val encoder = BCryptPasswordEncoder()
        val senhaEncoder = encoder.encode(criador.senha)

        criador.senha = senhaEncoder

        return repository.save(criador)
    }

    fun logarCriador(criad: CriadorLogin): CriadorLogin? {

        val encoder = BCryptPasswordEncoder()
        val criador = repository.findByEmail(criad.email)

        if (criador.isPresent) {
            if (encoder.matches(criad.senha, criador.get().senha)) {
                val auth = criad.email + ":" + criad.senha
                val encodedAuth = Base64.encodeBase64(auth.toByteArray(Charset.forName("US-ASCII")))
                val authHeader = "Basic " + String(encodedAuth)
                criad.token = authHeader
                return criad
            }
        }

        return null
    }

}