package io.altar.jeeexample.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.altar.jeeexample.model.Entity_;

/**
 * Abstract base class for all persistence layers in the application. This class
 * provides common database operations for entities using JPA. It handles CRUD
 * (Create, Read, Update, Delete) operations and generic query methods.
 * 
 * @param <E> The entity type that this persistence class handles
 */
public abstract class EntityPersistence<E extends Entity_> {

	@PersistenceContext(unitName = "database")
	protected EntityManager em;

	/**
	 * Gets the entity class handled by this persistence layer. This method must be
	 * implemented by subclasses to specify which entity type they work with.
	 * 
	 * @return The Class object representing the entity type
	 */
	protected abstract Class<E> getEntityClass();

	/**
	 * Creates a new entity in the database. This method delegates to the update
	 * method as entities are created through merging.
	 * 
	 * @param e The entity to create
	 * @return The created entity with assigned ID
	 */
	public E create(E e) {
		return update(e);
	}

	/**
	 * Reads an entity by its unique identifier.
	 * 
	 * @param id The ID of the entity to read
	 * @return The entity if found, or null if not found
	 */
	public E read(long id) {
		return em.find(getEntityClass(), id);
	}

	/**
	 * Updates an existing entity in the database. This method merges the entity
	 * with the persistence context.
	 * 
	 * @param e The entity to update
	 * @return The updated entity
	 */
	public E update(E e) {
		return em.merge(e);
	}

	/**
	 * Deletes an entity by its unique identifier. If the entity doesn't exist, this
	 * method does nothing.
	 * 
	 * @param id The ID of the entity to delete
	 */
	public void delete(long id) {
		E e = read(id);
		if (e != null) {
			em.remove(e);
		}
	}

	/**
	 * Reads all entities from the database.
	 * 
	 * @return A list of all entities
	 */
	public List<E> readAll() {
		TypedQuery<E> query = em.createQuery("SELECT e FROM " + getEntityClass().getSimpleName() + " e",
				getEntityClass());
		return query.getResultList();
	}

	/**
	 * Reads all entities with filtering and ordering capabilities. This method
	 * supports filtering by a specific field and ordering the results.
	 * 
	 * @param filterField  The name of the field to filter by (can be null)
	 * @param filterValue  The value to filter by (can be null)
	 * @param orderByField The name of the field to order by (can be null)
	 * @param ascending    Whether to sort in ascending order (default is true)
	 * @return A list of entities matching the criteria
	 */
	public List<E> readAll(String filterField, String filterValue, String orderByField, boolean ascending) {
		// Simple approach: only support string filtering for now
		// For simplicity in Java EE contexts with limited JAX-RS conversion support

		StringBuilder jpql = new StringBuilder("SELECT e FROM ");
		jpql.append(getEntityClass().getSimpleName());
		jpql.append(" e");

		// Add WHERE clause if filter is provided (simple string matching only)
		if (filterField != null && !filterField.isEmpty() && filterValue != null && !filterValue.isEmpty()) {
			jpql.append(" WHERE e.");
			jpql.append(filterField);
			jpql.append(" LIKE :filterValue");
		}

		// Add ORDER BY clause
		jpql.append(" ORDER BY e.");
		jpql.append(orderByField != null && !orderByField.isEmpty() ? orderByField : "id");
		jpql.append(ascending ? " ASC" : " DESC");

		TypedQuery<E> query = em.createQuery(jpql.toString(), getEntityClass());

		// Set filter parameter if provided
		if (filterField != null && !filterField.isEmpty() && filterValue != null && !filterValue.isEmpty()) {
			// Determine the type of the filter field and convert filterValue accordingly
			Object convertedFilterValue = convertFilterValue(filterField, filterValue);
			query.setParameter("filterValue", convertedFilterValue);
		}

		return query.getResultList();
	}

	/**
	 * Converts a string filter value to the appropriate data type based on the
	 * field type. This method attempts to parse the filter value according to the
	 * target field's data type.
	 * 
	 * @param fieldName   The name of the field to convert the value for
	 * @param filterValue The string value to convert
	 * @return The converted value in the appropriate type
	 */
	private Object convertFilterValue(String fieldName, String filterValue) {
		try {
			// Get the entity class to determine field types
			Class<E> entityClass = getEntityClass();

			// Try to find the field in the entity class
			java.lang.reflect.Field field = null;
			try {
				field = entityClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// If field not found directly, check parent classes
				Class<?> currentClass = entityClass;
				while (currentClass != null && field == null) {
					try {
						field = currentClass.getDeclaredField(fieldName);
					} catch (NoSuchFieldException ex) {
						currentClass = currentClass.getSuperclass();
					}
				}
			}

			if (field != null) {
				Class<?> fieldType = field.getType();

				// Convert based on the field type
				if (fieldType == int.class || fieldType == Integer.class) {
					return Integer.parseInt(filterValue);
				} else if (fieldType == long.class || fieldType == Long.class) {
					return Long.parseLong(filterValue);
				} else if (fieldType == double.class || fieldType == Double.class) {
					return Double.parseDouble(filterValue);
				} else if (fieldType == float.class || fieldType == Float.class) {
					return Float.parseFloat(filterValue);
				} else if (fieldType == boolean.class || fieldType == Boolean.class) {
					return Boolean.parseBoolean(filterValue);
				} else if (fieldType == java.util.Date.class) {
					// For Date fields, you might need to parse from a specific format
					// This is a simplified approach - in practice you'd use SimpleDateFormat or
					// similar
					return filterValue; // Return as string for now
				}
			}

			// If we can't determine the type or it's not a primitive wrapper, treat as
			// String
			return filterValue;
		} catch (Exception e) {
			// If any error occurs during conversion, return the original value
			return filterValue;
		}
	}

}