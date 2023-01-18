package br.com.projetojupiter.repository

import br.com.projetojupiter.model.Criador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CriadorRepository : JpaRepository<Criador, Long> {

    fun findByEmail(email: String): Optional<Criador>
}