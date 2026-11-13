package io.altar.auth_example.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.auth_example.dto.AddressRequest;
import io.altar.auth_example.dto.AddressResponse;
import io.altar.auth_example.service.AddressService;
import io.altar.auth_example.util.JwtUtil;

@RequestScoped
@Path("addresses")
@Produces(MediaType.APPLICATION_JSON)
public class AddressController {
    @Inject
    protected AddressService service;

    private long userId(String authorization) {
        return JwtUtil.getUserIdFromToken(authorization.substring("Bearer ".length()).trim());
    }

    @GET
    public Response list(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization) {
        return Response.ok(service.list(userId(authorization))).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @PathParam("id") long id) {
        AddressResponse address = service.get(userId(authorization), id);
        return address == null ? notFound() : Response.ok(address).build();
    }

    @GET
    @Path("public/{id}")
    public Response get(@PathParam("id") long id) {
        AddressResponse address = service.get(id);
        return address == null ? notFound() : Response.ok(address).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, AddressRequest request) {
        if (request == null || !request.isValid()) {
            return badRequest();
        }
        AddressResponse address = service.create(userId(authorization), request);
        return address == null ? notFound() : Response.status(Response.Status.CREATED).entity(address).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization,
            @PathParam("id") long id, AddressRequest request) {
        if (request == null || !request.isValid()) {
            return badRequest();
        }
        AddressResponse address = service.update(userId(authorization), id, request);
        return address == null ? notFound() : Response.ok(address).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorization, @PathParam("id") long id) {
        return service.delete(userId(authorization), id) ? Response.noContent().build() : notFound();
    }

    private Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).entity("Address or user not found").build();
    }

    private Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("street, city, postalCode and country are required and must be at most 255 characters").build();
    }
}
