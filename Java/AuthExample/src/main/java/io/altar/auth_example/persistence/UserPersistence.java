package io.altar.auth_example.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.altar.auth_example.model.User;

@ApplicationScoped
public class UserPersistence {

	@PersistenceContext(unitName = "database")
	protected EntityManager em;
	
	public User create(User e) {
		return em.merge(e);
	}
	
	public User findByUsername(String username) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.deleted = false", User.class);
		query.setParameter("username", username);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.deleted = false", User.class);
		query.setParameter("email", email);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Iterable<User> getAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.deleted = false", User.class);
		return query.getResultList();
	}
	
	public User updatePhotoUrl(Long id, String photoUrl) {
		User user = findActiveById(id);
		if (user != null) {
			user.setPhotoUrl(photoUrl);
		}
		return user;
	}

	public User findActiveById(Long id) {
		User user = em.find(User.class, id);
		return user != null && !user.isDeleted() ? user : null;
	}

	public boolean softDeleteUser(Long id) {
		User user = findActiveById(id);
		if (user == null) {
			return false;
		}
		user.setDeleted(true);
		user.setUsername(null);
		user.setEmail(null);
		return true;
	}
}
