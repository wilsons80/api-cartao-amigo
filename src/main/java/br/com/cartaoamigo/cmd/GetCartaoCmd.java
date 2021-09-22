package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.CartaoTOBuilder;
import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.to.CartaoTO;

@Component
public class GetCartaoCmd {
	
	@Autowired private CartaoRepository repository;
	@Autowired private CartaoTOBuilder toBuilder;
	
	
	public CartaoTO getCartaoTitularByIdPessoaFisica(Long idPessoaFisica) {
		Optional<Cartao> entity = repository.findCartaoTitularByIdPessoaFisica(idPessoaFisica);
		if(entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return null;
	}
	
	public CartaoTO getCartaoDependenteByIdPessoaFisica(Long idPessoaFisica) {
		Optional<Cartao> entity = repository.findCartaoDependenteByIdPessoaFisica(idPessoaFisica);
		return toBuilder.buildTO(entity.get());
	}

	public CartaoTO findByNumeroCartao(String numeroCartao) {
		Optional<Cartao> entity = repository.findByNumeroCartao(numeroCartao);
		return toBuilder.buildTO(entity.get());
	}
	
	public Cartao getByNumeroCartao(String numeroCartao) {
		Optional<Cartao> entity = repository.findByNumeroCartao(numeroCartao);
		return entity.orElse(null);
	}
	
	public List<CartaoTO> getAll() {
		Optional<List<Cartao>> entitys = Optional.ofNullable(repository.findAll());
		return toBuilder.buildAll(entitys.get());
	}
	
	public List<CartaoTO> findAllAtivos() {
		Optional<List<Cartao>> entitys = repository.findAllByStatus(false);
		return toBuilder.buildAll(entitys.get());
	}

	public List<CartaoTO> findAllInativos() {
		Optional<List<Cartao>> entitys = repository.findAllByStatus(true);
		if(!entitys.isPresent()) {return null;}
		return toBuilder.buildAll(entitys.get());
	}
	
	private List<CartaoTO> getAllPorCpf(String cpf, boolean isTitular) {
		Optional<List<Cartao>> entitys = repository.findAllByCpf(cpf, isTitular);
		if(!entitys.isPresent()) {return null;}
		return toBuilder.buildAll(entitys.get());
	}
	
	public List<CartaoTO> getAllComoTitular(String cpf) {
		return getAllPorCpf(cpf, true);
	}
	
	public List<CartaoTO> getAllComoDependente(String cpf) {
		return getAllPorCpf(cpf, false);
	}
}
