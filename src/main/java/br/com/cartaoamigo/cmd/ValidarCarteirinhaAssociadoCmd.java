package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.exception.CartaoNaoEncontradoException;
import br.com.cartaoamigo.exception.ClinicaTipoEspecialidadeNaoEncontradaException;
import br.com.cartaoamigo.to.AssinaturasTO;
import br.com.cartaoamigo.to.TitularTO;
import br.com.cartaoamigo.to.ValidacaoCarteirinhaAssociadoTO;

@Component
public class ValidarCarteirinhaAssociadoCmd {
	
	@Autowired private GetClinicasTipoEspecialidadeCmd getClinicasTipoEspecialidadeCmd;
	@Autowired private GetCartaoCmd getCartaoCmd;
	@Autowired private SalvarProcedimentoAssociadoClinicaCmd salvarProcedimentoAssociadoClinicaCmd;
	@Autowired private GetTitularCmd getTitularCmd;
	@Autowired private CartaoRepository cartaoRepository;
	@Autowired private GetAssinaturasCmd getAssinaturasCmd;
	
	public ValidacaoCarteirinhaAssociadoTO getDadosCartao(Long idClinica, Long idTipoEspecialidade, String numeroCartao) {
		Cartao cartao = getCartaoCmd.getByNumeroCartao(numeroCartao);
		if(Objects.isNull(cartao)) {
			throw new CartaoNaoEncontradoException("O cartão informado não existe em nossa base de dados.");
		}

		Optional<ClinicasTipoEspecialidades> clinicasTipoEspecialidadesOptional = getClinicasTipoEspecialidadeCmd.getByClinicaAndTipoEspecialidade(idClinica, idTipoEspecialidade);
		if(!clinicasTipoEspecialidadesOptional.isPresent()) {
			throw new ClinicaTipoEspecialidadeNaoEncontradaException("Essa clínica não está cadastrada para atender com esse tipo de especialidade.");
		}
		
		TitularTO titularTO = getTitularCmd.getTOById(cartao.getIdTitular());
		
		AssinaturasTO assinaturaTO = getAssinaturasCmd.findAtivaByIdTitular(titularTO.getId());
		
		// salva os dados do procedimento para ser mostrado no dashboard
		salvarProcedimentoAssociadoClinicaCmd.salvarProcedimento(clinicasTipoEspecialidadesOptional.get(), cartao, assinaturaTO);		
		
		ValidacaoCarteirinhaAssociadoTO carteirinhaTO = new ValidacaoCarteirinhaAssociadoTO();
		carteirinhaTO.setStatus           (false);
		
		//Busca a data de validade baseado no titular, pois o dependente não faz pagamento da assinatura.
		if(cartao.getIsTitular()) {
			LocalDate dataFimValidade = Objects.nonNull(cartao.getDataValidadePlano()) ? cartao.getDataValidadePlano().toLocalDate() : null;
			carteirinhaTO.setDataFimValidade(dataFimValidade);
		} else {
			Optional<Cartao> cartaoTitular = cartaoRepository.findCartaoTitularByIdPessoaFisica(titularTO.getPessoaFisica().getId());
			if(cartaoTitular.isPresent()) {
				LocalDate dataFimValidade = Objects.nonNull(cartaoTitular.get().getDataValidadePlano()) ? cartaoTitular.get().getDataValidadePlano().toLocalDate() : null;
				carteirinhaTO.setDataFimValidade(dataFimValidade);
			}
		}
		
		//Validação com PAGAR.ME
		if(Objects.nonNull(assinaturaTO)) {
			if(cartao.getAtivo() && titularTO.getAtivo() && assinaturaTO.getAtivo()) {
				carteirinhaTO.setStatus(true);
			}
		} else {
			//Validação com PAGSEGURO
			if(cartao.getAtivo() && titularTO.getAtivo() && Objects.nonNull(cartao.getDataValidadePlano()) && carteirinhaTO.getDataFimValidade().isAfter(LocalDate.now())) {
				carteirinhaTO.setStatus(true);
			}
		}
		
		carteirinhaTO.setNumeroCartao(cartao.getNumeroCartao());
		carteirinhaTO.setNomeImpresso(cartao.getNomeImpresso());
		
		return carteirinhaTO;
		
	}



	
}
