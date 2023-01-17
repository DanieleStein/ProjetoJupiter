package br.com.projetojupiter.controller

import br.com.projetojupiter.model.Cartao
import br.com.projetojupiter.repository.CartaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cartao")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class CartaoController {

    @Autowired
    var repository: CartaoRepository? = null

    @GetMapping
    fun getAll(): ResponseEntity<MutableList<Cartao>> {
        return ResponseEntity.ok(repository!!.findAll())
    }

    @PostMapping
    fun post(@RequestBody cartao: Cartao): ResponseEntity<Cartao> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(cartao))
    }

    @PutMapping("/id/{id}")
    fun put(@RequestBody cartao: Cartao): ResponseEntity<Cartao>? {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(cartao))
    }

    @DeleteMapping("/id/{id}")
    fun delete(@PathVariable id: Long) {
        repository!!.deleteById(id)
    }
}