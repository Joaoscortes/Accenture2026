package io.altar.jseproject.pratica2.model.dtos;

public class ShelfDTO extends EntityDTO {
	private int capacity;
	private long productId;
	private float dailyPrice;
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public float getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	
	
}
