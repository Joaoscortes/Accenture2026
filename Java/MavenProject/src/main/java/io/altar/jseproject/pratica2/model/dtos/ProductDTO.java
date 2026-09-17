package io.altar.jseproject.pratica2.model.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.altar.jseproject.pratica2.model.Product;
import io.altar.jseproject.pratica2.model.Shelf;

public class ProductDTO extends EntityDTO {

	private List<Long> shelvesIds = new ArrayList<Long>();
	private int discount;
	private int iva;
	private float pvp;

	public List<Long> getShelvesIds() {
		return shelvesIds;
	}

	public void setShelvesIds(List<Long> shelves) {
		this.shelvesIds = shelves;
	}
	
	public void setShelves(List<Long> shelves) {
		this.shelvesIds = shelves;
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
		return "ProductDTO [shelvesIds=" + shelvesIds + ", discount=" + discount + ", iva=" + iva + ", pvp=" + pvp
				+ "]";
	}

	public Product toEntity() {
		Product product = new Product();
		if (this.getId() > 0) {
			product.setId(this.getId());
		}
		product.setDiscount(this.getDiscount());
		product.setIva(this.getIva());
		product.setPvp(this.getPvp());

		return product;
	}
	
}
