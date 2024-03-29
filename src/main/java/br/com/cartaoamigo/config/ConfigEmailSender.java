package br.com.cartaoamigo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackages = {"mail"})
@PropertySource({"classpath:sendmail.properties"})
@EnableAsync
public class ConfigEmailSender {

	@Autowired
    private Environment env;

    private final String PROPERTY_HOST     = "mail.host";
    private final String PROPERTY_PORT     = "mail.port";
    private final String PROPERTY_USERNAME = "mail.username";
    private final String PROPERTY_PASSWORD = "mail.password";

    private final String PROPERTY_TRANSPORT_PROTOCOL   = "mail.transport.protocol";
    private final String PROPERTY_SMTP_AUTH            = "mail.smtp.auth";
    private final String PROPERTY_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private final String PROPOERTY_SMTP_TIMEOUT        = "mail.smtp.connectiontimeout";
    private final String PROPERTY_DEBUG                = "mail.debug";
    
    //private final String PROPERTY_SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
    //private final String PROPERTY_SMTP_SSL_ENABLE        = "mail.smtp.ssl.enable";
    		
    		

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getRequiredProperty(PROPERTY_HOST));
        javaMailSender.setPort(parsePortProperty(env.getRequiredProperty(PROPERTY_PORT)));
        javaMailSender.setUsername(env.getRequiredProperty(PROPERTY_USERNAME));
        javaMailSender.setPassword(env.getRequiredProperty(PROPERTY_PASSWORD));
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_TRANSPORT_PROTOCOL, env.getRequiredProperty(PROPERTY_TRANSPORT_PROTOCOL));
        properties.setProperty(PROPERTY_SMTP_AUTH, env.getRequiredProperty(PROPERTY_SMTP_AUTH));
        properties.setProperty(PROPERTY_SMTP_STARTTLS_ENABLE, env.getRequiredProperty(PROPERTY_SMTP_STARTTLS_ENABLE));
        properties.setProperty(PROPERTY_DEBUG, env.getRequiredProperty(PROPERTY_DEBUG));
        properties.setProperty(PROPOERTY_SMTP_TIMEOUT, env.getRequiredProperty(PROPOERTY_SMTP_TIMEOUT));
        
        //properties.setProperty(PROPERTY_SMTP_STARTTLS_REQUIRED, env.getRequiredProperty(PROPERTY_SMTP_STARTTLS_REQUIRED));
        //properties.setProperty(PROPERTY_SMTP_SSL_ENABLE, env.getRequiredProperty(PROPERTY_SMTP_SSL_ENABLE));
        return properties;
    }

    private Integer parsePortProperty(String port){
        return Integer.valueOf(port);
    }
}
