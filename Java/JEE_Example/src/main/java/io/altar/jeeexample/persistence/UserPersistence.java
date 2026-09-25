package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.User;

/**
 * Persistence class for User entities. This class handles all database
 * operations for User objects using JPA. It extends EntityPersistence to
 * inherit common CRUD operations.
 */
@ApplicationScoped
public class UserPersistence extends EntityPersistence<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
