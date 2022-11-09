package br.com.projetojupiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetojupiter.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}