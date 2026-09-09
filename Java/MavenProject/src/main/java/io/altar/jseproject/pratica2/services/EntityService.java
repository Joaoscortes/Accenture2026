package io.altar.jseproject.pratica2.services;

import java.util.Collection;
import java.util.Set;

import io.altar.jseproject.pratica2.model.Entity_;
import io.altar.jseproject.pratica2.repositories.EntityRepository;
import io.altar.jseproject.pratica2.utils.interfaces.CRUD_Interface;

public abstract class EntityService<U extends EntityRepository<T>, T extends Entity_> implements CRUD_Interface<T> {
	public U repo;

	@Override
	public long add(T e) {
		return repo.add(e);
	}

	@Override
	public Set<Long> getAllIds() {
		// TODO Auto-generated method stub
		return repo.getAllIds();
	}

	@Override
	public Collection<T> getAll() {
		// TODO Auto-generated method stub
		return repo.getAll();
	}

	@Override
	public T get(long id) {
		// TODO Auto-generated method stub
		return repo.get(id);
	}

	@Override
	public void remove(long id) {
		repo.remove(id);

	}

}
