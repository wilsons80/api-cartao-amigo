package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.AssinaturasTOBuilder;
import br.com.cartaoamigo.dao.repository.AssinaturasRepository;
import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.AssinaturasTO;

@Component
public class SalvarAssinaturaCmd {
	
	@Autowired private AssinaturasRepository repository;
	@Autowired private AssinaturasTOBuilder toBuilder;
	
	public AssinaturasTO salvarAssinatura(AssinaturasTO to) {
		Assinaturas entity = new Assinaturas();

		if(Objects.isNull(to.getIdTitular())) {
			throw new NotFoundException("O titular não foi encontrado.");
		}
		if(Objects.isNull(to.getIdPlano())) {
			throw new NotFoundException("O plano da assinatura não foi encontrado.");
		}
		if(StringUtils.isEmpty(to.getCodigoAssinatura())) {
			throw new NotFoundException("O código da assinatura não foi encontrado.");
		}
		
		entity.setAtivo           (true);
		entity.setDataCriacao     (LocalDateTime.now());
		
		entity.setCodigoAssinatura(to.getCodigoAssinatura());
		entity.setIdPlano         (to.getIdPlano());
		entity.setIdTitular       (to.getIdTitular());
		
		entity = repository.save(entity);
		return toBuilder.buildTO(entity);
	}
}
