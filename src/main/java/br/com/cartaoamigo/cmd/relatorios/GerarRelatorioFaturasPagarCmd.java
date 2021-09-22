package br.com.cartaoamigo.cmd.relatorios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.SalvarImpressaoCartaoCmd;
import br.com.cartaoamigo.exception.RelatorioException;
import br.com.cartaoamigo.exception.base.NegocioException;
import br.com.cartaoamigo.infra.relatorio.GeradorRelatorio;
import br.com.cartaoamigo.infra.relatorio.TipoRelatorio;
import br.com.cartaoamigo.infra.util.DataUtil;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.to.relatorios.ImpressaoCartaoTO;
import net.lingala.zip4j.ZipFile;

@Component
public class GerarRelatorioFaturasPagarCmd {
	
	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private SalvarImpressaoCartaoCmd salvarImpressaoCartaoCmd;
	 
	private String urlLindQrcode = "https://chart.googleapis.com/chart?chf=bg,s,FFFFFF00&amp&chs=400x400&cht=qr&chl=";
	
	
	public byte[] gerarCartaBoasVindas(List<ImpressaoCartaoTO> dados)  {
		try {
			String nomeRelatorio = "Carta_Boas_Vindas";
			String[] path = {"carta_boas_vindas"};
			
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("P_LINK_QRCODE", urlLindQrcode);
			
			dados.forEach(d -> d.setNomeAssociado( StringUtil.initCap(d.getNomeAssociado())));
			
			byte[] arquivoPDF = geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, TipoRelatorio.PDF.getTipo());
			
			return arquivoPDF;
			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Erro ao gerar as cartas de boas vindas.");
		}
		
	}	
	
	public byte[] gerarPDF(List<ImpressaoCartaoTO> dados)  {
		try {
			String nomeRelatorio = "CartaoAmigo";
			String[] path = {"cartao"};
			
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("P_LINK_QRCODE", urlLindQrcode);
			
			byte[] arquivoPDF = geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, TipoRelatorio.PDF.getTipo());
			
			salvarImpressaoCartaoCmd.salvar(dados);
			
			return arquivoPDF;
			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Erro ao gerar o relatório de cartões.");
		}
		
	}	
	
	public byte[] gerarZip(List<ImpressaoCartaoTO> dados)  {
		try {
			String nomeRelatorio = "CartaoAmigo";
			String[] path = {"cartao"};
			
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("P_LINK_QRCODE", urlLindQrcode);
			
			ArrayList<File> arquivos = new ArrayList<File>();
			
			dados.forEach(arquivo -> {
				try {
					byte[] conteudo = geradorRelatorio.gerar(parametros, Arrays.asList(arquivo), nomeRelatorio, path, TipoRelatorio.PDF.getTipo());
					
					File pdf = new File(arquivo.getNumeroCartao() + ".pdf");
					FileUtils.writeByteArrayToFile(pdf, conteudo);
					arquivos.add(pdf);
			
				} catch (Exception e) {
					throw new RelatorioException(e.getMessage());
				}
			});
			
			byte[] arquivoZip = gerarArquivoZip(arquivos);
			
			salvarImpressaoCartaoCmd.salvar(dados);
			
			return arquivoZip;
			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
		
	}	
	
	
	private byte[] gerarArquivoZip(List<File> arquivos) throws IOException {
		String nomeArquivo = "cartaoamigo_" + DataUtil.getDataRelatorio() + ".zip";
		ZipFile zipFile = new ZipFile(nomeArquivo);
		
		zipFile.addFiles( arquivos );		
		File filezip = zipFile.getFile();
		
		byte[] byteZip = FileUtils.readFileToByteArray(filezip);
		
		return byteZip;
	}
	
}
