package io.altar.jseproject.pratica2.model;

import io.altar.jseproject.pratica2.utils.exceptions.MyException;

public abstract class Entity_ {
	private long id = -1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if(this.id != -1 || id < 0) {
//			throw new IllegalArgumentException("Este atributo não pode ser modificado.");
//			throw new UnsupportedOperationException("Este atributo não pode ser modificado.");
//			throw new IllegalStateException("Este atributo não pode ser modificado.");
			throw new MyException("Este atributo não pode ser modificado.");
		}
		this.id = id;
	}

}
