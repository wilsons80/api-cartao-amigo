package br.com.cartaoamigo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AlterarVoucherCmd;
import br.com.cartaoamigo.cmd.CadastrarVoucherCmd;
import br.com.cartaoamigo.cmd.ExcluirVoucherCmd;
import br.com.cartaoamigo.cmd.ExportarVoucherCmd;
import br.com.cartaoamigo.cmd.GetVoucherCmd;
import br.com.cartaoamigo.dao.dto.VoucherDTO;
import br.com.cartaoamigo.to.VoucherTO;

@RestController
@RequestMapping(value = "voucher")
public class VoucherService {

	@Autowired private GetVoucherCmd getCmd;
	@Autowired private CadastrarVoucherCmd salvarCmd;
	@Autowired private AlterarVoucherCmd alterarCmd;
	@Autowired private ExcluirVoucherCmd excluirCmd;
	@Autowired private ExportarVoucherCmd exportarVoucherCmd;
	
	@GetMapping(path = "/{idVoucher}", produces = MediaType.APPLICATION_JSON_VALUE )
	public VoucherTO getById(@PathVariable(name = "idVoucher") Long idVoucher) {
		return getCmd.getById(idVoucher);
	}

	@GetMapping(path = "/codigo/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE )
	public VoucherTO getByCodigo(@PathVariable(name = "codigo") String codigo) {
		return getCmd.getByCodigo(codigo);
	}

	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VoucherDTO> getAllFilter(@RequestParam(name = "codigo", required = false) String codigo,
			                             @RequestParam(name = "ativo", required = false) String ativo,
			                             @RequestParam(name = "utilizado", required = false) String utilizado,
								         @RequestParam(name = "dataInicioGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioGerado,
		                                 @RequestParam(name = "dataFimGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimGerado
		                                ) {
		return getCmd.getAllFilter(codigo, ativo, utilizado, dataInicioGerado, dataFimGerado);
	}
	
	@PostMapping(path = "/lista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VoucherTO> salvar(@RequestBody List<VoucherTO> to) {
		return salvarCmd.salvar(to);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public VoucherTO alterar(@RequestBody VoucherTO to) {
		return alterarCmd.alterar(to);
	}
	
	@DeleteMapping(path = "/{idVoucher}", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletar(@PathVariable(name = "idVoucher") Long idVoucher) {
		excluirCmd.excluir(idVoucher);
	}

	@PostMapping(path = "/lote", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletar(@RequestBody List<Long> ids) {
		excluirCmd.excluirEmLote(ids);
	}
	
	@PostMapping(path = "/exportar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<VoucherDTO> param) {
		return exportarVoucherCmd.gerar(param);
	}

}
