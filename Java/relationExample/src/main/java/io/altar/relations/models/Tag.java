package io.altar.relations.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Tag extends Entity_ {

	private static final long serialVersionUID = 1L;

	private String name;
	@ManyToMany(mappedBy = "tags")
	private Set<Product> products = new HashSet<>();

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [products=" + products.stream().map(product -> product.getId()) + ", name=" + name + "]";
	}
}
