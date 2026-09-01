package io.altar.jseproject.model;

import java.util.ArrayList;
import java.util.List;

public class Product extends Entity_ {

	private List<Shelf> shelves = new ArrayList<Shelf>();
	private int discount;
	private int iva;
	private float pvp;

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

}
