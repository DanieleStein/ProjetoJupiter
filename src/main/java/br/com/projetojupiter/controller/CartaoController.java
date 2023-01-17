package br.com.projetojupiter.controller;

import java.util.List;

import br.com.projetojupiter.model.Cartao;
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

import br.com.projetojupiter.repository.CartaoRepository;

@RestController
@RequestMapping("/cartao")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class CartaoController {
	
	@Autowired
	private CartaoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cartao>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Cartao> post(@RequestBody Cartao cartao) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(cartao));
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Cartao> put(@RequestBody Cartao cartao) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(cartao));
	}
}
