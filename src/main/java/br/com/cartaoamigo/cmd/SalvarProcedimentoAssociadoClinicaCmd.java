package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.ProcedimentoAssociadoClinicaRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.entity.ProcedimentoAssociadoClinica;

@Component
public class SalvarProcedimentoAssociadoClinicaCmd {
	
	@Autowired private ProcedimentoAssociadoClinicaRepository procedimentoAssociadoClinicaRepository;

	public void salvarProcedimento(ClinicasTipoEspecialidades clinicasTipoEspecialidades, Cartao cartao) {
		ProcedimentoAssociadoClinica procedimento = new ProcedimentoAssociadoClinica();		
		procedimento.setCartao(cartao);
		procedimento.setClinicaTipoEspecidades(clinicasTipoEspecialidades);
		procedimento.setDataProcedimento(LocalDateTime.now());
		
		procedimentoAssociadoClinicaRepository.save(procedimento);
	}
}
