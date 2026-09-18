package io.altar.jseproject.pratica2.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = Product.GET_ALL_PRODUCTS, query = "SELECT p FROM Product p"),
		@NamedQuery(name = Product.GET_ALL_PRODUCTS_IDS, query = "SELECT p.id FROM Product p"),
		@NamedQuery(name = Product.GET_PRODUCTS_COUNT, query = "SELECT COUNT(p.id) FROM Product p") })
public class Product extends Entity_ {
	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_PRODUCTS = "getAllProducts";
	public static final String GET_ALL_PRODUCTS_IDS = "getAllProductsIds";
	public static final String GET_PRODUCTS_COUNT = "getProductsCount";

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Shelf> shelves;
	private int discount;
	private int iva;
	private float pvp;

	public Product() {
	}

	public Product(int discount, int iva, float pvp) {
		this.discount = discount;
		this.iva = iva;
		this.pvp = pvp;
	}

	public Product(List<Shelf> shelvesIds, int discount, int iva, float pvp) {
		this.shelves = shelvesIds;
		this.discount = discount;
		this.iva = iva;
		this.pvp = pvp;
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}

	public void addShelf(Shelf shelf) {
		this.shelves.add(shelf);
	}

	public void removeShelfId(Shelf shelf) {
		this.shelves.remove(shelf);
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		switch (iva) {
		case 23:
		case 6:
		case 11:
			this.iva = iva;
			break;

		default:
			throw new RuntimeException();
		}

	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.getId() + ", shelves=" + shelves + ", discount=" + discount + ", iva=" + iva
				+ ", pvp=" + pvp + "]";
	}

}