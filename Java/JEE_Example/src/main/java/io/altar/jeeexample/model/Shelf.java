package io.altar.jeeexample.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Shelf extends Entity_ {

	private static final long serialVersionUID = 1L;

	private int capacity;
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	private float dailyPrice;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	@Override
	public String toString() {
		return "Shelf [capacity=" + capacity + ", dailyPrice=" + dailyPrice + "]";
	}
}
