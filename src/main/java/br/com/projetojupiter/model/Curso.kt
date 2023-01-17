package br.com.projetojupiter.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "tb_cursos")
class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotNull
    @Size(min = 5, max = 100)
    var curso: String = ""

    @NotNull
    @Size(min = 5, max = 100)
    var titulo: String = ""

    @NotNull
    @Size(min = 10, max = 200)
    var descricao: String = ""

    @OneToMany(mappedBy = "curso", cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("curso")
    var conteudo: MutableList<Conteudo>? = null

    @ManyToOne
    @JsonIgnoreProperties("curso")
    var criador: Criador? = null


}