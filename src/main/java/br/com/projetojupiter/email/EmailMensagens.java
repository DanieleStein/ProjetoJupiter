package br.com.projetojupiter.email;

import br.com.projetojupiter.model.Usuario;

public class EmailMensagens {
	
	public static String createTitle(Usuario usuario) {
		return usuario.getNome() + " seu cadastro foi realizado com sucesso!";
	}
	
	public static String mensagemToNewUsuario(Usuario usuario) {
		return "Olá " + usuario.getNome()
		+ "! Seja muito bem vindo(a) ao nosso site. \n\n"
		+ "Aqui você terá acesso aos melhores conteúdos! \n\n"
		+ "Jupiter, a plataforma das plataformas!"; 
	}

}
