package io.altar.jeeexample.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jeeexample.model.Entity_;
import io.altar.jeeexample.persistence.EntityPersistence;
import io.altar.jeeexample.util.ICRUD;

/**
 * Abstract base service class for all entity services. This class provides
 * common CRUD operations and handles the delegation of requests to the
 * corresponding persistence layer.
 * 
 * @param <P> The type of the persistence layer that this service uses
 * @param <E> The type of entity that this service manages
 */
@Transactional
public abstract class EntityService<P extends EntityPersistence<E>, E extends Entity_> implements ICRUD<E> {

	/**
	 * The persistence layer that this service delegates requests to.
	 */
	@Inject
	protected P persistence;

	/**
	 * Creates a new entity.
	 * 
	 * @param e The entity to create
	 * @return The created entity
	 */
	@Override
	public E create(E e) {
		return persistence.create(e);
	}

	/**
	 * Reads an entity by its unique identifier.
	 * 
	 * @param id The ID of the entity to read
	 * @return The entity with the specified ID, or null if not found
	 */
	@Override
	public E read(long id) {
		return persistence.read(id);
	}

	/**
	 * Updates an existing entity.
	 * 
	 * @param e The entity to update
	 * @return The updated entity
	 */
	@Override
	public E update(E e) {
		return persistence.update(e);
	}

	/**
	 * Deletes an entity by its unique identifier.
	 * 
	 * @param id The ID of the entity to delete
	 */
	@Override
	public void delete(long id) {
		persistence.delete(id);
	}

	/**
	 * Retrieves all entities without any filtering or sorting.
	 * 
	 * @return List of all entities
	 */
	public List<E> readAll() {
		return persistence.readAll();
	}

	/**
	 * Retrieves all entities with optional filtering and sorting.
	 * 
	 * @param filterField  The field to filter by (optional)
	 * @param filterValue  The value to filter by (optional)
	 * @param orderByField The field to sort by (optional)
	 * @param ascending    Whether to sort in ascending order
	 * @return List of entities matching the criteria
	 */
	public List<E> readAll(String filterField, String filterValue, String orderByField, boolean ascending) {
		return persistence.readAll(filterField, filterValue, orderByField, ascending);
	}

}