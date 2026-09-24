package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Store;

@ApplicationScoped
public class StorePersistence extends EntityPersistence<Store> {

	@Override
	protected Class<Store> getEntityClass() {
		return Store.class;
	}

}
