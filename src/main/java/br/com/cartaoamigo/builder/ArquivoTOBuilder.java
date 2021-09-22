package br.com.cartaoamigo.builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.cartaoamigo.cmd.GetUsuarioLogadoCmd;
import br.com.cartaoamigo.entity.Arquivo;
import br.com.cartaoamigo.entity.ArquivosMetadados;
import br.com.cartaoamigo.exception.UploadArquivoException;
import br.com.cartaoamigo.infra.util.MD5Util;
import br.com.cartaoamigo.to.ArquivoTO;


@Component
public class ArquivoTOBuilder {
	
	@Autowired private ArquivosMetadadosTOBuilder arquivosMetadadosTOBuilderToBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	public ArquivoTO buildTO(Arquivo p) {
		ArquivoTO to = new ArquivoTO();
		
		BeanUtils.copyProperties(p,  to);
		
		to.setMetadados(arquivosMetadadosTOBuilderToBuilder.buildTO(p.getMetadados()));
		
		return to;
	}

	public Arquivo build(ArquivoTO p) {
		Arquivo to = new Arquivo();
		
		if (Objects.isNull(p)) {
			return to;
		}
		
		BeanUtils.copyProperties(p,  to);
		
		to.setMetadados(arquivosMetadadosTOBuilderToBuilder.build(p.getMetadados()));
		
		return to;
	}

	
	public Arquivo build(MultipartFile file) {
		Arquivo arquivo = new Arquivo();
		arquivo.setMetadados(new ArquivosMetadados());
		return build(file, arquivo);
	}

	public Arquivo build(MultipartFile file, Arquivo arquivo) {
		try {
			String hashArquivo = MD5Util.getHashArquivo(file.getBytes());

			arquivo.setBlob(file.getBytes());
			arquivo.getMetadados().setDtCriacao(LocalDateTime.now());
			arquivo.getMetadados().setHash(hashArquivo);
			arquivo.getMetadados().setNmArquivo(file.getOriginalFilename());
			arquivo.getMetadados().setNrTamanhoArquivo(file.getSize());
			arquivo.getMetadados().setDsTipoArquivo(file.getContentType());

			Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
			arquivo.getMetadados().setUsuarioAlteracao(idUsuarioLogado);

			return arquivo;

		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadArquivoException("Erro ao gravar o arquivo. " + e.getMessage());
		}
	}
	
	public List<Arquivo> buildTOAll(List<ArquivoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<ArquivoTO> buildAll(List<Arquivo> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
