package io.altar.jeeexample.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import io.altar.jeeexample.model.Store;
import io.altar.jeeexample.persistence.StorePersistence;
import io.altar.jeeexample.service.StoreService;

/**
 * Controller class for managing Store entities. This class extends the generic
 * EntityController and provides REST endpoints for CRUD operations specific to
 * Store entities.
 */
@RequestScoped
@Path("store")
public class StoreController extends EntityController<StoreService, StorePersistence, Store> {

}
