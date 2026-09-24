package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.User;

@ApplicationScoped
public class UserPersistence extends EntityPersistence<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
