package br.com.cartaoamigo.mail.sendgrid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import br.com.cartaoamigo.exception.EmailNaoEnviadoException;

/**
 * {@link} https://app.sendgrid.com/guide/integrate/langs/java
 * @author wilson Souza
 *
 */
@Service
public class SendGridEmailService implements EmailService {

	@Value("${sendgrid.api.key}") 
	private String apiKeySendGrid;
	
	@Value("${sendgrid.email.cartaoamigo}")
	private String emailFrom;
	


    @Override
    public void sendText(String to, String subject, String body) {
        Response response = sendEmail(to, subject, new Content("text/plain", body));
        System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: " + response.getHeaders());
    }
    

    @Override
    public void sendHTML(String to, String subject, String body) {
        Response response = sendEmail(to, subject, new Content("text/html", body));
        System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: " + response.getHeaders());
    }

    
    private Response sendEmail(String to, String subject, Content content) {
        try {
        	Mail mail = new Mail(new Email(emailFrom), subject, new Email(to), content);
        	mail.setReplyTo(new Email(emailFrom));
        	
        	
        	Response response = null;
        	Request request = new Request();
        	SendGrid sg = new SendGrid(apiKeySendGrid);
        	
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            response = sg.api(request);
           
            return response;
            
        } catch (IOException ex) {
            throw new EmailNaoEnviadoException("Email não enviado.");
        }
    }
}
