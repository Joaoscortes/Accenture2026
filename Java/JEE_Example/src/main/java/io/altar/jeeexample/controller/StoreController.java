package io.altar.jeeexample.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import io.altar.jeeexample.model.Store;
import io.altar.jeeexample.persistence.StorePersistence;
import io.altar.jeeexample.service.StoreService;

@RequestScoped
@Path("store")
public class StoreController extends EntityController<StoreService, StorePersistence, Store> {

}
