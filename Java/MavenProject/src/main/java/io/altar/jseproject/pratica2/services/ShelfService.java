package io.altar.jseproject.pratica2.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jseproject.pratica2.models.entities.Product;
import io.altar.jseproject.pratica2.models.entities.Shelf;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;

@ApplicationScoped
public class ShelfService extends EntityService<ShelfRepository, Shelf> {

	@Inject
	ProductService PRODUCT_SERVICE;

	public String getClassName() {
		return "Shelf";
	}

	public void remove(long id) {
		Shelf shelfToRemove = repo.get(id);
		if (shelfToRemove.getProduct() != null) {
			PRODUCT_SERVICE.updateShelfOnProduct(shelfToRemove.getProduct().getId(), id);
		}
		repo.remove(id);
	}

	public void updateProductOnShelfs(Product product, List<Long> shelfsOld, List<Long> shelfsNew) {
		for (Long shelfId : shelfsOld) {
			if (shelfId > 0) {
				Shelf shelf = repo.get(shelfId);
				if (shelfsNew.indexOf(shelfId) == -1) {
					shelf.setProduct(null);
					repo.edit(shelf);
				}
			}
		}
		for (Long shelfId : shelfsNew) {
			if (shelfId > 0) {
				Shelf shelf = repo.get(shelfId);
				if (shelfsOld.indexOf(shelfId) == -1) {
					shelf.setProduct(product);
					repo.edit(shelf);
				}
			}
		}
	}

	public List<Long> getShelfIdsByProductId(long productId) {
		Collection<Shelf> values = repo.getAll();
		List<Long> result = values.stream()
				.filter(value -> (value.getProduct() == null) ? true : value.getProduct().getId() == productId)
				.map(value -> value.getId()).collect(Collectors.toList());

		return result;
	}

	public List<Shelf> findByProductId(long id) {
		Collection<Shelf> values = repo.getAll();
		List<Shelf> result = values.stream().filter(value -> value.getProduct().getId() == id)
				.collect(Collectors.toList());

		return result;
	}

	public void removeProductsByProductId(long id) {
		repo.removeProductsByProductId(id);
	}

	public List<Shelf> getEmptyShelves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(Shelf entity) {
		// TODO Auto-generated method stub

	}
}
