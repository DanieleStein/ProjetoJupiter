package br.com.projetojupiter.repository

import br.com.projetojupiter.model.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {

    fun findAllByCursoContainingIgnoreCase(curso: String): MutableList<Curso>

    fun findAllByTituloContainingIgnoreCase(titulo: String): MutableList<Curso>
}