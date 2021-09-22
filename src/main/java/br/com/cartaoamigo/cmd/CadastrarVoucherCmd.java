package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.VoucherTOBuilder;
import br.com.cartaoamigo.dao.repository.VoucherRepository;
import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.to.VoucherTO;

@Component
public class CadastrarVoucherCmd {
	
	@Autowired private VoucherRepository repository;
	@Autowired private VoucherTOBuilder toBuilder;
	
	public List<VoucherTO> salvar(List<VoucherTO> listaTO) {
		List<Voucher> listaEntity = toBuilder.buildTOAll(listaTO);
		listaEntity.forEach(v -> {
			try {
				v.setDataCriacao(LocalDateTime.now());
				v.setAtivo(true);
				
				Thread.sleep(1);
				v.setCodigo(String.valueOf(v.hashCode()));
				
			} catch (InterruptedException e) {
			}
		});
		
		listaEntity = repository.saveAll(listaEntity);
		return toBuilder.buildAll(listaEntity);
	}
	
}
