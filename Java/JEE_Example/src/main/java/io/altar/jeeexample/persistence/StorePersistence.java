package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Store;

/**
 * Persistence class for Store entities. This class handles all database
 * operations for Store objects using JPA. It extends EntityPersistence to
 * inherit common CRUD operations.
 */
@ApplicationScoped
public class StorePersistence extends EntityPersistence<Store> {

	@Override
	protected Class<Store> getEntityClass() {
		return Store.class;
	}

}
