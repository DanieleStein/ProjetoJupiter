package br.com.projetojupiter.email

import br.com.projetojupiter.model.Usuario
import org.springframework.stereotype.Service

//Classe resposnsável por gerenciar as mensagens que serão enviadas nos emails.
@Service
class EmailMensagens {

    fun createTitle(usuario: Usuario): String {
        return usuario.nome + ", seu cadastro foi realizado com sucesso!"
    }

    fun mensagemToNewUsuario(usuario: Usuario): String {
        return ("Olá " + usuario.nome
                + "! Seja muito bem vindo(a) ao nosso site. \n\n"
                + "Aqui você terá acesso aos melhores conteúdos! \n\n"
                + "Jupiter, a plataforma das plataformas!")
    }
}