package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class CondicaoParcelamentoTO {

	private Boolean error;
	private List<ParcelasBandeiraTO> parcelas;
	
	public CondicaoParcelamentoTO() {
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public List<ParcelasBandeiraTO> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelasBandeiraTO> parcelas) {
		this.parcelas = parcelas;
	}
	
}
