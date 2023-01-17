package br.com.projetojupiter.controller

import br.com.projetojupiter.model.Pedido
import br.com.projetojupiter.repository.PedidoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class PedidoController {

    @Autowired
    var repository: PedidoRepository? = null

    @GetMapping
    fun getAll(): ResponseEntity<List<Pedido?>>? {
        return ResponseEntity.ok(repository!!.findAll())
    }

    @PostMapping
    fun post(@RequestBody pedido: Pedido): ResponseEntity<Pedido>? {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(pedido))
    }

    @PutMapping("/id/{id}")
    fun put(@RequestBody pedido: Pedido): ResponseEntity<Pedido>? {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(pedido))
    }

    @DeleteMapping("/id/{id}")
    fun delete(@PathVariable id: Long) {
        repository!!.deleteById(id)
    }
}