package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Shelf;

@ApplicationScoped
public class ShelfPersistence extends EntityPersistence<Shelf> {

	@Override
	protected Class<Shelf> getEntityClass() {
		return Shelf.class;
	}
}
