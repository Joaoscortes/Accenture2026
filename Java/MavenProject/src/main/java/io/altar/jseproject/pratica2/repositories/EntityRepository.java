package io.altar.jseproject.pratica2.repositories;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.pratica2.models.entities.Entity_;
import io.altar.jseproject.pratica2.utils.interfaces.CRUD_Interface;

public abstract class EntityRepository<T extends Entity_> implements CRUD_Interface<T> {

	
	@PersistenceContext(unitName = "database")
	protected EntityManager entityManager;
	
	protected abstract Class<T> getEntityClass();
	
	protected abstract String getAllEntities();
	
	protected abstract String getAllEntitiesIds();


	public T add(T entity) {
		return entityManager.merge(entity);
	}

	public Collection<Long> getAllIds() {
		return entityManager.createNamedQuery(getAllEntitiesIds(), Long.class).getResultList();
	}

	public Collection<T> getAll() {
		return entityManager.createNamedQuery(getAllEntities(), getEntityClass()).getResultList();
	}

	public T get(long id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void edit(T entity) {
		entityManager.merge(entity);
	}

	public void remove(long id) {
		T entity = get(id);
		if(entity != null) {
			entityManager.remove(entity);
		}
	}

	public boolean isEmpty() {
		return false;
	}

	public long size() {
		return 0;
	}
}
