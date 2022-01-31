package br.com.cartaoamigo.ws.pagarme.to;

public class BandeiraCartaoTO {

	private String brand;
	private String brandName;
	private String brandImage;

	public BandeiraCartaoTO() {
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandImage() {
		return brandImage;
	}

	public void setBrandImage(String brandImage) {
		this.brandImage = brandImage;
	}

	@Override
	public String toString() {
		return "BandeiraCartaoTO [brand=" + brand + ", brandName=" + brandName + ", brandImage=" + brandImage + "]";
	}
	

}
