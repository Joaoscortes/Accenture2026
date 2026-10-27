package io.altar.auth_example.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
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
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
		query.setParameter("username", username);
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		query.setParameter("email", email);
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Iterable<User> getAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}
	
	public User updatePhotoUrl(String username, String photoUrl) {
		User user = findByUsername(username);
		if (user != null) {
			user.setPhotoUrl(photoUrl);
		}
		return user;
	}

	public void deleteUser(Long id) {
		User user = em.find(User.class, id);
		if (user != null) {
			em.remove(user);
		}
	}
}
