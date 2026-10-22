package io.altar.auth_example.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.auth_example.dto.LoginRequest;
import io.altar.auth_example.dto.LoginResponse;
import io.altar.auth_example.dto.RecoverPasswordRequest;
import io.altar.auth_example.dto.UserResponse;
import io.altar.auth_example.model.User;
import io.altar.auth_example.service.UserService;
import io.altar.auth_example.util.JwtUtil;
import io.altar.auth_example.util.PasswordUtil;
import io.altar.auth_example.util.SendGridService;
import io.jsonwebtoken.JwtException;

@RequestScoped
@Path("users")
public class UserController {

	@Inject
	protected UserService service;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User create(User e) {
		return service.create(e);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest loginRequest) {
		// Find the user by username
		User user = service.findByUsername(loginRequest.getUsername());
		
		if (user != null && PasswordUtil.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
			// Generate JWT token with role
			String token = JwtUtil.generateToken(user.getUsername(), user.getRole().name());
			return Response.ok(new LoginResponse(token)).build();
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
	}
	
	@POST
	@Path("/recover-password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response recoverPassword(RecoverPasswordRequest request) {
		User user = service.findByEmail(request.getEmail());
		
		if (user != null) {
			// In a real implementation, you would generate a reset token and store it
			// For this example, we'll just send the email directly
			try {
				// This is a placeholder - in real app you'd have a proper reset URL with token
				String resetUrl = "https://yourapp.com/reset-password?token=placeholder_token";
				SendGridService.sendPasswordResetEmail(request.getEmail(), resetUrl);
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity("Failed to send reset email").build();
			}
		}
		
		// Return success regardless of whether user exists (for security)
		return Response.ok().build();
	}
	
	@GET
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("Missing or invalid authorization header").build();
		}

		String username;
		try {
			// Parsing verifies the signature and rejects expired tokens
			username = JwtUtil.getUsernameFromToken(authorization.substring("Bearer ".length()).trim());
		} catch (JwtException | IllegalArgumentException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid or expired token").build();
		}

		User user = service.findByUsername(username);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
		}
		return Response.ok(new UserResponse(user)).build();
	}

	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		Iterable<User> users = service.getAllUsers();
		return Response.ok(users).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Long id) {
		try {
			service.deleteUser(id);
			return Response.noContent().build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to delete user").build();
		}
	}
}
