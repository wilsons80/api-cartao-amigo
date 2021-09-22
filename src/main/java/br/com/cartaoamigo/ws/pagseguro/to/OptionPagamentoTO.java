package br.com.cartaoamigo.ws.pagseguro.to;

public class OptionPagamentoTO {

	private String code;
	private String name;
	private String displayName;
	private String status;
	private ImagensTO images;
	
	public OptionPagamentoTO() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ImagensTO getImages() {
		return images;
	}

	public void setImages(ImagensTO images) {
		this.images = images;
	}

	
		
}
