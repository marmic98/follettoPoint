package model;

import java.util.ArrayList;
import java.util.List;

public class CartBean {

	private List<ProductBean> products;
	
	public CartBean() {
		products = new ArrayList<ProductBean>();
	}
	
	public void addProduct(ProductBean product) {
		for(ProductBean prod : products) {
			if(prod.getCode() == product.getCode()) {
				prod.setQuantity(prod.getQuantity()+1);
				return;
			}
		}
		products.add(product);
	}
	
	public void deleteProduct(ProductBean product) {
		for(ProductBean prod : products) {
			if(prod.getCode() == product.getCode()) {
				prod.setQuantity(prod.getQuantity()-1);
				if (prod.getQuantity() == 0)
					products.remove(prod);
				break;
			}
		}
 	}
	
	public List<ProductBean> getProducts() {
		return  products;
	}
}
