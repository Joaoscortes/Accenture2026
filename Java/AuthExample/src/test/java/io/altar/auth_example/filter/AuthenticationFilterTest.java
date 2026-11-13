package io.altar.auth_example.filter;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.Test;

import io.altar.auth_example.model.Role;
import io.altar.auth_example.model.User;
import io.altar.auth_example.service.UserService;
import io.altar.auth_example.util.JwtUtil;

class AuthenticationFilterTest {
    private final AuthenticationFilter filter = new AuthenticationFilter();
    private User activeUser;

    AuthenticationFilterTest() {
        filter.users = new UserService() {
            @Override
            public User findActiveById(Long id) {
                return activeUser != null && id == activeUser.getId() && !activeUser.isDeleted() ? activeUser : null;
            }
        };
    }

    @Test
    void publicRoutesAreExactAndMethodSpecific() throws Exception {
        assertNull(run("POST", "users", null));
        assertNull(run("POST", "users/login", null));
        assertEquals(401, run("DELETE", "users/1", null).getStatus());
        assertEquals(401, run("GET", "users/query", null).getStatus());
        assertEquals(401, run("POST", "users/login/extra", null).getStatus());
    }

    @Test
    void deletedUsersAndReusedUsernamesCannotUseOldTokens() throws Exception {
        activeUser = user(1, "alice", Role.USER);
        String oldToken = JwtUtil.generateToken("alice", "USER", 1);
        assertNull(run("GET", "addresses", oldToken));
        activeUser.setDeleted(true);
        assertEquals(401, run("GET", "addresses", oldToken).getStatus());
        activeUser = user(2, "alice", Role.USER);
        assertEquals(401, run("GET", "addresses", oldToken).getStatus());
        assertNull(run("GET", "addresses", JwtUtil.generateToken("alice", "USER", 2)));
        assertEquals(401, run("GET", "addresses", "invalid").getStatus());
    }

    @Test
    void adminAuthorizationUsesCurrentDatabaseRole() throws Exception {
        activeUser = user(1, "alice", Role.USER);
        String token = JwtUtil.generateToken("alice", "ADMIN", 1);
        assertEquals(403, run("GET", "users/query", token).getStatus());
        assertEquals(403, run("GET", "users/query;ignored=value", token).getStatus());
        activeUser.setRole(Role.ADMIN);
        assertNull(run("GET", "users/query", token));
    }

    private User user(long id, String username, Role role) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setRole(role);
        return user;
    }

    private Response run(String method, String path, String token) throws Exception {
        AtomicReference<Response> aborted = new AtomicReference<>();
        UriInfo uri = (UriInfo) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { UriInfo.class },
                (proxy, called, args) -> "getPath".equals(called.getName()) ? path : null);
        ContainerRequestContext context = (ContainerRequestContext) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[] { ContainerRequestContext.class }, (proxy, called, args) -> {
                    switch (called.getName()) {
                    case "getUriInfo": return uri;
                    case "getMethod": return method;
                    case "getHeaderString": return token == null ? null : "Bearer " + token;
                    case "abortWith": aborted.set((Response) args[0]); return null;
                    default: return null;
                    }
                });
        filter.filter(context);
        return aborted.get();
    }
}
