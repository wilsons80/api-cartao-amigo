package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "paymentMethods")
public class MetodosPagamentoTO {
	
	private List<PaymentMethodsTO> paymentMethod;
	
	public MetodosPagamentoTO() {
	}

	public List<PaymentMethodsTO> getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(List<PaymentMethodsTO> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
