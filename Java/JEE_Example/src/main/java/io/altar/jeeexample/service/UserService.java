package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.User;
import io.altar.jeeexample.persistence.UserPersistence;

@ApplicationScoped
public class UserService extends EntityService<UserPersistence, User> {

}
