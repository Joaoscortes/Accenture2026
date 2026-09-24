package io.altar.jeeexample.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.altar.jeeexample.model.Entity_;
import io.altar.jeeexample.persistence.EntityPersistence;
import io.altar.jeeexample.service.EntityService;
import io.altar.jeeexample.util.ICRUD;

public abstract class EntityController<S extends EntityService<P, E>, P extends EntityPersistence<E>, E extends Entity_>
		implements ICRUD<E> {

	@Inject
	protected S service;

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public E create(E e) {
		return service.create(e);
	}

	@Override
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public E read(@PathParam("id") long id) {
		return service.read(id);
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public E update(E e) {
		return service.update(e);
	}

	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		service.delete(id);
	}

}
