package br.com.projetojupiter.repository;

import java.util.List;

import br.com.projetojupiter.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository <Curso, Long> {
	
	public List<Curso> findAllByCursoContainingIgnoreCase(String curso);
	
	public List<Curso> findAllByTituloContainingIgnoreCase(String titulo);
}
