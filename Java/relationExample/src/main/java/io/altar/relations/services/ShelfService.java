package io.altar.relations.services;

import javax.enterprise.context.RequestScoped;

import io.altar.relations.models.Shelf;
import io.altar.relations.repositories.ShelfRepository;

@RequestScoped
public class ShelfService extends EntityService<ShelfRepository, Shelf> {

}
