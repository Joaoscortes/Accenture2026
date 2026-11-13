package io.altar.auth_example.persistence;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.auth_example.dto.AddressResponse;
import io.altar.auth_example.model.Address;

@ApplicationScoped
public class AddressPersistence {
    @PersistenceContext(unitName = "database")
    protected EntityManager em;

    public Address create(Address address) {
        em.persist(address);
        return address;
    }

    public List<Address> findByUserId(long userId) {
        return em.createQuery("SELECT a FROM Address a WHERE a.user.id = :userId AND a.user.deleted = false ORDER BY a.id", Address.class)
                .setParameter("userId", userId).getResultList();
    }

    public Address findByIdAndUserId(long id, long userId) {
        List<Address> results = em.createQuery(
                "SELECT a FROM Address a WHERE a.id = :id AND a.user.id = :userId AND a.user.deleted = false", Address.class)
                .setParameter("id", id).setParameter("userId", userId).getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public void remove(Address address) {
        em.remove(address);
    }

	public Address get(long id) {
		return em.find(Address.class, id);
	}
}
