package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Shelf;
import io.altar.jeeexample.persistence.ShelfPersistence;

@ApplicationScoped
public class ShelfService extends EntityService<ShelfPersistence, Shelf> {

}
