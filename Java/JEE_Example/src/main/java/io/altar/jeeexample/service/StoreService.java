package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Store;
import io.altar.jeeexample.persistence.StorePersistence;

@ApplicationScoped
public class StoreService extends EntityService<StorePersistence, Store> {

}
