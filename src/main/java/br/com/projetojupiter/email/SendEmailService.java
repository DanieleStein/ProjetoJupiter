package br.com.projetojupiter.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendEmailService {
	
	@Autowired
	private final JavaMailSender envioEmailDoJava;
	
	public SendEmailService(final JavaMailSender javaMailSender) {
		this.envioEmailDoJava = javaMailSender;
	}
	
	public void enviar(String para, String titulo, String conteudo) {
		
		var mensagem = new SimpleMailMessage();
		mensagem.setTo(para);
		mensagem.setSubject(titulo);
		mensagem.setText(conteudo);
		envioEmailDoJava.send(mensagem);
	}

}
