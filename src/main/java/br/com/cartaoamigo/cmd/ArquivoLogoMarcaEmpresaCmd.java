package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoLogoMarcaEmpresaCmd {

	@Autowired private GetArquivoEmpresaCmd getArquivoCmd;
	@Autowired private AlterarLogoMarcaEmpresaCmd alterarArquivoCmd;
	@Autowired private GravarArquivoEmpresaCmd gravarArquivoEmpresaCmd;
	
	public void salvar(MultipartFile file) {
		gravarArquivoEmpresaCmd.gravar(file);
	}

	public byte[] getArquivoLogomarca() {
		return getArquivoCmd.get();
	}
	
	public void alterarArquivoUnidade(MultipartFile file) {
		alterarArquivoCmd.alterar(file);
	}
	


}
