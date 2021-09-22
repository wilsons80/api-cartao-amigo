package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.RedefinirSenha;
import br.com.cartaoamigo.to.RedefinirSenhaTO;

@Component
public class RedefinirSenhaTOBuilder {
	
	
	public RedefinirSenhaTO buildTO(RedefinirSenha p) {
		RedefinirSenhaTO to = new RedefinirSenhaTO();
		BeanUtils.copyProperties(p, to);
		
		
		return to;
	}
	public RedefinirSenha build(RedefinirSenhaTO p) {
		RedefinirSenha to = new RedefinirSenha();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public List<RedefinirSenha> buildTOAll(List<RedefinirSenhaTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	public List<RedefinirSenhaTO> buildAll(List<RedefinirSenha> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
