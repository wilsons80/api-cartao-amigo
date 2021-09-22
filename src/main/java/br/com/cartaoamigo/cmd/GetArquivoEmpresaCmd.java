package br.com.cartaoamigo.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Arquivo;
import br.com.cartaoamigo.entity.Empresa;
import br.com.cartaoamigo.exception.NotFoundException;

@Component
public class GetArquivoEmpresaCmd {

	@Autowired private GetArquivosCmd getArquivosCmd;
	@Autowired private GetEmpresaCmd empresaCmd;
	
	public byte[] get() {
		Empresa empresa = empresaCmd.get();
		
		if(Objects.isNull(empresa)) {
			throw new NotFoundException("Nâo foi possível recuperar os dados da empresa.");
		}

		if (empresa.getMetadados() == null || empresa.getMetadados().getId() == null) {
			return null;
		}

		Optional<Arquivo> arquivo = getArquivosCmd.getByIdMetadados(empresa.getMetadados().getId());
		if (!arquivo.isPresent()) {
			return null;
		}

		return arquivo.get().getBlob();
	}
}
