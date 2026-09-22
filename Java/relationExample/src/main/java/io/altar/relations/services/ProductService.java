package io.altar.relations.services;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import io.altar.relations.models.Product;
import io.altar.relations.repositories.ProductRepository;

@RequestScoped
public class ProductService extends EntityService<ProductRepository, Product> {

	public Collection<Product> getAll() {
		return repository.getAll();
	}
}
