package br.com.projetojupiter.controller

import br.com.projetojupiter.model.Curso
import br.com.projetojupiter.repository.CursoRepository
import br.com.projetojupiter.service.CursoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class CursoController {

    @Autowired
    var cursoService: CursoService? = null


    @Autowired
    var repository: CursoRepository? = null


    @GetMapping
    fun getAll(): ResponseEntity<MutableList<Curso>> {
        return ResponseEntity.ok(cursoService!!.all)
    }


    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Curso> {
        return repository!!.findById(id)
            .map { resp: Curso ->
                ResponseEntity.ok(
                    resp
                )
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/curso/{curso}")
    fun getByCurso(@PathVariable curso: String): ResponseEntity<MutableList<Curso>> {
        return ResponseEntity.ok(repository!!.findAllByCursoContainingIgnoreCase(curso))
    }

    @GetMapping("/titulo/{titulo}")
    fun getByTitulo(@PathVariable titulo: String): ResponseEntity<MutableList<Curso>> {
        return ResponseEntity.ok(repository!!.findAllByTituloContainingIgnoreCase(titulo))
    }

    @PostMapping
    fun post(@RequestBody curso: Curso): ResponseEntity<Curso> {
        return ResponseEntity.status(HttpStatus.OK).body(repository!!.save(curso))
    }

    @PutMapping("/id/{id}")
    fun put(@RequestBody curso: Curso): ResponseEntity<Curso> {
        return ResponseEntity.status(HttpStatus.OK).body(repository!!.save(curso))
    }

    @DeleteMapping("/id/{id}")
    fun delete(@PathVariable id: Long) {
        repository!!.deleteById(id)
    }
}