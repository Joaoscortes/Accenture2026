package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Store;
import io.altar.jeeexample.persistence.StorePersistence;

/**
 * Service class for managing Store entities. This class extends the generic
 * EntityService and provides store-specific business logic and operations.
 */
@ApplicationScoped
public class StoreService extends EntityService<StorePersistence, Store> {

}
