package br.com.projetojupiter.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_cartao")
class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotNull
    @Size(min = 3, max = 100)
    var nomeCartao: String = ""

    @NotNull
    @Column(unique = true)
    @Size(min = 8, max = 20)
    var numeroCartao: String = ""

    @NotNull
    @Size(min = 3, max = 6)
    var codSeguranca: String = ""

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    var dataValidade: Date? = null

    @ManyToOne
    @JsonIgnoreProperties("cartao")
    var usuario: Usuario? = null
}