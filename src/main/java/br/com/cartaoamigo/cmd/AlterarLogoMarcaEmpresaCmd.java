package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AlterarLogoMarcaEmpresaCmd {

	@Autowired private GravarArquivoEmpresaCmd gravarLogoMarcaEmpresaCmd;

	public void alterar(MultipartFile file) {
		gravarLogoMarcaEmpresaCmd.gravar(file);
	}
}
