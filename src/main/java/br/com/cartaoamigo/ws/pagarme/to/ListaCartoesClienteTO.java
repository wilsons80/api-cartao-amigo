package br.com.cartaoamigo.ws.pagarme.to;

import java.util.List;

public class ListaCartoesClienteTO {
	
	private List<CartaoClienteTO> data;

	public ListaCartoesClienteTO() {
	}

	public List<CartaoClienteTO> getData() {
		return data;
	}

	public void setData(List<CartaoClienteTO> data) {
		this.data = data;
	}


}
