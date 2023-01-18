package br.com.projetojupiter.repository

import br.com.projetojupiter.model.Pedido
import br.com.projetojupiter.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PedidoRepository : JpaRepository<Pedido, Long> {

    fun findByUsuario(usuario: Usuario): Optional<Pedido>
}