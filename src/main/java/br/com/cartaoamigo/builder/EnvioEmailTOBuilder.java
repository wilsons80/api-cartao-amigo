package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.EnvioEmail;
import br.com.cartaoamigo.to.EnvioEmailTO;

@Component
public class EnvioEmailTOBuilder {
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder; 

	
	public EnvioEmailTO buildTO(EnvioEmail p) {
		EnvioEmailTO to = new EnvioEmailTO();
		BeanUtils.copyProperties(p, to);
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		
		return to;
	}
	public EnvioEmail build(EnvioEmailTO p) {
		EnvioEmail to = new EnvioEmail();
		BeanUtils.copyProperties(p, to);
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.build(p.getPessoaFisica()));
		
		return to;
	}
	
	public List<EnvioEmail> buildTOAll(List<EnvioEmailTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	public List<EnvioEmailTO> buildAll(List<EnvioEmail> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
