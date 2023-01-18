package br.com.projetojupiter.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

//Classe para o usuario receber o email.
@Service
class SendEmailService {

    @Autowired
    var envioEmailDoJava: JavaMailSender? = null //Classe que tem toda a configuração para que o email seja enviado


    fun SendEmailService(javaMailSender: JavaMailSender) {
        envioEmailDoJava = javaMailSender
    }

    fun enviar(para: String, titulo: String, conteudo: String) {
        val mensagem = SimpleMailMessage() //Classe simpleMailMessage gerar os dados de envio do email.
        mensagem.setTo(para)
        mensagem.subject = titulo
        mensagem.text = conteudo
        envioEmailDoJava!!.send(mensagem)
    }
}