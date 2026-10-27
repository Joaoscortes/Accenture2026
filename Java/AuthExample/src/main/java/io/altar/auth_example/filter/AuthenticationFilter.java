package io.altar.auth_example.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.altar.auth_example.util.JwtUtil;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    
    private static final List<String> EXCLUDED_PATHS = new ArrayList<>();
    private static final List<String> ADMIN_ONLY_PATHS = new ArrayList<>();
    
    static {
        // Add paths that don't require authentication (like login and recovery)
    	EXCLUDED_PATHS.add("/users");
        EXCLUDED_PATHS.add("/users/login");
        EXCLUDED_PATHS.add("/users/recover-password");
        
        // Add paths that require admin role
        ADMIN_ONLY_PATHS.add("/users/query");
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String requestPath = requestContext.getUriInfo().getPath();
        
        // Skip authentication for excluded paths
        if (EXCLUDED_PATHS.stream().anyMatch(path -> requestPath.contains(path))) {
            return;
        }
        
        // Get the Authorization header
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Missing or invalid authorization header")
                    .build()
            );
            return;
        }
        
        // Extract the token
        String token = authorizationHeader.substring("Bearer ".length());
        
        try {
            // Validate the token
            if (!JwtUtil.validateToken(token, JwtUtil.getUsernameFromToken(token))) {
                requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid or expired token")
                        .build()
                );
                return;
            }
            
            // Check if endpoint requires admin role
            if (ADMIN_ONLY_PATHS.stream().anyMatch(path -> requestPath.contains(path))) {
                String userRole = JwtUtil.getRoleFromToken(token);
                if (!"ADMIN".equals(userRole)) {
                    requestContext.abortWith(
                        Response.status(Response.Status.FORBIDDEN)
                            .entity("Admin role required for this operation")
                            .build()
                    );
                }
            }
        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token: " + e.getMessage())
                    .build()
            );
        }
    }
}