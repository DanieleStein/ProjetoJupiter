package br.com.projetojupiter.repository;

import br.com.projetojupiter.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{
	
	

}
