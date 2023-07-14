package model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int code;
	String name;
	String description;
	double price;
	
	double priceScontato;
	int quantity;
	int categoria;
	double sconto;
	double iva;

	public ProductBean() {
		code = -1;
		name = "";
		description = "";
		quantity = 0;
		categoria = 0;
		sconto = 0;
		iva = 0;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price2) {
		this.price = price2;
		if(sconto > 0) {
			
			price = (1-(sconto/100))*this.price;
		}
		if(iva > 0) {
			this.price = (1+(iva/100))*this.price;
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public int getCategoria() {
		return categoria;
	}
	
	public void setSconto(double sconto) {
		this.sconto = sconto;
	}
	
	public double getSconto() {
		return sconto;
	}
	
	public void setIva(double iva) {
		this.iva = iva;
	}
	
	public double getIva() {
		return iva;
	}
	
	public void setpriceScontato(double priceScontato) {
		this.priceScontato = priceScontato;
	}
	
	public double getpriceScontato() {
		return priceScontato;
	}
	
	@Override
	public String toString() {
		return name + " (" + code + "), " + price + " " + quantity + " " + description +"categoria: "+categoria+" iva: "+iva + " sconto: "+sconto;
	}

}
