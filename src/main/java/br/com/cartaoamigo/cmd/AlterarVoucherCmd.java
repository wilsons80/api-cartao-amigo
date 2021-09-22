package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.VoucherTOBuilder;
import br.com.cartaoamigo.dao.repository.VoucherRepository;
import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.VoucherTO;

@Component
public class AlterarVoucherCmd {

	@Autowired private VoucherRepository repository;
	@Autowired private VoucherTOBuilder toBuilder;

	public VoucherTO alterar(VoucherTO to) {
		Voucher entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Voucher informada não existe: " + to.getId()));
		entity = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(entity));
	}

}
