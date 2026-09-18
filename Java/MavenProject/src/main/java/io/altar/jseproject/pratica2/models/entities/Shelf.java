package io.altar.jseproject.pratica2.models.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Shelf.GET_ALL_SHELVES, query = "SELECT e FROM Shelf e"),
		@NamedQuery(name = Shelf.GET_ALL_SHELVES_IDS, query = "SELECT e.id FROM Shelf e"),
		@NamedQuery(name = Shelf.GET_SHELVES_COUNT, query = "SELECT COUNT(p.id) FROM Shelf p"),
		@NamedQuery(name = Shelf.GET_SHELVES_BY_PRODUCT_ID, query = "SELECT s FROM Shelf s WHERE s.product.id = :productId"),
		@NamedQuery(name = Shelf.GET_EMPTY_SHELVES, query = "SELECT s FROM Shelf s WHERE s.product = null"),
		@NamedQuery(name = Shelf.SET_SHELVES_PRODUCT_TO_NULL, query = "UPDATE Shelf s SET s.product = null WHERE s.product.id = :productId") })
public class Shelf extends Entity_ {
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_SHELVES = "getAllShelves";
	public static final String GET_ALL_SHELVES_IDS = "getAllShelvesIds";
	public static final String GET_SHELVES_COUNT = "getShelvesCount";
	public static final String GET_SHELVES_BY_PRODUCT_ID = "getShelvesByProductId";
	public static final String GET_EMPTY_SHELVES = "getEmptyShelves";
	public static final String SET_SHELVES_PRODUCT_TO_NULL = "setShelvesProductToNullByProductId";

	private int capacity;
	@ManyToOne(fetch = FetchType.LAZY)
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
		return "Shelf [capacity=" + capacity + ", dailyPrice=" + dailyPrice + "]";
	}
}
