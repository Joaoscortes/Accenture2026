package io.altar.auth_example.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AddressRequestTest {
    @Test
    void requiresAllNonBlankFieldsAndEnforcesLength() {
        AddressRequest request = new AddressRequest();
        assertFalse(request.isValid());
        request.setStreet("Main Street");
        request.setCity("Lisbon");
        request.setPostalCode("1000-001");
        request.setCountry("Portugal");
        assertTrue(request.isValid());
        request.setCountry("  ");
        assertFalse(request.isValid());
        request.setCountry(new String(new char[256]).replace('\0', 'x'));
        assertFalse(request.isValid());
        request.setCountry(new String(new char[255]).replace('\0', 'x'));
        assertTrue(request.isValid());
    }
}
