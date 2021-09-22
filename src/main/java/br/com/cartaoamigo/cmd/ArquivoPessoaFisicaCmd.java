package br.com.cartaoamigo.cmd;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoPessoaFisicaCmd {


	public void salvar(MultipartFile file, Long idPessoaFisica) {
	}


	public void alterar(MultipartFile file, Long idPessoaFisica) {
		
	}
	
	public byte[] getPorPessoa(Long idPessoaFisica) {
		return null;
	}
}
