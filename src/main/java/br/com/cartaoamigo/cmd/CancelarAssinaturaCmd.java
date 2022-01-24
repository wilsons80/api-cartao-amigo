package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.AssinaturasTOBuilder;
import br.com.cartaoamigo.dao.repository.AssinaturasRepository;
import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.AssinaturasTO;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.TitularTO;

@Component
public class CancelarAssinaturaCmd {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CancelarAssinaturaCmd.class);
	
	@Autowired private AssinaturasRepository repository;
	@Autowired private AssinaturasTOBuilder toBuilder;
	@Autowired private GetAssinaturasCmd getAssinaturasCmd;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	@Autowired private GetTitularCmd getTitularCmd;
	@Autowired private CancelarCartoesCmd cancelarCartoesCmd;
	
	public AssinaturasTO cancelarAssinatura(String codigoAssinaturaPagarme, boolean enviaEmail) {
		LOGGER.info("cancelarAssinatura >>> " + codigoAssinaturaPagarme);
		
		Assinaturas assinatura = getAssinaturasCmd.getAssinaturaCodigoPagarMe(codigoAssinaturaPagarme);
		if(Objects.isNull(assinatura)) {
			throw new NotFoundException("A assinatura não foi encontrada para ser cancelada.");
		}

		TitularTO titular = getTitularCmd.getTOById(assinatura.getIdTitular());
		if(Objects.isNull(titular)) {
			throw new NotFoundException("Não foi possível encontrar o titular da assinatura.");
		}

		if(enviaEmail) {
			/////////////////////////////////////////////////////////////////////////////////
			//Enviar email de pagamento >>> Pago
			/////////////////////////////////////////////////////////////////////////////////
			EnvioEmailTO envioEmailTO = new EnvioEmailTO();
			envioEmailTO.setIdTipoEmail         (TipoEmail.CANCELAMENTO_ASSINATURA.getId());
			envioEmailTO.setPessoaFisica        (titular.getPessoaFisica());	
			envioEmailTO.setIdTipoPlano         (assinatura.getIdPlano());
			envioEmailTO.setIsTitular           (true);
			envioEmailTO.setIdAssinatura        (assinatura.getId());
			
			gravarEnvioEmailCmd.gravarEnvioEmail(envioEmailTO);	
		}
		
		assinatura.setAtivo           (false);
		assinatura.setDataCancelamento(LocalDateTime.now());
		
		cancelarCartoesCmd.cancelarCartoes(titular.getId());
		
		return toBuilder.buildTO(repository.save(assinatura));
	}
	
}
