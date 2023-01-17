package br.com.projetojupiter.controller;

import java.util.List;

import br.com.projetojupiter.model.Pedido;
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

import br.com.projetojupiter.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Pedido> post(@RequestBody Pedido pedido) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(pedido));
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<Pedido> put(@RequestBody Pedido pedido) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(pedido));
	}
}
