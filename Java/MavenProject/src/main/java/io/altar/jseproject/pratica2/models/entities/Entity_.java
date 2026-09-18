package io.altar.jseproject.pratica2.models.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.altar.jseproject.pratica2.utils.exceptions.DisallowModificationAttributeException;

@MappedSuperclass
public abstract class Entity_ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id = -1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (this.id != -1 || id < 0) {
			throw new DisallowModificationAttributeException();
//			throw new UnsupportedOperationException("Este atributo não pode ser modificado.");
//			throw new IllegalStateException("Este atributo não pode ser modificado.");
//			throw new IllegalArgumentException("Este atributo não pode ser modificado.");
		}
		this.id = id;
	}
}
