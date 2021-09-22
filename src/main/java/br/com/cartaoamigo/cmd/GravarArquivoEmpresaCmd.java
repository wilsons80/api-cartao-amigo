package br.com.cartaoamigo.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.cartaoamigo.builder.ArquivoTOBuilder;
import br.com.cartaoamigo.dao.repository.ArquivoRepository;
import br.com.cartaoamigo.dao.repository.ArquivosMetadadosRepository;
import br.com.cartaoamigo.dao.repository.EmpresaRepository;
import br.com.cartaoamigo.entity.Arquivo;
import br.com.cartaoamigo.entity.ArquivosMetadados;
import br.com.cartaoamigo.entity.Empresa;

@Component
public class GravarArquivoEmpresaCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ArquivoTOBuilder arquivoBuilder;
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private EmpresaRepository empresaRepository ;
	@Autowired private ArquivosMetadadosRepository arquivosMetadadosRepository;
	@Autowired private GetArquivosCmd getArquivosCmd;
	
	public void gravar(MultipartFile file) {
		Empresa empresa = empresaRepository.findAll().stream().findAny().get();
		
		Arquivo arquivo = null;
		ArquivosMetadados metadados = empresa.getMetadados();
		
		if (metadados != null) {
			Optional<Arquivo> optionalArquivo = getArquivosCmd.getByIdMetadados(metadados.getId());
			arquivo = arquivoBuilder.build(file, optionalArquivo.get());
		} else {
			arquivo = arquivoBuilder.build(file);
			Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
			arquivo.getMetadados().setUsuarioAlteracao(idUsuarioLogado);
		}
		
		arquivo = arquivoRepository.save(arquivo);
		arquivosMetadadosRepository.save(arquivo.getMetadados());
		
		empresa.setMetadados(arquivo.getMetadados());
		empresaRepository.save(empresa);

	}
}
