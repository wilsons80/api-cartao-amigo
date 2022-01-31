package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CarteiraCartaoPagamentoAssociadoRepository;
import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;

@Component
public class SalvarCarteiraCartaoPagamentoAssociadoCmd {
	
	@Autowired private CarteiraCartaoPagamentoAssociadoRepository carteiraCartaoPagamentoAssociadoRepository;
	@Autowired private GetCarteiraCartaoPagamentoAssociadoCmd getCarteiraCartaoPagamentoAssociadoCmd;
	
	public void salvar(CriarCartaoClienteTO cartaoClienteTO) {
		CarteiraCartaoPagamentoAssociado entity = getCarteiraCartaoPagamentoAssociadoCmd.getByIdCartaoPagarme(cartaoClienteTO.getId());
		
		if(Objects.isNull(entity)) {
			entity = new CarteiraCartaoPagamentoAssociado(); 
			entity.setDataCriacao      (LocalDateTime.now());
			entity.setIdCartaoPagarMe  (cartaoClienteTO.getId());
			entity.setIdClientePagarMe (cartaoClienteTO.getCustomer().getId());
			entity.setIdTitular        (cartaoClienteTO.getIdTitular());
		}	
		
		entity.setMesValidade      (String.valueOf(cartaoClienteTO.getExp_month()));
		entity.setAnoValidade      (String.valueOf(cartaoClienteTO.getExp_year()));
		entity.setBandeira         (cartaoClienteTO.getBrand());
		entity.setPrimeiros6digitos(cartaoClienteTO.getFirst_six_digits());
		entity.setUltimos4digitos  (cartaoClienteTO.getLast_four_digits());
		
		carteiraCartaoPagamentoAssociadoRepository.save(entity);
	}
	
	
}
