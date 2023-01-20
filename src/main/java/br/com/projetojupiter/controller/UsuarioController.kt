package br.com.projetojupiter.controller

import br.com.projetojupiter.model.Usuario
import br.com.projetojupiter.model.UsuarioLogin
import br.com.projetojupiter.repository.UsuarioRepository
import br.com.projetojupiter.request.EmailUsuarioRequest
import br.com.projetojupiter.response.MensagemResponse
import br.com.projetojupiter.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class UsuarioController(
    private var usuarioService: UsuarioService
) {

    @Autowired
    var repository: UsuarioRepository? = null

    @PostMapping("/login")
    fun authentication(@RequestBody user: UsuarioLogin): ResponseEntity<UsuarioLogin?> {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body( usuarioService.logar(user) )
    }

    @PostMapping("/register")
    fun register(@RequestBody usuario: Usuario?): ResponseEntity<Usuario>? {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(usuario?.let { usuarioService!!.cadastrarUsuario(it) })
    }

    @GetMapping("/ativo")
    fun ativo(@RequestBody usuario: EmailUsuarioRequest): ResponseEntity<MensagemResponse>? {
        val email = usuario.email
        return ResponseEntity.status(HttpStatus.OK)
            .body(usuarioService!!.ehUsuarioAtivo(email))
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Usuario?>>? {
        return ResponseEntity.ok(repository!!.findAll())
    }

    @PutMapping("/id/{id}")
    fun put(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(repository!!.save(usuario))
    }

    @DeleteMapping("/id/{id}")
    fun delete(@PathVariable id: Long) {
        repository!!.deleteById(id)
    }
}