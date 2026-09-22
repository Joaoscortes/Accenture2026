package io.altar.jseproject.pratica2.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jseproject.pratica2.models.entities.Product;
import io.altar.jseproject.pratica2.models.entities.Shelf;
import io.altar.jseproject.pratica2.repositories.ProductRepository;

@ApplicationScoped
public class ProductService extends EntityService<ProductRepository, Product> {

	@Inject
	ShelfService SHELF_SERVICE;

	public String getClassName() {
		return "Product";
	}

	@Override
	public Product add(Product product) {
		Product current = repo.add(product);
		if (product.getShelves() != null && product.getShelves().size() > 0) {
			SHELF_SERVICE.updateProductOnShelfs(product, new ArrayList<Long>(),
					product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()));
		}
		return current;
	}

	@Override
	public void edit(Product product) {
		Product oldProduct = validEntity(product.getId());
		product = validShelvesOnProduct(product);
		if (!oldProduct.getShelves().equals(product.getShelves())) {
			SHELF_SERVICE.updateProductOnShelfs(product,
					oldProduct.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()),
					product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()));
		}
		repo.edit(product);
	}

	public void remove(long id) {
		Product product = repo.get(id);
		repo.remove(id);
		SHELF_SERVICE.updateProductOnShelfs(product,
				product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()), new ArrayList<Long>());
	}

	public void updateShelfOnProduct(long productId, long shelfId) {
		if (productId > 0 && shelfId > 0) {
			Product product = repo.get(productId);
			Shelf shelf = SHELF_SERVICE.get(shelfId);
			product.getShelves().remove(shelf);
			repo.edit(product);
		}
	}

	public List<Long> getShelfIdsByProductId(long productId) {
		return SHELF_SERVICE.getShelfIdsByProductId(productId);
	}

	public Product validShelvesOnProduct(Product product) {
		List<Shelf> shelves = product.getShelves();
		List<Long> validShelvesIds = SHELF_SERVICE.getShelfIdsByProductId((long) 0);
		validShelvesIds.addAll(SHELF_SERVICE.getShelfIdsByProductId(product.getId()));
		product.setShelves(shelves.stream().filter(shelf -> validShelvesIds.indexOf(shelf.getId()) >= 0)
				.collect(Collectors.toList()));
		return product;
	}

	public Collection<Product> requestFilter(Integer iva, Integer discount, String sortBy) {
		return repo.requestFilter(iva, discount, sortBy);
	}
}
