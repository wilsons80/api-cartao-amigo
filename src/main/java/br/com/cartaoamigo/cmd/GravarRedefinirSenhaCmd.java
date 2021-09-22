package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.RedefinirSenhaTOBuilder;
import br.com.cartaoamigo.dao.repository.RedefinirSenhaRepository;
import br.com.cartaoamigo.entity.RedefinirSenha;
import br.com.cartaoamigo.infra.util.Java8DateUtil;
import br.com.cartaoamigo.to.RedefinirSenhaTO;

@Component
public class GravarRedefinirSenhaCmd {
	
	@Value("${url.cartaoamigo.redefinirsenha}")
	private String urlRedefinirSenha; 
    
	@Autowired private RedefinirSenhaRepository envioEmailRepository;
	@Autowired private RedefinirSenhaTOBuilder toBuilder;
	
	public RedefinirSenhaTO gravarEnvioEmail(RedefinirSenhaTO to) {
		RedefinirSenha entity = new RedefinirSenha();
		entity.setId(null);
		
		LocalDateTime duracao = LocalDateTime.now().plusHours(1L);
		entity.setCodigoValidacao(String.valueOf(Java8DateUtil.getDate(duracao).getTime()));
		entity.setDataValidade(duracao);
		entity.setEmail(to.getEmail());
		entity.setLink(String.format(urlRedefinirSenha, entity.getCodigoValidacao() ));		
		
		RedefinirSenha emailSalvo = envioEmailRepository.save(entity);		
		return toBuilder.buildTO(emailSalvo);
		
	}

}
