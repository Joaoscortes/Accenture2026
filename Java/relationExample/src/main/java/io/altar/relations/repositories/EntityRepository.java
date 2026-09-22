package io.altar.relations.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.relations.models.Entity_;

public abstract class EntityRepository<E extends Entity_> {

	@PersistenceContext(unitName = "database")
	protected EntityManager entityManager;
	
	protected abstract Class<E> getEntityClass();
	
	public E addEntity(E entity) {
		return entityManager.merge(entity);
	}

	public E getEntity(long id) {
		return entityManager.find(getEntityClass(), id);
	}

	public E editEntity(E entity) {
		return entityManager.merge(entity);
	}

	public void removeEntity(long id) {
		E entity = getEntity(id);
		if(entity != null) {
			entityManager.remove(entity);
		}
	}
}
