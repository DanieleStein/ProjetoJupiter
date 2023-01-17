package br.com.projetojupiter.controller;

import java.util.Optional;

import br.com.projetojupiter.model.Criador;
import br.com.projetojupiter.model.CriadorLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetojupiter.repository.CriadorRepository;
import br.com.projetojupiter.service.CriadorService;

@RestController
@RequestMapping("/criador")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class CriadorController {
	
	@Autowired
	private CriadorService criadorService;
	
	@Autowired
	private CriadorRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<CriadorLogin> authentication(@RequestBody Optional<CriadorLogin> criad) {
		return criadorService.logarCriador(criad)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/register")
	public ResponseEntity<Criador> register(@RequestBody Criador criador) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(criadorService.cadastrarCriador(criador));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Criador> getById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Criador> put(@RequestBody Criador criador) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(criador));
	}
	
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
