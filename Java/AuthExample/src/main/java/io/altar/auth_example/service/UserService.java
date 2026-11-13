package io.altar.auth_example.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.auth_example.model.User;
import io.altar.auth_example.model.Role;
import io.altar.auth_example.persistence.UserPersistence;

@ApplicationScoped
@Transactional
public class UserService {

	@Inject
	protected UserPersistence persistence;

	public User create(User e) {
		e.setId(0);
		e.setDeleted(false);
		e.setRole(Role.USER);
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
	
	public User updatePhotoUrl(Long id, String photoUrl) {
		return persistence.updatePhotoUrl(id, photoUrl);
	}

	public User findActiveById(Long id) {
		return persistence.findActiveById(id);
	}

	public boolean softDeleteUser(Long id) {
		return persistence.softDeleteUser(id);
	}
}
