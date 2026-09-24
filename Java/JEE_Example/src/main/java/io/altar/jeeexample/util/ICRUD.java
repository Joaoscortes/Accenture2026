package io.altar.jeeexample.util;

import io.altar.jeeexample.model.Entity_;

public interface ICRUD<E extends Entity_> {

	E create(E e);

	E read(long id);

	E update(E e);

	void delete(long id);
}
