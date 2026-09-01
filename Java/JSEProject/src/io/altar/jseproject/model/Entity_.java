package io.altar.jseproject.model;

public abstract class Entity_ {
	private long id;
	private static long count = 0;
	

	public Entity_() {
		id = count++;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) throws Exception {
		if (id < 0) {
			throw new Exception();
		}
		this.id = id;
	}

}
