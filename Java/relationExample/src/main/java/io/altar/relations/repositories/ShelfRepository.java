package io.altar.relations.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.altar.relations.models.Shelf;

@ApplicationScoped
public class ShelfRepository extends EntityRepository<Shelf> {

	@Override
	protected Class<Shelf> getEntityClass() {
		return Shelf.class;
	}
}