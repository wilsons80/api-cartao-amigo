package br.com.cartaoamigo.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ConfigProfileDev extends ConfigProfile {

	public ConfigProfileDev() {
		
	}
}
