package io.altar.jseproject.pratica2.services;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.pratica2.models.entities.Entity_;
import io.altar.jseproject.pratica2.repositories.EntityRepository;
import io.altar.jseproject.pratica2.utils.interfaces.CRUD_Interface;

@Transactional
public abstract class EntityService<R extends EntityRepository<M>, M extends Entity_> implements CRUD_Interface<M> {

	@Inject
	protected R repo;

	public abstract String getClassName();

	public long add(M entity) {
		return repo.add(entity);
	}

	public Set<Long> getAllIds() {
		return repo.getAllIds();
	}

	public Collection<M> getAll() {
		return repo.getAll();
	}

	public M get(long id) {
		return validEntity(id);
	}

	public void edit(M entity) {
		validEntity(entity.getId());
		repo.edit(entity);
	}

	public void remove(long id) {
		repo.remove(id);
	}

	public boolean isEmpty() {
		return repo.isEmpty();
	}

	public long size() {
		return repo.size();
	}

	public M validEntity(long entityId) {
		M entity = repo.get(entityId);
		if (entity == null) {
			throw new IllegalArgumentException(String.format("No %s with Id [%d].", getClassName(), entityId));
		}
		return entity;
	}

}
