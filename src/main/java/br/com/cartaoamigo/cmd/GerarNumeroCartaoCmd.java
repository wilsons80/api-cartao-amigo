package br.com.cartaoamigo.cmd;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.infra.util.DataUtil;
import br.com.cartaoamigo.infra.util.StringUtil;

@Component
public class GerarNumeroCartaoCmd {
	
	public String getNumero(Long idPessoaFisica, boolean isTitular, String uf ) {
		StringBuilder numeroCartao = new StringBuilder();
		
		numeroCartao.append(isTitular ? "T" : "D");
		numeroCartao.append(uf.toUpperCase());
		numeroCartao.append(DataUtil.getAnoYY());
		
		Long numero = 2000 + idPessoaFisica; 
		numeroCartao.append(StringUtil.lpad(numero.toString(), "0", 6));
		
		return numeroCartao.toString();
	}
	
}
