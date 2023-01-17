package br.com.projetojupiter.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tb_pedido")
class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotNull
    var periodoContratadoMeses: Int = 0

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    var dataInicial: Date = Date()

    @NotNull
    var valor: Double = 0.0

    @ManyToOne
    @JsonIgnoreProperties("pedido")
    var usuario: Usuario? = null
}