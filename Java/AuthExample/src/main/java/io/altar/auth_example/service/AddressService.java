package io.altar.auth_example.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.auth_example.dto.AddressRequest;
import io.altar.auth_example.dto.AddressResponse;
import io.altar.auth_example.model.Address;
import io.altar.auth_example.model.User;
import io.altar.auth_example.persistence.AddressPersistence;
import io.altar.auth_example.persistence.UserPersistence;

@ApplicationScoped
@Transactional
public class AddressService {
    @Inject
    protected AddressPersistence persistence;
    @Inject
    protected UserPersistence users;

    public List<AddressResponse> list(long userId) {
        return persistence.findByUserId(userId).stream().map(AddressResponse::new).collect(Collectors.toList());
    }

    public AddressResponse get(long userId, long id) {
        Address address = persistence.findByIdAndUserId(id, userId);
        return address == null ? null : new AddressResponse(address);
    }

    public AddressResponse create(long userId, AddressRequest request) {
        User user = users.findActiveById(userId);
        if (user == null) {
            return null;
        }
        Address address = new Address();
        address.setUser(user);
        apply(address, request);
        return new AddressResponse(persistence.create(address));
    }

    public AddressResponse update(long userId, long id, AddressRequest request) {
        Address address = persistence.findByIdAndUserId(id, userId);
        if (address == null) {
            return null;
        }
        apply(address, request);
        return new AddressResponse(address);
    }

    public boolean delete(long userId, long id) {
        Address address = persistence.findByIdAndUserId(id, userId);
        if (address == null) {
            return false;
        }
        persistence.remove(address);
        return true;
    }

    private void apply(Address address, AddressRequest request) {
        address.setStreet(request.getStreet().trim());
        address.setCity(request.getCity().trim());
        address.setPostalCode(request.getPostalCode().trim());
        address.setCountry(request.getCountry().trim());
    }

	public AddressResponse get(long id) {
		Address address = persistence.get(id);
		System.out.println("address " + address);
        return address == null ? null : new AddressResponse(address);
	}
}
