package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.EmpresaRepository;
import br.com.cartaoamigo.entity.Empresa;

@Component
public class GetEmpresaCmd {

	@Autowired private EmpresaRepository empresaRepository ;
	
	public Empresa get() {
		Empresa empresa = empresaRepository.findAll().stream().findAny().orElse(null);
		return empresa;
	}
}
