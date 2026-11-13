package io.altar.auth_example.dto;

import io.altar.auth_example.model.Address;

public class AddressResponse {
    private final long id;
    private final String street;
    private final String city;
    private final String postalCode;
    private final String country;
    private final UserResponse user;

    public AddressResponse(Address address) {
        id = address.getId();
        street = address.getStreet();
        city = address.getCity();
        postalCode = address.getPostalCode();
        country = address.getCountry();
        user = address.getUser() == null ? null : new UserResponse(address.getUser());
    }

    public long getId() { return id; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
    public UserResponse getUser() { return user; }
}
