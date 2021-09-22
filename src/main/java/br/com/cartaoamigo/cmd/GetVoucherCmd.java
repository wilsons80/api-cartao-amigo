package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.VoucherTOBuilder;
import br.com.cartaoamigo.dao.GetVoucherDao;
import br.com.cartaoamigo.dao.dto.VoucherDTO;
import br.com.cartaoamigo.dao.repository.VoucherRepository;
import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.to.VoucherTO;

@Component
public class GetVoucherCmd {

	@Autowired private VoucherRepository voucherRepository;
	@Autowired private VoucherTOBuilder voucherTOBuilder;
	@Autowired private GetVoucherDao getVoucherDao;
	
	
	public VoucherTO getById(Long idVoucher) {
		Optional<Voucher> entity = voucherRepository.findById(idVoucher);
		if(!entity.isPresent()) {return null;}
		return voucherTOBuilder.buildTO(entity.get());
	}
	
	public VoucherTO getByCodigo(String codigo) {
		Optional<Voucher> entity = voucherRepository.findByCodigo(codigo);
		if(!entity.isPresent()) {return null;}
		return voucherTOBuilder.buildTO(entity.get());
	}
	
	public List<VoucherDTO> getAllFilter(String codigo, String ativo, String utilizado, LocalDate dataInicioGerado, LocalDate dataFimGerado) {
		List<VoucherDTO> vouchers = getVoucherDao.getAllFilter(codigo, ativo, utilizado, dataInicioGerado, dataFimGerado);
		if(vouchers.isEmpty()) {return null;}
		return vouchers;
	}
	
	
}
