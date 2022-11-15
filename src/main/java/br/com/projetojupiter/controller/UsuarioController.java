package br.com.projetojupiter.controller;

import java.util.List;
import java.util.Optional;

import br.com.projetojupiter.model.UsuarioLogin;
import br.com.projetojupiter.request.EmailUsuarioRequest;
import br.com.projetojupiter.response.MensagemResponse;
import br.com.projetojupiter.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetojupiter.model.Usuario;
import br.com.projetojupiter.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository repository;

	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> authentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.logar(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/register")
	public ResponseEntity<Usuario> register(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));
	}

	@GetMapping("/ativo")
	public ResponseEntity<MensagemResponse> ativo(@RequestBody EmailUsuarioRequest usuario){
		String email = usuario.getEmail();
		return ResponseEntity.status(HttpStatus.OK)
				.body(usuarioService.ehUsuarioAtivo(email));
	}





	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(usuario));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(usuario));
	}



}
