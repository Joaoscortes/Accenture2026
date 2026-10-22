package io.altar.auth_example.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.auth_example.model.User;
import io.altar.auth_example.persistence.UserPersistence;

@ApplicationScoped
@Transactional
public class UserService {

	@Inject
	protected UserPersistence persistence;

	public User create(User e) {
		return persistence.create(e);
	}
	
	public User findByUsername(String username) {
		return persistence.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return persistence.findByEmail(email);
	}
	
	public Iterable<User> getAllUsers() {
		return persistence.getAllUsers();
	}
	
	public void deleteUser(Long id) {
		persistence.deleteUser(id);
	}
}
