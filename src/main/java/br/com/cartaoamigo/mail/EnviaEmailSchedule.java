package br.com.cartaoamigo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.mail.EnviarEmailCartaoAmigoCmd;

@EnableScheduling 
@Component
public class EnviaEmailSchedule {
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	private final long DOIS_MINUTO = MINUTO * 1;
	
	@Autowired private EnviarEmailCartaoAmigoCmd enviarCmd;
	
	@Scheduled(fixedDelay = DOIS_MINUTO) 
    public void verificaPorMinuta() { 
		enviarCmd.enviarEmail();
    }
	
		
}
