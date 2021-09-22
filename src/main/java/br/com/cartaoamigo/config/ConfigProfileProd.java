package br.com.cartaoamigo.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ConfigProfileProd extends ConfigProfile {

	public ConfigProfileProd() {
		
	}
	
}
