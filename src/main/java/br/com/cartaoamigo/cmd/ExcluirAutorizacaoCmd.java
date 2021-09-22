package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.AutorizacaoGatewayRepository;
import br.com.cartaoamigo.entity.AutorizacaoGateway;
import br.com.cartaoamigo.exception.ClinicaNaoEncontradaException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirAutorizacaoCmd {

	@Autowired private AutorizacaoGatewayRepository repository;
	
	public void excluir(Long id) {
		if (Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Identificador da autorização não informado.");
		}
		AutorizacaoGateway entity = repository.findById(id).orElseThrow(() -> new ClinicaNaoEncontradaException("Autorização informada não existe: " + id));
		repository.delete(entity);
	}

}
