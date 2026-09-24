package io.altar.jeeexample.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import io.altar.jeeexample.model.User;
import io.altar.jeeexample.persistence.UserPersistence;
import io.altar.jeeexample.service.UserService;

@RequestScoped
@Path("user")
public class UserController extends EntityController<UserService, UserPersistence, User> {

}
