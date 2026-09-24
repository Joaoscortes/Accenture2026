package io.altar.jeeexample.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.jeeexample.model.Entity_;
import io.altar.jeeexample.util.ICRUD;

public abstract class EntityPersistence<E extends Entity_> implements ICRUD<E> {

	@PersistenceContext(unitName = "database")
	protected EntityManager em;

	protected abstract Class<E> getEntityClass();

	@Override
	public E create(E e) {
		return update(e);
	}

	@Override
	public E read(long id) {
		return em.find(getEntityClass(), id);
	}

	@Override
	public E update(E e) {
		return em.merge(e);
	}

	@Override
	public void delete(long id) {
		E e = read(id);
		if (e != null) {
			em.remove(e);
		}

	}

}
