package br.com.projetojupiter.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import java.util.Date
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_usuario")
class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotNull
    @Size(min=3, max=100)
    var nome: String = ""

    @NotNull
    @Size(min=3, max=100)
    @Column(unique=true)
    var email: String = ""

    @NotNull
    @Size(min=3, max=100)
    var senha: String = ""

    @NotNull
    @Size(min=3, max=100)
    @Column(unique=true)
    var cpf: String = ""

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    lateinit var dataNascimento: Date

    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("usuario")
    var cartao: MutableList<Cartao>? = null

    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("usuario")
    var pedido: MutableList<Pedido>? = null
}