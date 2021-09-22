package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cartaoamigo.cmd.S3ImagemCmd;
import br.com.cartaoamigo.infra.constantes.S3Context;
import br.com.cartaoamigo.to.ArquivoTO;

@RestController
@RequestMapping(value = "s3usuario")
public class S3UsuarioService {

	@Autowired
	private S3ImagemCmd s3ImagemCmd;

	@PostMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ArquivoTO salvar(@RequestParam("file") MultipartFile file, 
			                @RequestParam(name = "nomeFoto", required = false) String nomeFoto,
                            @PathVariable(name = "id") Long id) {
		return s3ImagemCmd.salvarArquivoProfile(file,id, nomeFoto);
	}

	@GetMapping(path = "/{id}")
	public byte[] buscar(@PathVariable(name = "id") Long id) {
		return s3ImagemCmd.getFotoUsuarioS3(id);
	}

	@DeleteMapping(path = "/{id}")
	public void deletar(@PathVariable(name = "id") Long id) {
		s3ImagemCmd.deletar(S3Context.PROFILE, id);
	}
	
}
