package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.ArquivoLogoMarcaEmpresaCmd;


@RestController
@RequestMapping(value = "empresa")
public class EmpresaService {

	@Autowired private ArquivoLogoMarcaEmpresaCmd arquivoCmd;

	
	
	@GetMapping(path = "/logomarca")
	public byte[] getLogoEmpresa() {
		return arquivoCmd.getArquivoLogomarca();
	}
	
}
