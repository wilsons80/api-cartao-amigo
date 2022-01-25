package br.com.cartaoamigo.rule;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.exception.VoucherInvalidoException;

@Component
public class VoucherAtivoRule {
	
	public void verificar(Voucher voucher) {
		if(!voucher.getAtivo()) {
			throw new VoucherInvalidoException("O código do cupom não está mais ativo.");
		}
		
		if(voucher.getPorcentagem() > 100) {
			throw new VoucherInvalidoException("O voucher não pode ser maior que 100%.");
		}
		
		if(voucher.getDataValidade().toLocalDate().isBefore(LocalDate.now())) {
			throw new VoucherInvalidoException("O código do cupom já venceu e não pode mais ser utilizado.");
		}

	}
}
