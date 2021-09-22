package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.to.VoucherTO;

@Component
public class VoucherTOBuilder {
	
	public VoucherTO buildTO(Voucher p) {
		VoucherTO to = new VoucherTO();
		BeanUtils.copyProperties(p, to);	
		
		return to;
	}
	
	public Voucher build(VoucherTO p) {
		Voucher to = new Voucher();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public List<Voucher> buildTOAll(List<VoucherTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<VoucherTO> buildAll(List<Voucher> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
		
	}

}
