package io.altar.auth_example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import io.altar.auth_example.model.Role;
import io.altar.auth_example.model.User;
import io.altar.auth_example.persistence.UserPersistence;

class UserServiceTest {
    @Test
    void registrationCannotSetIdDeletedOrAdmin() {
        UserService service = new UserService();
        service.persistence = new UserPersistence() {
            @Override public User create(User user) { return user; }
        };
        User user = new User();
        user.setId(42);
        user.setDeleted(true);
        user.setRole(Role.ADMIN);
        User result = service.create(user);
        assertEquals(0, result.getId());
        assertFalse(result.isDeleted());
        assertEquals(Role.USER, result.getRole());
    }
}
