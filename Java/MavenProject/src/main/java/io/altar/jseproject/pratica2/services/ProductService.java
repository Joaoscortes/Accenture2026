package io.altar.jseproject.pratica2.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jseproject.pratica2.model.Product;
import io.altar.jseproject.pratica2.model.Shelf;
import io.altar.jseproject.pratica2.repositories.ProductRepository;

@ApplicationScoped
public class ProductService extends EntityService<ProductRepository, Product> {

	@Inject
	ShelfService ss;

	@Override
	public void edit(Product p) {
		repo.edit(p);

	}

	public List<Shelf> getAllShelvesFromProductId(long id) {
		return ss.getAllShelvesFromProductId(id);
	}
}
