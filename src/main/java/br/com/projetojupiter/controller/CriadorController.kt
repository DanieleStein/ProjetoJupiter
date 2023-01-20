package br.com.projetojupiter.controller

import br.com.projetojupiter.model.Criador
import br.com.projetojupiter.model.CriadorLogin
import br.com.projetojupiter.repository.CriadorRepository
import br.com.projetojupiter.service.CriadorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/criador")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class CriadorController(
    var criadorService: CriadorService,
    var repository: CriadorRepository
) {

    @PostMapping("/login")
    fun authentication(@RequestBody criad: CriadorLogin): CriadorLogin? {
        return criadorService.logarCriador(criad)
    }

    @PostMapping("/register")
    fun register(@RequestBody criador: Criador): ResponseEntity<Criador> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(criadorService.cadastrarCriador(criador))
    }

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Criador> {
        return repository!!.findById(id)
            .map { resp: Criador ->
                ResponseEntity.ok(
                    resp
                )
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/id/{id}")
    fun put(@RequestBody criador: Criador): ResponseEntity<Criador> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(criador))
    }

    @DeleteMapping("/id/{id}")
    fun delete(@PathVariable id: Long) {
        repository!!.deleteById(id)
    }
}