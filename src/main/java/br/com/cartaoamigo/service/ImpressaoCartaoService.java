package br.com.cartaoamigo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetImpressaoCartaoCmd;
import br.com.cartaoamigo.cmd.relatorios.GerarRelatorioFaturasPagarCmd;
import br.com.cartaoamigo.to.relatorios.ImpressaoCartaoTO;

@RestController
@RequestMapping(value = "impressaocartao")
public class ImpressaoCartaoService {

	@Autowired private GerarRelatorioFaturasPagarCmd gerarRelatorioCmd;
	@Autowired private GetImpressaoCartaoCmd getCmd;
	
	
	@PostMapping(path = "/gerar-impressao-cartao", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<ImpressaoCartaoTO> dados) {
		return gerarRelatorioCmd.gerarPDF(dados);
	}
	
	@PostMapping(path = "/gerar-carta-boas-vindas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerarCartaBoasVindas(@RequestBody List<ImpressaoCartaoTO> dados) {
		return gerarRelatorioCmd.gerarCartaBoasVindas(dados);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ImpressaoCartaoTO> getAllFilter(@RequestParam(name = "idpessoafisica", required = false) Long idPessoaFisica,
			                                    @RequestParam(name = "numerocartao", required = false) String numeroCartao,
			                                    @RequestParam(name = "impresso", required = false) String impresso,
			                                    @RequestParam(name = "tipoAssociado", required = false) String tipoAssociado,
								                @RequestParam(name = "dataInicioGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioGerado,
		                                        @RequestParam(name = "dataFimGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimGerado,
								                @RequestParam(name = "dataInicioImpresso", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioImpresso,
		                                        @RequestParam(name = "dataFimImpresso", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimImpresso
		                                        ) {
		return getCmd.getAllFilter(idPessoaFisica, numeroCartao, dataInicioGerado, dataFimGerado, dataInicioImpresso, dataFimImpresso, impresso, tipoAssociado);
	}
	
}
