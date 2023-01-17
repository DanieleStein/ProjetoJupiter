package br.com.projetojupiter.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_conteudos")
class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotNull
    @Size(min = 5, max = 100)
    var titulo: String = ""

    @NotNull
    @Size(min = 10, max = 200)
    var descricao: String = ""

    @NotNull
    var url: String = ""

    var ordemConteudo: Long = 0

    @ManyToOne
    @JsonIgnoreProperties("conteudo")
    var curso: Curso? = null
}