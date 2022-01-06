package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
	
	@Autowired private AssinaturasRepository repository;
	@Autowired private AssinaturasTOBuilder toBuilder;
	@Autowired private GetAssinaturasCmd getAssinaturasCmd;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	@Autowired private GetTitularCmd getTitularCmd;
	
	public AssinaturasTO cancelarAssinatura(Long idAssinatura) {
		Optional<Assinaturas> assinatura = getAssinaturasCmd.findById(idAssinatura);
		if(!assinatura.isPresent()) {
			throw new NotFoundException("A assinatura não foi encontrada para ser cancelada.");
		}

		TitularTO titular = getTitularCmd.getTOById(assinatura.get().getIdTitular());
		if(Objects.isNull(titular)) {
			throw new NotFoundException("Não foi possível encontrar o titular da assinatura.");
		}

		/////////////////////////////////////////////////////////////////////////////////
		//Enviar email de pagamento >>> Pago
		/////////////////////////////////////////////////////////////////////////////////
		EnvioEmailTO envioEmailTO = new EnvioEmailTO();
		envioEmailTO.setIdTipoEmail         (TipoEmail.CANCELAMENTO_ASSINATURA.getId());
		envioEmailTO.setPessoaFisica        (titular.getPessoaFisica());	
		envioEmailTO.setIdTipoPlano         (assinatura.get().getIdPlano());
		envioEmailTO.setIsTitular           (true);
		envioEmailTO.setIdAssinatura        (idAssinatura);
		
		gravarEnvioEmailCmd.gravarEnvioEmail(envioEmailTO);	
		
		assinatura.get().setAtivo           (false);
		assinatura.get().setDataCancelamento(LocalDateTime.now());
		
		return toBuilder.buildTO(repository.save(assinatura.get()));
	}
	
}
