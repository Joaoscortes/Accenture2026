package io.altar.jseproject.pratica2.services;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.pratica2.model.Entity_;
import io.altar.jseproject.pratica2.repositories.EntityRepository;
import io.altar.jseproject.pratica2.utils.interfaces.CRUD_Interface;

@Transactional
public abstract class EntityService<R extends EntityRepository<M>, M extends Entity_> implements CRUD_Interface<M> {

	@Inject
	protected R repo;

	@Override
	public long add(M e) {
		return repo.add(e);
	}

	@Override
	public Set<Long> getAllIds() {
		// TODO Auto-generated method stub
		return repo.getAllIds();
	}

	@Override
	public Collection<M> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public M get(long id) {
		// TODO Auto-generated method stub
		return repo.get(id);
	}

	@Override
	public void remove(long id) {
		repo.remove(id);

	}

}
