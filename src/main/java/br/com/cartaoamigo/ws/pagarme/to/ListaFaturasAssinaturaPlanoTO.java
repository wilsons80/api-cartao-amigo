package br.com.cartaoamigo.ws.pagarme.to;

import java.util.List;

public class ListaFaturasAssinaturaPlanoTO {
	
	private List<FaturaAssinaturaPlanoTO> data;


	public ListaFaturasAssinaturaPlanoTO() {
	}


	public List<FaturaAssinaturaPlanoTO> getData() {
		return data;
	}


	public void setData(List<FaturaAssinaturaPlanoTO> data) {
		this.data = data;
	}

}
