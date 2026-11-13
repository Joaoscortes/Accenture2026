package io.altar.auth_example.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.altar.auth_example.model.Role;
import io.altar.auth_example.model.User;
import io.altar.auth_example.service.UserService;
import io.altar.auth_example.util.JwtUtil;
import io.jsonwebtoken.JwtException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    protected UserService users;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath().replaceAll(";[^/]*", "")
                .replaceAll("^/+", "").replaceAll("/+$", "");
        if ("POST".equals(requestContext.getMethod()) && ("users".equals(path)
                || "users/login".equals(path) || "users/recover-password".equals(path))) {
            return;
        }

        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Missing or invalid authorization header").build());
            return;
        }

        String token = authorization.substring("Bearer ".length()).trim();
        Long userId;
        String username;
        try {
            userId = JwtUtil.getUserIdFromToken(token);
            username = JwtUtil.getUsernameFromToken(token);
        } catch (JwtException | IllegalArgumentException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid or expired token").build());
            return;
        }
        User user = userId == null ? null : users.findActiveById(userId);
        if (user == null || username == null || !username.equals(user.getUsername())) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid or expired token").build());
            return;
        }
        if ("users/query".equals(path) && user.getRole() != Role.ADMIN) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Admin role required for this operation").build());
        }
    }
}
