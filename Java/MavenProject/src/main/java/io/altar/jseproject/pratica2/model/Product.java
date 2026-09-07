package io.altar.jseproject.pratica2.model;

import java.util.List;

public class Product extends Entity_ {

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

	public Product(List<Shelf> shelves, int discount, int iva, float pvp) {
		this.shelves = shelves;
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

	public void removeShelf(Shelf shelf) {
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
		this.iva = iva;
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
