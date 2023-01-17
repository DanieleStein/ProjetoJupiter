package br.com.projetojupiter.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_criador")
class Criador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotNull
    @Size(min = 3, max = 100)
    var nome: String = ""

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 100)
    var email: String = ""

    @NotNull
    @Size(min = 6, max = 100)
    var senha: String = ""

    @OneToMany(mappedBy = "criador", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("criador")
    var curso: MutableList<Curso>? = null
}