package br.com.projetojupiter.repository;

import java.util.List;

import br.com.projetojupiter.model.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoRepository extends JpaRepository <Conteudo, Long> {
	
	public List<Conteudo> findAllByTituloContainingIgnoreCase(String titulo);

}
