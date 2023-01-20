package br.com.projetojupiter.service

import br.com.projetojupiter.model.Curso
import br.com.projetojupiter.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val cursoRepository: CursoRepository
) {
    fun getAll(): MutableList<Curso>? {
        return cursoRepository.findAll()
    }
}