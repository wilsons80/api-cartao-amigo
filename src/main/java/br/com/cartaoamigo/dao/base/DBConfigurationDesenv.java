package br.com.cartaoamigo.dao.base;

import java.net.URI;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("dev")
@Configuration
public class DBConfigurationDesenv {

	@Value("${heroku.database.url}")
	private String dbUrl;
	
	@Bean
    public BasicDataSource dataSource() throws Exception {
        URI dbUri = new URI(dbUrl);

        Class.forName("org.postgresql.Driver");
        
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        System.out.println("========================================================================================================================================================");
        System.out.println("DESENVOLVIMENTO: Criando conexção na URL: " + dbUrl);
        System.out.println("========================================================================================================================================================");        
        
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
	
}
