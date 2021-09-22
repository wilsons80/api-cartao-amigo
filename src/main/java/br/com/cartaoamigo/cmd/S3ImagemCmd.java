package br.com.cartaoamigo.cmd;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.cartaoamigo.builder.ArquivoTOBuilder;
import br.com.cartaoamigo.entity.Arquivo;
import br.com.cartaoamigo.exception.ArquivoNaoSalvoException;
import br.com.cartaoamigo.infra.util.MD5Util;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.service.exception.FileException;
import br.com.cartaoamigo.to.ArquivoTO;
import br.com.cartaoamigo.to.ArquivosMetadadosTO;
import br.com.cartaoamigo.to.PessoaFisicaTO;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class S3ImagemCmd {

	@Autowired private S3Cmd s3Cmd;
	@Autowired private ImageCmd imageCmd;
	@Autowired private SalvarArquivoCmd salvarArquivoCmd;
	@Autowired private ArquivoTOBuilder arquivoTOBuilder;
	@Autowired private GetArquivosCmd getArquivosCmd;
	@Autowired private ExcluirArquivoCmd excluirArquivoCmd;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	@Autowired private GetUsuarioCmd getUsuarioCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	
	public ArquivoTO salvarArquivoProfile(MultipartFile file, Long idUsuario, String nomeFoto) {
		try {
			Arquivo arquivo     = null;
			ArquivoTO arquivoTO = null;
			
			ArquivoTO foto = buildArquivoTO(file);
			
			UsuariosTO usuarioTO = getUsuarioCmd.getTOById(idUsuario);
			PessoaFisicaTO pessoaFisicaTO = usuarioTO.getPessoaFisica();
			
			if(Objects.isNull(pessoaFisicaTO.getIdFoto())) {
				arquivo = salvarArquivo(foto);
			} else {
				arquivoTO = getArquivosCmd.getTOById(pessoaFisicaTO.getIdFoto());
				if (!arquivoTO.getMetadados().getHash().equals(foto.getMetadados().getHash())) {
					arquivo = arquivoTOBuilder.build(arquivoTO);
				}
			}
			
			// Salva o arquivo no S3 apenas no cadastro e alteração da foto
			if(arquivo != null) {
				BufferedImage jpgImage = imageCmd.getJpgImageFromFile(file);
				jpgImage = imageCmd.cropSquare(jpgImage);
				jpgImage = imageCmd.resize(jpgImage, size);
				
				String fileName = getNomeArquivo(nomeFoto, idUsuario);
				URI uri = s3Cmd.uploadFile(imageCmd.getInputStream(jpgImage, "jpg"), fileName, "image");
				
				arquivo.setUrl(uri.toString());
				arquivo.getMetadados().setNmArquivo(fileName);
				
				pessoaFisicaTO.setIdFoto(arquivo.getId());
				alterarPessoaFisicaCmd.alterar(pessoaFisicaTO);	
				
				return arquivoTOBuilder.buildTO(arquivo);
			}
			
			return arquivoTO;
			
		} catch (Exception e) {
			throw new ArquivoNaoSalvoException("Erro ao salvar o arquivo no S3.");
		}
	}



	private String getNomeArquivo(String nomeFoto, Long idArquivo) {
		StringBuilder nome = new StringBuilder(prefix);
		
		if(StringUtils.isNotEmpty(nomeFoto)) {
			nome.append("_");
			nome.append(StringUtil.removerAcentos(nomeFoto.replaceAll(" ", "_")).toLowerCase() ); 
		}
		nome.append("_");
		nome.append(idArquivo);
		nome.append(".jpg");
		
		return nome.toString();
	}


	private Arquivo salvarArquivo(ArquivoTO to) throws IOException {
		Arquivo arquivo = salvarArquivoCmd.salvar(to);
		return arquivo;
	}


	private ArquivoTO buildArquivoTO(MultipartFile file) throws IOException {
		ArquivoTO to = new ArquivoTO();
		to.setUrl(null);
		
		ArquivosMetadadosTO metadados = new ArquivosMetadadosTO();
		metadados.setNmArquivo(file.getName());
		metadados.setDtCriacao(LocalDateTime.now());
		metadados.setHash(MD5Util.getHashArquivo(file.getBytes()));
		metadados.setNrTamanhoArquivo(file.getSize());
		metadados.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		metadados.setDsTipoArquivo(FilenameUtils.getExtension(file.getName()));
		
		to.setMetadados(metadados);
		
		return to;
	}
	
	public byte[] getFotoUsuarioS3(Long idUsuario) {
		UsuariosTO usuarioTO = getUsuarioCmd.getTOById(idUsuario);
		if(Objects.nonNull(usuarioTO.getPessoaFisica().getIdFoto())) {
			return getBytesS3(usuarioTO.getPessoaFisica().getIdFoto());
		}
		return null;
	}
	
	public byte[] getBytesS3(Long idArquivo) {
		try {
			Optional<Arquivo> arquivo = getArquivosCmd.getById(idArquivo);
			if(arquivo.isPresent()) {
				return s3Cmd.getArquivoS3(arquivo.get().getMetadados().getNmArquivo());
			}
			return null;
		} catch (Exception e) {
			throw new FileException("Erro ao recuperar o arquivo do S3.");
		}
	}
	
	
	public void deletar(String nomeTabela, Long idArquivo) {
		try {
			Optional<Arquivo> arquivoOptional = getArquivosCmd.getById(idArquivo);
			if(arquivoOptional.isPresent()) {
				Arquivo arquivo = arquivoOptional.get();
				
				s3Cmd.deletarArquivoS3(arquivo.getMetadados().getNmArquivo());
				excluirArquivoCmd.excluir(arquivo.getId());
			}
			
		} catch (Exception e) {
			throw new FileException("Erro ao excluir o arquivo.");
		}
	}
	
	
}
