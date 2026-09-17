package io.altar.jseproject.pratica2.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.pratica2.model.Shelf;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;

@ApplicationScoped
public class ShelfService extends EntityService<ShelfRepository, Shelf> {

//	public ShelfService() {
//		repo = ShelfRepository.getInstance();
//	}

	public List<Long> getShelfIdsByProductId(long productId) {

		return repo.getShelfIdsByProductId(productId);
	}

	@Override
	public void edit(Shelf s) {
		repo.edit(s);
	}

	public List<Shelf> getAllShelvesFromProductId(long id) {
		// TODO Auto-generated method stub
		return repo.getAllShelvesFromProductId(id);
	}
}
