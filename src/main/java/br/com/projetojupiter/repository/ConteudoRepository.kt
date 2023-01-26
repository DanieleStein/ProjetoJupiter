package br.com.projetojupiter.repository

import br.com.projetojupiter.model.Conteudo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConteudoRepository : JpaRepository<Conteudo, Long> {
    fun findAllByTituloContainingIgnoreCase(titulo: String): MutableList<Conteudo>
}

