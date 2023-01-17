package br.com.projetojupiter.controller;

import java.util.List;

import br.com.projetojupiter.model.Conteudo;
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

import br.com.projetojupiter.repository.ConteudoRepository;

@RestController
@RequestMapping("/conteudos")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class ConteudoController {
	
	@Autowired
	private ConteudoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Conteudo>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Conteudo> getById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Conteudo>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Conteudo> post(@RequestBody Conteudo conteudo) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(conteudo));
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Conteudo> put(@RequestBody Conteudo conteudo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(conteudo));
	}
	
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
