package io.altar.jseproject.pratica2.utils.interfaces;

import java.util.Collection;
import java.util.Set;

import io.altar.jseproject.pratica2.models.entities.Entity_;

public interface CRUD_Interface<T extends Entity_> {

	T add(T e);

	Collection<Long> getAllIds();

	Collection<T> getAll();

	T get(long id);

	void edit(T entity);

	void remove(long id);
}
