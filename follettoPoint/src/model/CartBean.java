package model;

import java.util.ArrayList;
import java.util.List;
import model.ProductCartBean;

public class CartBean {

	private List<ProductCartBean> products;
	
	public CartBean() {
		products = new ArrayList<ProductCartBean>();
	}
	
	public void addProduct(ProductBean product, int q) {
		for(ProductCartBean prod : products) {
			if(prod.getProduct().getCode() == product.getCode()) {
				prod.setQuantityCart(prod.getQuantityCart()+q);
				return;
				}
		}
		products.add(new ProductCartBean(product, q));
	}
	
	public void deleteProduct(ProductBean product) {
		for(ProductCartBean prod : products) {
			if(prod.getProduct().getCode() == product.getCode()) {
				prod.setQuantityCart(prod.getQuantityCart()-1);
				if (prod.getQuantityCart() == 0)
					products.remove(prod);
			break;
			}
		}
 	}
	
	public List<ProductCartBean> getProducts() {
		return  products;
	}
}
