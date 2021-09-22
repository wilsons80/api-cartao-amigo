package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.CartaoTOBuilder;
import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.to.CartaoTO;

@Component
public class CadastrarCartaoCmd {
	
	@Value("${sistema.url.front}") private String urlSistemaFront;
	
	@Autowired private CartaoRepository repository;
	@Autowired private CartaoTOBuilder toBuilder;
	@Autowired private GerarNumeroCartaoCmd gerarNumeroCartaoCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	
	private CartaoTO cadastrar(CartaoTO to) {
		String numeroCartao = gerarNumeroCartaoCmd.getNumero(to.getPessoaFisica().getId(), to.getIsTitular(), to.getPessoaFisica().getUf());
		to.setUrlCode(urlSistemaFront.trim() + "/validarcartao?numerocartao=" + numeroCartao);
		to.setNumeroCartao(numeroCartao);
		
		Cartao entity = toBuilder.build(to);
		entity.setDataCriado(LocalDateTime.now());
		
		return toBuilder.buildTO(repository.save(entity));
	}
	
	
	public void gerarCartao(PessoaFisica pessoaFisica, Long idTitular, Boolean isTitular) {
		CartaoTO cartaoTO = new CartaoTO();
		
		cartaoTO.setAtivo(true);
		cartaoTO.setIdTitular(idTitular);
		cartaoTO.setIsTitular(isTitular);
		cartaoTO.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(pessoaFisica));
		cartaoTO.setNomeImpresso(pessoaFisica.getNome().toUpperCase());
		
		cadastrar(cartaoTO);
	}
	

}
