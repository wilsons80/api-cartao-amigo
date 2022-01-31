package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.CarteiraCartaoPagamentoAssociadoTOBuilder;
import br.com.cartaoamigo.dao.repository.CarteiraCartaoPagamentoAssociadoRepository;
import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.to.CarteiraCartaoPagamentoAssociadoTO;

@Component
public class GetCarteiraCartaoPagamentoAssociadoCmd {
	
	@Autowired private CarteiraCartaoPagamentoAssociadoRepository repository;
	@Autowired private CarteiraCartaoPagamentoAssociadoTOBuilder toBuilder;
	

	public CarteiraCartaoPagamentoAssociado getById(Long id) {
		Optional<CarteiraCartaoPagamentoAssociado> entity = repository.findById(id);
		return entity.orElse(null);
	}

	
	public CarteiraCartaoPagamentoAssociado getByIdCartaoPagarme(String idCartaoPagarme) {
		Optional<CarteiraCartaoPagamentoAssociado> entity = repository.findByIdCartaoPagarme(idCartaoPagarme);
		return entity.orElse(null);
	}
	
	
	public CarteiraCartaoPagamentoAssociado getByIdCartaoPagarmeAndIdTitular(String idCartaoPagarme, Long idTitular) {
		Optional<CarteiraCartaoPagamentoAssociado> entity = repository.findByIdCartaoPagarmeAndIdTitular(idCartaoPagarme, idTitular);
		return entity.orElse(null);
	}
	
	public CarteiraCartaoPagamentoAssociadoTO getTOByIdCartaoPagarme(String idCartaoPagarme) {
		CarteiraCartaoPagamentoAssociado entity = getByIdCartaoPagarme(idCartaoPagarme);
		if(Objects.nonNull(entity)) {
			return toBuilder.buildTO(entity);
		}
		return null;
	}
	
	public List<CarteiraCartaoPagamentoAssociadoTO> getAllAtivoByTitular(Long idTitular) {
		Optional<List<CarteiraCartaoPagamentoAssociado>> entitys = repository.findAllAtivoByTitular(idTitular);
		return toBuilder.buildAll(entitys.get());
	}
	
	public List<CarteiraCartaoPagamentoAssociadoTO> getAllAtivoByClientePagarMe(String idClientePagarme) {
		Optional<List<CarteiraCartaoPagamentoAssociado>> entitys = repository.findAllAtivoByClientePagarMe(idClientePagarme);
		return toBuilder.buildAll(entitys.get());
	}
}
