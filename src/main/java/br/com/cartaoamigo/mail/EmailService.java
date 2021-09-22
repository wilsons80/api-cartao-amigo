package br.com.cartaoamigo.mail;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.to.EnvioEmailTO;



@Component
public class EmailService {
	
	
	@Value("${spring.profiles.active}")
	private String ambiente;
		
	@Autowired
	private MailSender mailSender;
	
	@Autowired
    private JavaMailSender javaMailSender;
	

	
	private boolean isAmbienteDesenvolvimento() {
		return !isAmbienteProducao();
	}
	private boolean isAmbienteProducao() {
		return ambiente != null && "prod".toUpperCase().equals(ambiente.toUpperCase());
	}
	
	public void sendConfirmacaoEmailTexto(EnvioEmailTO envioEmailTO) {
		sendEmailTexto(envioEmailTO);
	}

	@Async
	private void sendEmailTexto(EnvioEmailTO envioEmailTO) {
		SimpleMailMessage sm = new SimpleMailMessage();
		
		if(isAmbienteDesenvolvimento()) {
			sm.setTo("desenvolvimento@rafahsolucoes.com.br");
		} else {
			//Destinatário do email
			sm.setTo(envioEmailTO.getPessoaFisica().getEmail());
		}
		
		//Remetente do email
		sm.setFrom("faleconosco@cartaoamigo.com.br");
		
		//Assunto
		sm.setSubject("Cartão Amigo - Redefinição de senha");
		
		//Data do envio do email
		sm.setSentDate(new Date(System.currentTimeMillis()));
		
		//Corpo do email text/plain
		sm.setText("Mensagem de teste");
		
		
		//Envia o e-mail
		mailSender.send(sm);
	}
	
	
	public void sendConfirmacaoEmailHtml(EnvioEmailTO envioEmailTO, String conteudoHtml) throws MessagingException {
		sendEmailHtml(envioEmailTO, conteudoHtml);
	}

	
	@Async
	private void sendEmailHtml(EnvioEmailTO envioEmailTO, String conteudoHtml) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
		
		//Destinatário do email
		if(isAmbienteDesenvolvimento()) {
			helper.setTo("wilson.souza@rafahsolucoes.com.br");
		} else {
			helper.setTo(envioEmailTO.getPessoaFisica().getEmail());
		}
		
		//Remetente do email
		helper.setFrom("faleconosco@cartaoamigo.com.br");
		
		//Assunto
		helper.setSubject("Cartão Amigo - " + TipoEmail.getPorId(envioEmailTO.getIdTipoEmail()).getDescricao());
		
		//Data do envio do email
		helper.setSentDate(new Date(System.currentTimeMillis()));
		
		//Corpo do email text/html
        helper.setText(conteudoHtml, true);
        helper.addInline("logo", new ClassPathResource("static/imagens/imagem-cartao-amigo.jpg"));
		
		//Envia o e-mail
		javaMailSender.send(msg);
		
	}
	

	
}
