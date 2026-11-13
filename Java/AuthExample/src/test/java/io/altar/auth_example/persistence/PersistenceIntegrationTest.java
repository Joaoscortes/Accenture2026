package io.altar.auth_example.persistence;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.altar.auth_example.model.Address;
import io.altar.auth_example.model.Role;
import io.altar.auth_example.model.User;

class PersistenceIntegrationTest {
    private static EntityManagerFactory factory;

    @BeforeAll
    static void setUp() {
        factory = Persistence.createEntityManagerFactory("test");
    }

    @AfterAll
    static void tearDown() {
        if (factory != null) factory.close();
    }

    @Test
    void softDeleteRetainsAddressesAndAllowsUsernameAndEmailReuse() {
        EntityManager em = factory.createEntityManager();
        UserPersistence users = new UserPersistence();
        AddressPersistence addresses = new AddressPersistence();
        users.em = em;
        addresses.em = em;
        try {
            em.getTransaction().begin();
            User first = new User();
            first.setUsername("alice");
            first.setEmail("alice@example.test");
            first.setRole(Role.USER);
            first = users.create(first);
            User second = new User();
            second.setUsername("bob");
            second.setEmail("bob@example.test");
            second.setRole(Role.USER);
            second = users.create(second);
            Address address = new Address();
            address.setUser(first);
            address.setStreet("Street");
            address.setCity("City");
            address.setPostalCode("123");
            address.setCountry("Country");
            addresses.create(address);
            em.flush();

            long firstId = first.getId();
            long addressId = address.getId();
            assertEquals(1, addresses.findByUserId(firstId).size());
            assertNull(addresses.findByIdAndUserId(addressId, second.getId()));
            assertNotNull(addresses.findByIdAndUserId(addressId, firstId));
            assertTrue(users.softDeleteUser(firstId));
            assertFalse(users.softDeleteUser(firstId));
            em.flush();
            em.clear();

            User deleted = em.find(User.class, firstId);
            assertTrue(deleted.isDeleted());
            assertNull(deleted.getUsername());
            assertNull(deleted.getEmail());
            assertNotNull(em.find(Address.class, addressId));
            assertNull(users.findActiveById(firstId));
            assertNull(users.findByUsername("alice"));
            assertNull(users.findByEmail("alice@example.test"));
            int activeCount = 0;
            for (User ignored : users.getAllUsers()) activeCount++;
            assertEquals(1, activeCount);
            assertNull(users.updatePhotoUrl(firstId, "https://example.test/photo"));
            assertTrue(addresses.findByUserId(firstId).isEmpty());
            assertNull(addresses.findByIdAndUserId(addressId, firstId));

            User replacement = new User();
            replacement.setUsername("alice");
            replacement.setEmail("alice@example.test");
            replacement.setRole(Role.USER);
            replacement = users.create(replacement);
            em.flush();
            assertNotEquals(firstId, replacement.getId());
            assertEquals(replacement.getId(), users.findByUsername("alice").getId());
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
    }
}
