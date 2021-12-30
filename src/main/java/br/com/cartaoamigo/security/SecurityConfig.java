package br.com.cartaoamigo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import br.com.cartaoamigo.cmd.GetUsuarioAutenticadoCmd;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private GetUsuarioAutenticadoCmd getAutenticadorCmd;

	@Autowired
	private CustomPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getAutenticadorCmd).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/autenticador/login",
				                   "/pagseguro/**",
				                   "/pagarme/**",
				                   "/contaassociado/criar",
				                   "/pagamento/corretor/**",
				                   "/corretores/token/**",
				                   "/validarcartao/**",
				                   "/endereco/estados", 
				                   "/dependentestitular/isdependente/**",
				                   "/redefinirsenha/**",
				                   "/tipoplanos/ativos",
				                   "/clinicas/ativas",
				                   "/voucher/codigo/**",
				                   "/clinicastipoespecialidade/clinica/**",
				                   "/clinicastipoespecialidade/filter/**",
				                   "/endereco/cep/**",
				                   "/endereco/bairros/**",
				                   "/tipoespecialidades/combo",
				                   "/css/**",
				                   "/fonts/**", 
				                   "/images/**", 
				                   "/js/**", 
				                   "/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/")
	        .authenticated()
	    .and()
	      .formLogin()
	      .loginPage("/login")
	      .permitAll()
		.and()
		  .logout()
		  .logoutSuccessHandler(logoutSuccessHandler())
		  .permitAll()
	    .and()
	    	.cors()
	    .and()
		  .csrf()
		  .disable()
		  .authorizeRequests()
		  .anyRequest()
		  .authenticated();
		
		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean(name = "authenticadorManagerBean")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
	    return new CustomLogoutSuccessHandler();
	}	
	
	
}
