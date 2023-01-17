package br.com.projetojupiter.repository;

import java.util.Optional;

import br.com.projetojupiter.model.Criador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriadorRepository extends JpaRepository<Criador, Long> {
	
	 Optional<Criador> findByEmail(String email);
}
