package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.GatewayPagamentoRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirGatewayPagamentoCmd {
	
	@Autowired private GatewayPagamentoRepository repository;
	
	public void excluir(Long idGatewayPagamento) {
		if(Objects.isNull(idGatewayPagamento)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o GatewayPagamento. Parâmetro ausente.");
		}
		
		repository.deleteById(idGatewayPagamento);
	}
}
