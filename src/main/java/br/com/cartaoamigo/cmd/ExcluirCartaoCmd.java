package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirCartaoCmd {
	
	@Autowired private CartaoRepository cartaoRepository;
	
	public void excluir(Long idCartao) {
		if(Objects.isNull(idCartao)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o Cartão. Parâmetro ausente.");
		}
		
		cartaoRepository.deleteById(idCartao);
	}

}
