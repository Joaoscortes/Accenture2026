package io.altar.jeeexample.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jeeexample.model.Entity_;
import io.altar.jeeexample.persistence.EntityPersistence;
import io.altar.jeeexample.util.ICRUD;

@Transactional
public abstract class EntityService<P extends EntityPersistence<E>, E extends Entity_> implements ICRUD<E> {

	@Inject
	protected P persistence;

	@Override
	public E create(E e) {
		return persistence.create(e);
	}

	@Override
	public E read(long id) {
		return persistence.read(id);
	}

	@Override
	public E update(E e) {
		return persistence.update(e);
	}

	@Override
	public void delete(long id) {
		persistence.delete(id);
	}

}
