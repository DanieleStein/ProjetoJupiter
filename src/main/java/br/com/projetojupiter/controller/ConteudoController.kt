package br.com.projetojupiter.controller

import br.com.projetojupiter.model.Conteudo
import br.com.projetojupiter.repository.ConteudoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/conteudos")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ConteudoController {

    @Autowired
    var repository: ConteudoRepository? = null

    @GetMapping
    fun getAll(): ResponseEntity<MutableList<Conteudo?>> {
        return ResponseEntity.ok(repository!!.findAll())
    }

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Conteudo> {
        return repository!!.findById(id)
            .map { resp: Conteudo ->
                ResponseEntity.ok(
                    resp
                )
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/titulo/{titulo}")
    fun getByTitulo(@PathVariable titulo: String): ResponseEntity<MutableList<Conteudo>> {
        return ResponseEntity.ok(repository!!.findAllByTituloContainingIgnoreCase(titulo))
    }

    @PostMapping
    fun post(@RequestBody conteudo: Conteudo): ResponseEntity<Conteudo> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(conteudo))
    }

    @PutMapping("/id/{id}")
    fun put(@RequestBody conteudo: Conteudo): ResponseEntity<Conteudo> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(repository!!.save(conteudo))
    }

    @DeleteMapping("/id/{id}")
    fun delete(@PathVariable id: Long) {
        repository!!.deleteById(id)
    }
}