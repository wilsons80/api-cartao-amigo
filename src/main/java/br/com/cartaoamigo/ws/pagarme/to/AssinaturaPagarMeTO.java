package br.com.cartaoamigo.ws.pagarme.to;

import java.util.List;

public class AssinaturaPagarMeTO {

	private List<AssinaturaPlanoTO> data;

	public AssinaturaPagarMeTO() {
	}

	public List<AssinaturaPlanoTO> getData() {
		return data;
	}

	public void setData(List<AssinaturaPlanoTO> data) {
		this.data = data;
	}

}
