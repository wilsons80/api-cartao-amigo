package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.VoucherDTO;
import br.com.cartaoamigo.excel.ExportarVoucherExcelFileExporter;

@Component
public class ExportarVoucherCmd {

	@Autowired private ExportarVoucherExcelFileExporter excelFileExporter;

	public byte[] gerar(List<VoucherDTO> dados) {
		return excelFileExporter.gerar(dados);
	}


}
