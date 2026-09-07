package io.altar.jseproject.pratica1.model;

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
			throw new IllegalArgumentException("Este atributo não pode ser modificado.");
//			throw new UnsupportedOperationException("Este atributo não pode ser modificado.");
//			throw new IllegalStateException("Este atributo não pode ser modificado.");
		}
		this.id = id;
	}

}
