package pt.upacademy.examples.hashMapExample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class HashMapExample<T extends BaseClass> {
	private Map<Long, T> db = new HashMap<Long, T>();
	private long currentId = 0;

	// CRUD
	public Long create(T user) {
		db.put(currentId, user);
		return currentId++;
	}

	public Collection<T> getAll() {
		return db.values();
	}

	public T getById(long id) {
		return db.get(id);
	}

	public void update(T entity) {
		db.put(entity.getId(), entity);
	}

	public void delete(long id) {
		db.remove(id);
	}
}
