package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class OpcoesPagamentoTO {

	private List<OptionPagamentoTO> option;
	
	public OpcoesPagamentoTO() {
	}

	public List<OptionPagamentoTO> getOption() {
		return option;
	}

	public void setOption(List<OptionPagamentoTO> option) {
		this.option = option;
	}
		
}
