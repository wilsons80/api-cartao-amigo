package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.TipoCargoRepository;
import br.com.cartaoamigo.entity.TipoCargo;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;

@Component
public class ExcluirTipoCargoCmd {

	@Autowired private TipoCargoRepository repository;

	public void excluir(Long idTipoCargo) {

		if (Objects.isNull(idTipoCargo)) {
			throw new ParametroNaoInformadoException("Identificador da pessoa não informado.");
		}
		
		TipoCargo tipoCargo = repository.findById(idTipoCargo).orElseThrow(() -> new PessoaFisicaNaoEncontradaException("Tipo de Cargo informado não existe: " + idTipoCargo));
		repository.delete(tipoCargo);
	}

}
