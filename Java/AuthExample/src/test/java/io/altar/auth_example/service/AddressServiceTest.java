package io.altar.auth_example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import io.altar.auth_example.dto.AddressRequest;
import io.altar.auth_example.dto.AddressResponse;
import io.altar.auth_example.dto.UserResponse;
import io.altar.auth_example.model.Address;
import io.altar.auth_example.model.User;
import io.altar.auth_example.persistence.AddressPersistence;
import io.altar.auth_example.persistence.UserPersistence;

class AddressServiceTest {
    @Test
    void crudIsScopedToOwnerAndTrimsFields() {
        User owner = new User();
        owner.setId(1);
        owner.setUsername("alice");
        AddressService service = new AddressService();
        service.users = new UserPersistence() {
            @Override public User findActiveById(Long id) { return id == 1 ? owner : null; }
        };
        service.persistence = new AddressPersistence() {
            private final List<Address> data = new ArrayList<>();
            @Override public Address create(Address address) {
                address.setId(10);
                data.add(address);
                return address;
            }
            @Override public List<Address> findByUserId(long id) {
                return data.stream().filter(a -> a.getUser().getId() == id).collect(Collectors.toList());
            }
            @Override public Address findByIdAndUserId(long id, long userId) {
                return findByUserId(userId).stream().filter(a -> a.getId() == id).findFirst().orElse(null);
            }
            @Override public void remove(Address address) { data.remove(address); }
        };
        AddressRequest request = new AddressRequest();
        request.setStreet(" Main ");
        request.setCity(" Lisbon ");
        request.setPostalCode(" 123 ");
        request.setCountry(" Portugal ");
        AddressResponse created = service.create(1, request);
        assertEquals("Main", created.getStreet());
        UserResponse responseOwner = created.getUser();
        assertNotNull(responseOwner);
        assertEquals(1, responseOwner.getId());
        assertEquals("alice", responseOwner.getUsername());
        assertEquals(1, service.list(1).size());
        assertTrue(service.list(2).isEmpty());
        assertNull(service.create(2, request));
        assertNull(service.get(2, 10));
        assertNull(service.update(2, 10, request));
        assertFalse(service.delete(2, 10));
        request.setCity("Porto");
        assertEquals("Porto", service.update(1, 10, request).getCity());
        assertTrue(service.delete(1, 10));
        assertNull(service.get(1, 10));
    }
}
