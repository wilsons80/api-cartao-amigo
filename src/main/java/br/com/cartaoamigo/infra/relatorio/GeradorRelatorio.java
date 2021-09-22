package br.com.cartaoamigo.infra.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetUsuarioLogadoCmd;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Component
public class GeradorRelatorio {
	
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	/**
	 * 
	 * @param parametros
	 * @param dados
	 * @param nomeRelatorio
	 * @param pathRelatorio
	 * @param tipo (pdf, xls)
	 * @return
	 * @throws JRException
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes" })
	public byte[] gerar(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, String[] path, String mimeType) throws Exception {
		TipoRelatorio tipoRelatorio = TipoRelatorio.getPorTipo(mimeType);	
		
		String pathCompleto = String.join(File.separator, path);
		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		parametros.put("P_PATH_ROOT", "relatorios"+File.separator+path[0]);
		parametros.put("P_NOME_USUARIO_LOGADO", getUsuarioLogadoCmd.getUsuarioLogado().getNomeUsuario());
		parametros.put("REPORT_LOCALE", new Locale("pt","BR"));
		parametros.put("REPORT_TIME_ZONE", TimeZone.getTimeZone("America/Sao_Paulo"));
		
		byte[] toReturn = null;
		Exporter exporter = null;		
		switch (tipoRelatorio) {
		case PDF:
			exporter = new JRPdfExporter();
			toReturn = gerarRelatorioPDF(parametros, dados, nomeRelatorio, exporter, pathCompleto);
			break;
		case XLS:
			exporter = new JRXlsxExporter();
			toReturn = gerarRelatorioExcel(parametros, dados, nomeRelatorio, pathCompleto);
			break;
		default:
			throw new Exception("Não é possível gerar o tipo de relatório informado (" + tipoRelatorio + "). Suportado apenas:[1-PDF, 2-XLS]");
		}
				
		return toReturn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private byte[] gerarRelatorioPDF(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, Exporter exporter, String pathCompleto) throws IOException, JRException {
		final Resource fileResource = resourceLoader.getResource("classpath:relatorios" + File.separator + pathCompleto + File.separator +nomeRelatorio+".jasper");
		InputStream jasperStream = fileResource.getInputStream();
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dados);
		
		DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance(); 
		JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory"); 
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanColDataSource);
		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		
		ByteArrayOutputStream baosReport = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baosReport));
		exporter.exportReport();
		
		byte[] toReturn = baosReport.toByteArray();
		baosReport.close();
		return toReturn;
	}
	
	
	private byte[] gerarRelatorioExcel(Map<String, Object> parametros, List<?> dados, String nomeRelatorio, String pathCompleto) throws IOException, JRException {
		parametros.put("IS_IGNORE_PAGINATION", true);		
		
		final Resource fileResource = resourceLoader.getResource("classpath:relatorios" + File.separator + pathCompleto + File.separator +nomeRelatorio+".jasper");
		InputStream jasperStream = fileResource.getInputStream();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dados);
		
		DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance(); 
		JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory", "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory"); 
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, beanColDataSource);
		
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
		JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[] { "dados" });
        reportConfigXLS.setRemoveEmptySpaceBetweenRows(true);
        reportConfigXLS.setForcePageBreaks(false);
        reportConfigXLS.setWrapText(false);
        reportConfigXLS.setCollapseRowSpan(true);
        exporter.setConfiguration(reportConfigXLS);
        
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));        
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
       
        exporter.exportReport();
        return xlsReport.toByteArray();
	}
	
}
