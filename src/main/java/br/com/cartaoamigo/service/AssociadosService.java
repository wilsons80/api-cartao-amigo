package br.com.cartaoamigo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetAssociadosCmd;
import br.com.cartaoamigo.dao.dto.AssociadoComboDTO;
import br.com.cartaoamigo.dao.dto.AssociadosDTO;

 @RestController
 @RequestMapping(value = "associados")
 public class AssociadosService {
	
	@Autowired private GetAssociadosCmd getCmd;
	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AssociadosDTO> getAllFilter(@RequestParam(name = "idPessoaFisicaTitular", required = false) Long idPessoaFisicaTitular,
			                                @RequestParam(name = "ativo", required = false) String ativo,
								            @RequestParam(name = "dataInicioGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioGerado,
		                                    @RequestParam(name = "dataFimGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimGerado
		                                    ) {
		return getCmd.getAllFilter(idPessoaFisicaTitular, ativo, dataInicioGerado, dataFimGerado );
	}

	@GetMapping(path = "/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AssociadoComboDTO> getAllCombo() {
		return getCmd.getAllCombo();
	}
}
