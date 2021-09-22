package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ItemsSplitTO {
	
	private List<ItemSplitTO> item;

	public ItemsSplitTO() {
	}

	public List<ItemSplitTO> getItem() {
		return item;
	}

	public void setItem(List<ItemSplitTO> item) {
		this.item = item;
	}
	
	

}
