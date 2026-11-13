package io.altar.auth_example.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import io.altar.auth_example.dto.AddressRequest;
import io.altar.auth_example.dto.AddressResponse;
import io.altar.auth_example.model.Address;
import io.altar.auth_example.service.AddressService;
import io.altar.auth_example.util.JwtUtil;

class AddressControllerTest {
    @Test
    void validatesInputAndUsesAuthenticatedOwner() {
        AddressController controller = new AddressController();
        controller.service = new AddressService() {
            @Override public AddressResponse create(long userId, AddressRequest request) {
                assertEquals(7, userId);
                Address address = new Address();
                address.setId(5);
                address.setStreet(request.getStreet());
                return new AddressResponse(address);
            }
            @Override public java.util.List<AddressResponse> list(long userId) {
                assertEquals(7, userId);
                return Collections.emptyList();
            }
            @Override public AddressResponse get(long userId, long id) { return null; }
        };
        String authorization = "Bearer " + JwtUtil.generateToken("alice", "USER", 7);
        assertEquals(400, controller.create(authorization, null).getStatus());
        AddressRequest request = new AddressRequest();
        request.setStreet("Main");
        request.setCity("Lisbon");
        request.setPostalCode("123");
        request.setCountry("Portugal");
        Response response = controller.create(authorization, request);
        assertEquals(201, response.getStatus());
        assertEquals(5, ((AddressResponse) response.getEntity()).getId());
        assertEquals(200, controller.list(authorization).getStatus());
        assertEquals(404, controller.get(authorization, 9).getStatus());
    }
}
