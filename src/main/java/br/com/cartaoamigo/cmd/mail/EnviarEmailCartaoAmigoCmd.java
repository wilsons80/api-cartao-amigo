package br.com.cartaoamigo.cmd.mail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.EnvioEmailTOBuilder;
import br.com.cartaoamigo.cmd.GravarRedefinirSenhaCmd;
import br.com.cartaoamigo.dao.repository.EnvioEmailRepository;
import br.com.cartaoamigo.entity.EnvioEmail;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.mail.sendgrid.EmailService;
import br.com.cartaoamigo.mail.GetConteudoEmailContaCriadaCmd;
import br.com.cartaoamigo.mail.GetConteudoEmailPagamentoCmd;
import br.com.cartaoamigo.mail.GetConteudoEmailRedefinirSenhaCmd;
import br.com.cartaoamigo.to.EmailTO;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.RedefinirSenhaTO;


@Component
public class EnviarEmailCartaoAmigoCmd {
	private static final Logger LOGGER=LoggerFactory.getLogger(EnviarEmailCartaoAmigoCmd.class);
	
	@Value("${spring.profiles.active}")
	private String ambiente;
	
	@Value("${pagseguro.email}")
	private String endereco_email;
	
	
	@Autowired private EnvioEmailRepository envioEmailRepository;
	@Autowired private EnvioEmailTOBuilder envioEmailTOBuilder;
	@Autowired private EmailService sendGridEmailService;
	@Autowired private GravarRedefinirSenhaCmd gravarRedefinirSenhaCmd;
	@Autowired private GetConteudoEmailPagamentoCmd getConteudoEmailPagamentoCmd; 
	@Autowired private GetConteudoEmailRedefinirSenhaCmd getConteudoEmailRedefinirSenhaCmd;
	@Autowired private GetConteudoEmailContaCriadaCmd getConteudoEmailContaCriadaCmd;
	
	
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public void enviarEmail() {
		try {
			Optional<List<EnvioEmail>> emails = envioEmailRepository.findAllNaoEnviados();
			if(emails.isPresent()) {
				LOGGER.info(">>>>> Iniciando o envio de email em " + LocalDateTime.now());
				emails.get().forEach(email -> {
					LOGGER.info(">>>> enviando email para " + email.getPessoaFisica().getEmail());
					try {
						gravarEnvioEmail(email);						
					} catch (Exception e) {
						String erro = "Erro ao enviar email " + email.getId() + " para: " + email.getPessoaFisica().getEmail();
						
						LOGGER.info("=====================================================================");
						LOGGER.info(erro);
						LOGGER.info(e.getMessage());
						LOGGER.info("=====================================================================");
					}
				});
				LOGGER.info(">>>>> Finalizando o envio de email em " + LocalDateTime.now());
			}
	
		} catch (Exception e) {
			LOGGER.info("=====================================================================");
			LOGGER.info(e.getMessage());
			LOGGER.info("=====================================================================");
		}
	}
	
	
	public void enviarEmail(Long idPessoaFisica) {
		Optional<List<EnvioEmail>> emails = envioEmailRepository.findAllNaoEnviados(idPessoaFisica);
		if(emails.isPresent()) {
			emails.get().forEach(email -> {
				try {
					gravarEnvioEmail(email);					
				} catch (Exception e) {
					LOGGER.info("=====================================================================");
					LOGGER.info(e.getMessage());
					LOGGER.info("=====================================================================");
				}
			});
		}
	}


	private void gravarEnvioEmail(EnvioEmail email) {
		LOGGER.info(">>>> enviando email para " + email.getPessoaFisica().getEmail());
		
		EnvioEmailTO envioEmailTO = envioEmailTOBuilder.buildTO(email);
		
		String conteudoHtml = "";
		String assuntoEmail = "";
		
		if(email.getIdTipoEmail().equals(TipoEmail.CONTA_CRIADA.getId())) {
			EmailTO emailTO = new EmailTO();
			emailTO.setIdTipoEmail    (TipoEmail.CONTA_CRIADA.getId());
			emailTO.setPessoaFisica   (envioEmailTO.getPessoaFisica());
			emailTO.setIsTitular      (envioEmailTO.getIsTitular());
			emailTO.setUsername       (envioEmailTO.getUsername());
			emailTO.setSenhaProvisoria(envioEmailTO.getSenhaProvisoria());
			emailTO.setIdTipoPlano    (envioEmailTO.getIdTipoPlano());
			
			conteudoHtml = getConteudoEmailContaCriadaCmd.getConteudoHtml(emailTO).toString();
		}
		if(email.getIdTipoEmail().equals(TipoEmail.REDEFINIR_SENHA.getId())) {
			RedefinirSenhaTO redefinirSenhaTO = gravarRedefinirSenhaCmd.gravarEnvioEmail(new RedefinirSenhaTO(envioEmailTO.getPessoaFisica().getEmail()));
			
			EmailTO emailTO = new EmailTO();
			emailTO.setIdTipoEmail         (TipoEmail.REDEFINIR_SENHA.getId());
			emailTO.setLinkRedefinicaoSenha(redefinirSenhaTO.getLink());
			emailTO.setPessoaFisica        (envioEmailTO.getPessoaFisica());
			
			conteudoHtml = getConteudoEmailRedefinirSenhaCmd.getConteudoHtml(emailTO).toString();
		}	
		if(email.getIdTipoEmail().equals(TipoEmail.PAGAMENTO.getId())) {
			
			EmailTO emailTO = new EmailTO();
			emailTO.setIdTipoEmail         (TipoEmail.PAGAMENTO.getId());
			emailTO.setLinkPagamento       (envioEmailTO.getLinkPagamento());
			emailTO.setPessoaFisica        (envioEmailTO.getPessoaFisica());
			emailTO.setIdTipoPlano         (envioEmailTO.getIdTipoPlano());
			emailTO.setIdHistoricoPagamento(envioEmailTO.getIdHistoricoPagamento());
			
			conteudoHtml = getConteudoEmailPagamentoCmd.getConteudoHtml(emailTO).toString();
		}
		
		assuntoEmail = "Cartão Amigo - " + TipoEmail.getPorId(envioEmailTO.getIdTipoEmail()).getDescricao().replaceAll("_", " ");		
		if(isAmbienteDesenvolvimento()) {
			sendGridEmailService.sendHTML(endereco_email, assuntoEmail, conteudoHtml);
		} else {
			sendGridEmailService.sendHTML(envioEmailTO.getPessoaFisica().getEmail(), assuntoEmail, conteudoHtml);
		}
		
		email.setDataEnvio(LocalDateTime.now());
		email.setEnviado(true);
		envioEmailRepository.save(email);
	}

	
	
	
	private boolean isAmbienteDesenvolvimento() {
		return !isAmbienteProducao();
	}
	private boolean isAmbienteProducao() {
		return ambiente != null && "prod".toUpperCase().equals(ambiente.toUpperCase());
	}
	
}