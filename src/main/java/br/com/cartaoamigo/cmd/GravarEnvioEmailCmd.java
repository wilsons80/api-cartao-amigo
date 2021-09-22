package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.EnvioEmailTOBuilder;
import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.dao.repository.EnvioEmailRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.EnvioEmail;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.SolicitarRedefinirSenhaTO;

@Component
public class GravarEnvioEmailCmd {
    
	@Autowired private EnvioEmailRepository envioEmailRepository;
	@Autowired private EnvioEmailTOBuilder envioEmailTOBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository ;
	
	public EnvioEmailTO gravarEnvioEmail(EnvioEmailTO emailTO) {
		
		EnvioEmail envioEmail = new EnvioEmail();
		envioEmail.setId(null);
		envioEmail.setDataEnvio(null);
		envioEmail.setEnviado(false);
		envioEmail.setDataCriacao(LocalDateTime.now());
		envioEmail.setIdTipoEmail(emailTO.getIdTipoEmail());
		envioEmail.setIsTitular(emailTO.getIsTitular());
		envioEmail.setLinkPagamento(emailTO.getLinkPagamento());
		envioEmail.setIdTipoPlano(emailTO.getIdTipoPlano());
		envioEmail.setSenhaProvisoria(emailTO.getSenhaProvisoria());
		envioEmail.setPessoaFisica(pessoaFisicaTOBuilder.build(emailTO.getPessoaFisica()));
		envioEmail.setUsername(emailTO.getUsername());
		envioEmail.setIdHistoricoPagamento(emailTO.getIdHistoricoPagamento());
		
		EnvioEmail emailSalvo = envioEmailRepository.save(envioEmail);		
		return envioEmailTOBuilder.buildTO(emailSalvo);
		
	}


	
	public EnvioEmailTO gravarEnvioEmailRedefinirSenha(SolicitarRedefinirSenhaTO emailTO) {
		if(StringUtils.isEmpty(emailTO.getEmail())) {
			throw new NotFoundException("O e-mail deve ser informado");
		}
		
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findByEmail(emailTO.getEmail().toUpperCase());
		if(!pessoaFisica.isPresent()) {
			throw new PessoaFisicaNaoEncontradaException("Esse e-mail não está cadastrado em nossa base de dados.");
		}
		
		EnvioEmail envioEmail = new EnvioEmail();
		envioEmail.setId(null);
		envioEmail.setDataEnvio(null);
		envioEmail.setEnviado(false);
		envioEmail.setDataCriacao(LocalDateTime.now());
		envioEmail.setIdTipoEmail(TipoEmail.REDEFINIR_SENHA.getId());
		envioEmail.setPessoaFisica(pessoaFisica.get());
		
		EnvioEmail emailSalvo = envioEmailRepository.save(envioEmail);		
		return envioEmailTOBuilder.buildTO(emailSalvo);
		
	}
}
