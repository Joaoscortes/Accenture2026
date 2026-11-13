package io.altar.auth_example.controller;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import io.altar.auth_example.model.Role;
import io.altar.auth_example.model.User;
import io.altar.auth_example.service.UserService;
import io.altar.auth_example.util.JwtUtil;

class UserControllerTest {
    @Test
    void deletionRequiresOwnerOrAdminAndReportsMissingUser() {
        User requester = new User();
        requester.setId(1);
        requester.setRole(Role.USER);
        UserController controller = new UserController();
        controller.service = new UserService() {
            @Override public User findActiveById(Long id) { return id == 1 ? requester : null; }
            @Override public boolean softDeleteUser(Long id) { return id == 1 || id == 2; }
        };
        String authorization = "Bearer " + JwtUtil.generateToken("alice", "USER", 1);
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), controller.deleteUser(2L, authorization).getStatus());
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), controller.deleteUser(1L, authorization).getStatus());
        requester.setRole(Role.ADMIN);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), controller.deleteUser(2L, authorization).getStatus());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), controller.deleteUser(3L, authorization).getStatus());
    }
}
