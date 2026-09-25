package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.User;
import io.altar.jeeexample.persistence.UserPersistence;

/**
 * Service class for managing User entities. This class extends the generic
 * EntityService and provides user-specific business logic and operations.
 */
@ApplicationScoped
public class UserService extends EntityService<UserPersistence, User> {

}
