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
		return entity.isPresent() ? Optional.ofNullable(entity.get()) : null;
	}
	
	public Optional<AssinaturasTO> findTOById(Long idAssinatura) {
		Optional<Assinaturas> entity = repository.findById(idAssinatura);
		return entity.isPresent() ? Optional.ofNullable(toBuilder.buildTO(entity.get())) : null;
	}
	
	public AssinaturasTO findAtivaByIdTitular(Long idTitular) {
		Optional<Assinaturas> entity = repository.findAssinaturaAtivaByTitular(idTitular);
		return entity.isPresent() ? toBuilder.buildTO(entity.get()) : null;
	}
	
	public List<AssinaturasTO> getAllAssinaturasTitular(Long idTitular) {
		Optional<List<Assinaturas>> entitys = repository.findAllAssinaturasTitular(idTitular);
		return entitys.isPresent() ? toBuilder.buildAll(entitys.get()) : null;
	}
	
	public AssinaturasTO getAssinaturaTOCodigoPagarMe(String codigoAssinatura) {
		Optional<Assinaturas> entitys = repository.findAssinaturaCodigoPagarMe(codigoAssinatura);
		return entitys.isPresent() ? toBuilder.buildTO(entitys.get()) : null;
	}

	public Assinaturas getAssinaturaCodigoPagarMe(String codigoAssinatura) {
		Optional<Assinaturas> entitys = repository.findAssinaturaCodigoPagarMe(codigoAssinatura);
		return entitys.isPresent() ? entitys.get() : null;
	}

}
