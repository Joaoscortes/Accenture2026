package io.altar.jeeexample.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import io.altar.jeeexample.model.Shelf;
import io.altar.jeeexample.persistence.ShelfPersistence;
import io.altar.jeeexample.service.ShelfService;

@RequestScoped
@Path("shelves")
public class ShelfController extends EntityController<ShelfService, ShelfPersistence, Shelf> {

}
