package br.com.cartaoamigo.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.VoucherDTO;
import br.com.cartaoamigo.exception.base.NegocioException;
import br.com.cartaoamigo.infra.util.Java8DateUtil;
import br.com.cartaoamigo.infra.util.NumeroUtil;

@Component
public class ExportarVoucherExcelFileExporter {
	
	private List<String> colunas = Arrays.asList("Código","Promoção", "Porcentagem", "Data Criação", "Data Validade", "Ativo", "Utilizado", "Data Utilização", "Quem utilizou", "Quantidade Meses Desconto");
		
	public byte[] gerar(List<VoucherDTO> dados) {
		ByteArrayInputStream stream = gerarFileExcel(dados);
        byte[] targetArray = new byte[stream.available()];
        try {
			stream.read(targetArray);
		} catch (IOException e) {
			throw new NegocioException	(e.getMessage());
		}
        return targetArray;
	}
	
	private ByteArrayInputStream gerarFileExcel(List<VoucherDTO> dados) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Vouchers");
		
			Row row = sheet.createRow(0);
			
			AtomicInteger indexColuna = new AtomicInteger(0);
			colunas.stream().forEach(coluna -> {
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				Cell cell = row.createCell(indexColuna.getAndIncrement());
				cell.setCellValue(coluna);
				cell.setCellStyle(headerCellStyle);
			});
			
			
	        
	        for(int i = 0; i < dados.size(); i++) {
	        	AtomicInteger indexDados = new AtomicInteger(0);
	        	Row dataRow = sheet.createRow(i + 1);
	        	
	        	VoucherDTO voucher = dados.get(i);

	        	//Preenche as colunas
          	  	preencherDadosPessoaisAluno(indexDados, dataRow, voucher);
                
	        }
	        
	        for (int i = 0; i < indexColuna.get(); i++) {
	        	sheet.autoSizeColumn(i);
			}
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new NegocioException("Erro ao exportar os vouchers: " + ex.getMessage());
		}
	}

	private void preencherDadosPessoaisAluno(AtomicInteger indexDados, Row dataRow, VoucherDTO voucher) {
		
		colunas.stream().forEach(coluna -> {
			if(coluna.equals("Código")) {
	      	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(voucher.getCodigo()).orElse(""));
	        }

			if(coluna.equals("Promoção")) {
	      	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(voucher.getDescricaoPromocao()).orElse(""));
	        }

			if(coluna.equals("Porcentagem")) {
	      	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(NumeroUtil.formataDoubleComDuasCasasDecimais(voucher.getPorcentagem())).orElse(""));
	        }

			if(coluna.equals("Data Criação")) {
	      	  if(Objects.nonNull(voucher.getDataCriacao())) {
	      		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(voucher.getDataCriacao().toLocalDate()));
	      	  }else {
	      		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	      	  }
	        }
			
			if(coluna.equals("Data Validade")) {
	      	  if(Objects.nonNull(voucher.getDataValidade())) {
	      		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(voucher.getDataValidade().toLocalDate()));
	      	  }else {
	      		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	      	  }
	        }			

			if(coluna.equals("Ativo")) {
			  String ativo = Objects.nonNull(voucher.getAtivo()) && voucher.getAtivo() ? "Sim" : "Não"; 	
	      	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(ativo);
	        }			

			if(coluna.equals("Utilizado")) {
			  String utilizado = Objects.nonNull(voucher.getUtilizado()) && voucher.getUtilizado() ? "Sim" : "Não";
	      	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(utilizado);
	        }			

			if(coluna.equals("Data Utilização")) {
	      	  if(Objects.nonNull(voucher.getDataUtilizacao())) {
	      		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(voucher.getDataUtilizacao().toLocalDate()));
	      	  }else {
	      		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	      	  }
	        }			

			if(coluna.equals("Quem utilizou")) {
	      	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(voucher.getNomePessoaUlilizacao()).orElse(""));
	        }	
			
			if(coluna.equals("Quantidade Meses Desconto")) {
				if(Objects.nonNull(voucher.getQtdMesesDesconto())) {
					dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Optional.ofNullable(String.valueOf(voucher.getQtdMesesDesconto())).orElse(""));
				} else {
		      		dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
		      	}
		    }
			
		});
		
	}
		
	
}
