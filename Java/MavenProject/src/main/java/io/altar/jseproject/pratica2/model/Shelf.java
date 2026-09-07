package io.altar.jseproject.pratica2.model;

public class Shelf extends Entity_ {
	private int capacity;
	private Product product;
	private float dailyPrice;

	public Shelf() {
	}

	public Shelf(int capacity, float dailyPrice) {
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
	}

	public Shelf(int capacity, Product product, float dailyPrice) {
		this.capacity = capacity;
		this.product = product;
		this.dailyPrice = dailyPrice;
	}

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
		return "Shelf [capacity=" + capacity + ", productId=" + product.getId() + ", dailyPrice=" + dailyPrice + "]";
	}
}
