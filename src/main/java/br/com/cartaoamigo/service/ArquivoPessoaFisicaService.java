package br.com.cartaoamigo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cartaoamigo.cmd.ArquivoPessoaFisicaCmd;

@RestController
@RequestMapping(value = "arquivopessoafisica")
public class ArquivoPessoaFisicaService {

	@Autowired
	private ArquivoPessoaFisicaCmd arquivoPessoaFisicaCmd;
	
	@PostMapping(path = "/{idPessoaFisica}")
	@Transactional
	public void gravar(@RequestParam(name = "file") MultipartFile file, @PathVariable(name = "idPessoaFisica") Long idPessoaFisica ) {
		arquivoPessoaFisicaCmd.salvar(file, idPessoaFisica);
	}
	
	@PutMapping(path = "/{idPessoaFisica}")
	@Transactional
	public void alterar(@RequestParam(name = "file") MultipartFile file, @PathVariable(name = "idPessoaFisica") Long idPessoaFisica) {
		arquivoPessoaFisicaCmd.alterar(file, idPessoaFisica);
	}
	
	@GetMapping(path = "/{idPessoaFisica}")
	public byte[] getPorPessoa(@PathVariable(name = "idPessoaFisica") Long idPessoaFisica) {
		return arquivoPessoaFisicaCmd.getPorPessoa(idPessoaFisica);
	}	
	
}
