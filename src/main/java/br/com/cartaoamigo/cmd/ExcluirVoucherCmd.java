package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.VoucherRepository;
import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.VoucherException;

@Component
public class ExcluirVoucherCmd {

	@Autowired private VoucherRepository repository;

	public void excluir(Long idVoucher) {

		Optional<Voucher> voucher = repository.findById(idVoucher);
		if (!voucher.isPresent()) {
			throw new NotFoundException("Voucher informado não existe em nossa base de dados.");
		}

		if (voucher.get().getUtilizado()) {
			throw new VoucherException("Não é possível excluir, pois esse voucher já foi utilizado.");
		}

		repository.delete(voucher.get());
	}

	
	public void excluirEmLote(List<Long> listaIds) {
		if(Objects.nonNull(listaIds) && !listaIds.isEmpty()) {
			listaIds.forEach(idVoucher -> excluir(idVoucher));
		}
	}
}
