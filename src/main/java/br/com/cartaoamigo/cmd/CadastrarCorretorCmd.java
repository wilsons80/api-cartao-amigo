package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.CorretorTOBuilder;
import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.rule.CamposObrigatoriosCorretorRule;
import br.com.cartaoamigo.to.CorretorTO;

@Component
public class CadastrarCorretorCmd {
	
	@Value("${url.linkpagamento.corretor}")     
	private String urlLinkPagamentoCorretor;
	
	@Autowired private CorretorRepository repository;
	@Autowired private CorretorTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosCorretorRule rule;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	
	
	public CorretorTO salvar(CorretorTO to) {
		to.setAtivo(true);
		rule.verificar(to);
		Corretor entity = toBuilder.build(to);
		
		PessoaFisica pessoaFisica = entity.getPessoaFisica();		
		Optional<PessoaFisica> pessoaFisicaOptional = pessoaFisicaRepository.findByCpf(entity.getPessoaFisica().getCpf());
		if(pessoaFisicaOptional.isPresent()) {
			pessoaFisica = pessoaFisicaTOBuilder.buildNewEntity(to.getPessoaFisica(), pessoaFisicaOptional.get());
			pessoaFisica.setDataCadastro(LocalDateTime.now());
		} 
		
		pessoaFisicaOptional = pessoaFisicaRepository.findByEmail(to.getPessoaFisica().getEmail().toUpperCase());
		if(pessoaFisicaOptional.isPresent()) {
			pessoaFisica = pessoaFisicaTOBuilder.buildNewEntity(to.getPessoaFisica(), pessoaFisicaOptional.get());
			pessoaFisica.setDataCadastro(LocalDateTime.now());
		}
		
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		entity.setPessoaFisica(pessoaFisica);	
		
		entity.setDtCadastro(LocalDateTime.now());
		entity.setCodigo(getCodigo());
		entity.setToken(getToken());
		entity.setLinkPagamento(String.format(urlLinkPagamentoCorretor, entity.getToken()));
		
		return toBuilder.buildTO( repository.save(entity));
	}
	
	
	private String getToken() {
		String token = RandomStringUtils.randomNumeric(8).toUpperCase();		
		Optional<Corretor> codigoCorretor = repository.findByToken(token);
		while(codigoCorretor.isPresent()) {
			token = RandomStringUtils.randomNumeric(8).toUpperCase();
			codigoCorretor = repository.findByToken(token);
		}
		return token;
	}

	private String getCodigo() {
		String codigo = RandomStringUtils.randomAlphabetic(3).toUpperCase();
		Optional<Corretor> codigoCorretor = repository.findByCodigo(codigo);
		while(codigoCorretor.isPresent()) {
			codigo = RandomStringUtils.randomAlphabetic(3).toUpperCase();
			codigoCorretor = repository.findByCodigo(codigo);
		}
		return codigo;
	}

}
