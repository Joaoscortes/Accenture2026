package io.altar.jseproject.pratica2.model.converters;

import io.altar.jseproject.pratica2.model.Entity_;
import io.altar.jseproject.pratica2.model.dtos.EntityDTO;

public abstract class EntityConverter<E extends Entity_, D extends EntityDTO> {
	public abstract E toEntity(D dto);

	public abstract D toDTO(E e);
}
