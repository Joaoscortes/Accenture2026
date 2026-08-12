package pt.upcademy.examples.hashMapExample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashMapExample<T extends BaseClass> {
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

	public void update(T user) {
		db.put(user.getId(), user);
	}

	public void delete(long id) {
		db.remove(id);
	}
}
