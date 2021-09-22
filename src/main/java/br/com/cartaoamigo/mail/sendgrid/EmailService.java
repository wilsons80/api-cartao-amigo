package br.com.cartaoamigo.mail.sendgrid;

public interface EmailService {

    void sendText(String to, String subject, String body);
    void sendHTML(String to, String subject, String body);
    
}
