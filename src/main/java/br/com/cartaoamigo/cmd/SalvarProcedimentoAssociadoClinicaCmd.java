package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.ProcedimentoAssociadoClinicaRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.entity.ProcedimentoAssociadoClinica;
import br.com.cartaoamigo.to.AssinaturasTO;

@Component
public class SalvarProcedimentoAssociadoClinicaCmd {
	
	@Autowired private ProcedimentoAssociadoClinicaRepository procedimentoAssociadoClinicaRepository;

	public void salvarProcedimento(ClinicasTipoEspecialidades clinicasTipoEspecialidades, Cartao cartao, AssinaturasTO assinaturaTO) {
		ProcedimentoAssociadoClinica procedimento = new ProcedimentoAssociadoClinica();		
		procedimento.setCartao(cartao);
		procedimento.setClinicaTipoEspecidades(clinicasTipoEspecialidades);
		procedimento.setDataProcedimento(LocalDateTime.now());
		
		if(Objects.nonNull(assinaturaTO)) {
			procedimento.setAssinaturaAtiva(assinaturaTO.getAtivo());
		}
		
		procedimentoAssociadoClinicaRepository.save(procedimento);
	}
}
