package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.dao.PessoaFisicaDao;
import br.com.cartaoamigo.dao.dto.AssociadoComboDTO;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.ComboAssociadoTO;
import br.com.cartaoamigo.to.PessoaFisicaTO;

@Component
public class GetPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private PessoaFisicaTOBuilder toBuilder;
	@Autowired private PessoaFisicaDao pessoaFisicaDao;
	
	
	public List<ComboAssociadoTO> getAllAssociadosByCombo() {
		List<AssociadoComboDTO> pessoas = pessoaFisicaDao.getAllByAssociadosCombo();
		return toBuilder.buildAllComboAssociadoDTO(pessoas);
	}
	
	
	public List<PessoaFisicaTO> getAll() {
		List<PessoaFisica> entitys = repository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<PessoaFisicaTO>();
		}
		return toBuilder.buildAll(entitys);
	}

	public PessoaFisicaTO getTOById(Long id) {
		PessoaFisica entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa física não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public PessoaFisica getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
