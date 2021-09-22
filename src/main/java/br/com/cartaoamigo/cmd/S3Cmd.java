package br.com.cartaoamigo.cmd;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import br.com.cartaoamigo.service.exception.FileException;

@Component
public class S3Cmd {

	//private Logger LOG = LoggerFactory.getLogger(S3Cmd.class);

	@Autowired
	private AmazonS3Client s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			s3client.putObject(bucketName, fileName, is, meta);
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}
	
	public byte[] getArquivoS3(String fileName) {
		try {
			S3Object arquivo = s3client.getObject(bucketName, fileName);
			byte[] byteArray = IOUtils.toByteArray(arquivo.getObjectContent());
			return byteArray;
		} catch (Exception e) {
			throw new FileException("Erro ao recuperar o arquivo no S3: " + fileName);
		}
	}
	
	
	public void deletarArquivoS3(String fileName) {
		try {
			s3client.deleteObject(bucketName, fileName);
		} catch (Exception e) {
			throw new FileException("Erro ao apagar o arquivo no S3: " + fileName);
		}
	}
	

}