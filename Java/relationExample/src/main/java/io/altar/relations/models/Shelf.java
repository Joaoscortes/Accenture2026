package io.altar.relations.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Shelf extends Entity_ {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	private int capacity;
	private float dailyPrice;

	public static String getName() {
		return "Shelf";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public float getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	@Override
	public String toString() {
		long productId = (product == null) ? 0 : product.getId();
		return "Shelf [product=" + productId + ", capacity=" + capacity + ", dailyPrice=" + dailyPrice + "]";
	}
}
