package br.com.projetojupiter.controller;

import java.util.List;

import br.com.projetojupiter.service.CursoService;
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

import br.com.projetojupiter.model.Curso;
import br.com.projetojupiter.repository.CursoRepository;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	
	@Autowired
	private CursoRepository repository;


	@GetMapping()
	public ResponseEntity<List<Curso>> getAll() {
		return ResponseEntity.ok(cursoService.getAll());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> getById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/curso/{curso}")
	public ResponseEntity<List<Curso>> getByCurso(@PathVariable String curso) {
		return ResponseEntity.ok(repository.findAllByCursoContainingIgnoreCase(curso));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Curso>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Curso> post(@RequestBody Curso curso) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(curso));
	}
	
	@PutMapping
	public ResponseEntity<Curso> put(@RequestBody Curso curso) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(curso));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
