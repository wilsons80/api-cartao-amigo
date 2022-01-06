package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.AssinaturasTOBuilder;
import br.com.cartaoamigo.dao.repository.AssinaturasRepository;
import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.to.AssinaturasTO;

@Component
public class GetAssinaturasCmd {
	
	@Autowired private AssinaturasRepository repository;
	@Autowired private AssinaturasTOBuilder toBuilder;

	public Optional<Assinaturas> findById(Long idAssinatura) {
		Optional<Assinaturas> entity = repository.findById(idAssinatura);
		return Optional.ofNullable(entity.orElse(null));
	}
	
	public Optional<AssinaturasTO> findTOById(Long idAssinatura) {
		Optional<Assinaturas> entity = repository.findById(idAssinatura);
		return Optional.ofNullable(toBuilder.buildTO(entity.orElse(null)));
	}
	
	public AssinaturasTO findAtivaByIdTitular(Long idTitular) {
		Optional<Assinaturas> entity = repository.findAssinaturaAtivaByTitular(idTitular);
		return toBuilder.buildTO(entity.orElse(null));
	}
	
	public List<AssinaturasTO> getAllAssinaturasTitular(Long idTitular) {
		Optional<List<Assinaturas>> entitys = repository.findAllAssinaturasTitular(idTitular);
		return toBuilder.buildAll(entitys.orElse(null));
	}
	
	public AssinaturasTO getAssinaturaCodigoPagarMe(String codigoAssinatura) {
		Optional<Assinaturas> entitys = repository.findAssinaturaCodigoPagarMe(codigoAssinatura);
		return toBuilder.buildTO(entitys.orElse(null));
	}

}
