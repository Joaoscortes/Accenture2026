package io.altar.jseproject.pratica2.repositories;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.pratica2.model.Shelf;

@ApplicationScoped
public class ShelfRepository extends EntityRepository<Shelf> {

//	private static final ShelfRepository INSTANCE = new ShelfRepository();
//
//	private ShelfRepository() {
//	}
//
//	public static ShelfRepository getInstance() {
//		return INSTANCE;
//	}

	public List<Long> getShelfIdsByProductId(long productId) {
		Collection<Shelf> values = this.getAll();
		List<Long> result = values.stream()
				.filter(value -> (value.getProduct() == null) ? true : value.getProduct().getId() == productId)
				.map(value -> value.getId()).collect(Collectors.toList());

		return result;
	}

	public List<Shelf> getAllShelvesFromProductId(long id) {
		Collection<Shelf> values = this.getAll();
		List<Shelf> result = values.stream()
				.filter(value -> (value.getProduct() == null) ? true : value.getProduct().getId() == id)
				.collect(Collectors.toList());

		return result;
	}

}
