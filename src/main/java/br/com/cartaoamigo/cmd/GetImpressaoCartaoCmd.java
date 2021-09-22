package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ImpressaoCartaoTOBuilder;
import br.com.cartaoamigo.dao.ImpressaoCartaoDao;
import br.com.cartaoamigo.dao.dto.ImpressaoCartaoDTO;
import br.com.cartaoamigo.to.relatorios.ImpressaoCartaoTO;

@Component
public class GetImpressaoCartaoCmd {
	
	
	@Autowired private ImpressaoCartaoDao       dao;
	@Autowired private ImpressaoCartaoTOBuilder toBuilder;
	
	
	public List<ImpressaoCartaoTO> getAllFilter(Long idPessoaFisica, String numeroCartao,  LocalDate dataInicioGerado, LocalDate dataFimGerado, LocalDate dataInicioImpresso, LocalDate dataFimImpresso, String impresso, String tipoAssociado) {
		Optional<List<ImpressaoCartaoDTO>> entitys = Optional.empty();

		entitys = dao.getAllFilter(idPessoaFisica, numeroCartao, dataInicioGerado, dataFimGerado, dataInicioImpresso, dataFimImpresso, impresso, tipoAssociado);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<ImpressaoCartaoTO>();
		
	}
	

	
}
