package br.com.projetojupiter.repository;

import br.com.projetojupiter.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetojupiter.model.Pedido;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    Optional<Pedido> findByUsuario (Usuario usuario);

}
