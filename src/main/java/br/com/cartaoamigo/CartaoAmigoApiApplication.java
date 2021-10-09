package br.com.cartaoamigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@EnableTransactionManagement
@SpringBootApplication
public class CartaoAmigoApiApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CartaoAmigoApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CartaoAmigoApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
