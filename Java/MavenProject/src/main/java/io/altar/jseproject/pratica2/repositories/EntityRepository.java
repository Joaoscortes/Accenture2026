package io.altar.jseproject.pratica2.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.altar.jseproject.pratica2.model.Entity_;
import io.altar.jseproject.pratica2.utils.interfaces.CRUD_Interface;

public abstract class EntityRepository<T extends Entity_> implements CRUD_Interface<T> {

	private Map<Long, T> map = new HashMap<Long, T>();

	private long currentID = 1;

	public long add(T entity) {
		map.put(currentID, entity);
		entity.setId(currentID);
		return currentID++;
	}

	public Set<Long> getAllIds() {
		return map.keySet();
	}

	public Collection<T> getAll() {
		return map.values();
	}

	public T get(long id) {
		T entity = map.get(id);
		return entity;
	}

	public void edit(T entity) {
		map.put(entity.getId(), entity);
	}

	public void remove(long id) {
		map.remove(id);
	}

	public boolean isEmpty() {
		return (map.size() == 0) ? true : false;
	}

	public long size() {
		return map.size();
	}
}
