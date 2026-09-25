package io.altar.jeeexample.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.altar.jeeexample.model.Entity_;
import io.altar.jeeexample.persistence.EntityPersistence;
import io.altar.jeeexample.service.EntityService;
import io.altar.jeeexample.util.ICRUD;

/**
 * Abstract base controller class for all entity controllers. This class
 * provides common REST endpoints for CRUD operations and handles the delegation
 * of requests to the corresponding service layer.
 * 
 * @param <S> The type of the service that this controller uses
 * @param <P> The type of the persistence layer that this controller uses
 * @param <E> The type of entity that this controller manages
 */
public abstract class EntityController<S extends EntityService<P, E>, P extends EntityPersistence<E>, E extends Entity_>
		implements ICRUD<E> {

	/**
	 * The service layer that this controller delegates requests to.
	 */
	@Inject
	protected S service;

	/**
	 * Creates a new entity.
	 * 
	 * @param e The entity to create
	 * @return The created entity
	 */
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public E create(E e) {
		return service.create(e);
	}

	/**
	 * Reads an entity by its ID.
	 * 
	 * @param id The ID of the entity to read
	 * @return The entity with the specified ID
	 */
	@Override
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public E read(@PathParam("id") long id) {
		return service.read(id);
	}

	/**
	 * Retrieves all entities with optional filtering and sorting.
	 * 
	 * @param filterField  The field to filter by (optional)
	 * @param filterValue  The value to filter by (optional)
	 * @param orderByField The field to sort by (optional)
	 * @param ascending    Whether to sort in ascending order (defaults to true if
	 *                     null)
	 * @return List of entities matching the criteria
	 */
	@GET
	@Path("filter")
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> readAll(@QueryParam("filterField") String filterField, @QueryParam("filterValue") String filterValue,
			@QueryParam("orderByField") String orderByField, @QueryParam("ascending") Boolean ascending) {
		if (ascending == null) {
			ascending = true;
		}
		return service.readAll(filterField, filterValue, orderByField, ascending);
	}

	/**
	 * Retrieves all entities without any filtering or sorting.
	 * 
	 * @return List of all entities
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> readAll() {
		return service.readAll();
	}

	/**
	 * Updates an existing entity.
	 * 
	 * @param e The entity to update
	 * @return The updated entity
	 */
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public E update(E e) {
		return service.update(e);
	}

	/**
	 * Deletes an entity by its ID.
	 * 
	 * @param id The ID of the entity to delete
	 */
	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		service.delete(id);
	}

}