package io.altar.jseproject.pratica2.controllers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.pratica2.controllers.interfaces.EntityControllerInterface;
import io.altar.jseproject.pratica2.models.DTOs.EntityDTO;
import io.altar.jseproject.pratica2.models.converters.EntityConverter;
import io.altar.jseproject.pratica2.models.entities.Entity_;
import io.altar.jseproject.pratica2.repositories.EntityRepository;
import io.altar.jseproject.pratica2.services.EntityService;

public abstract class EntityController<S extends EntityService<R, E>, R extends EntityRepository<E>, C extends EntityConverter<E, D>, E extends Entity_, D extends EntityDTO>
		implements EntityControllerInterface<D> {

	@Inject
	protected S service;
	@Inject
	protected C converter;

	@Context
	protected UriInfo context;

	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return "Url : " + context.getRequestUri().toString() + " is Ok";
	}

	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public long count() {
		return service.size();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<D> get() {
		return service.getAll().stream().map(entity -> converter.toDTO(entity)).collect(Collectors.toList());
	}

	@GET
	@Path("getIds")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Long> getIds() {
		return service.getAllIds();
	}

	@GET
	@Path("isEmpty")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isEmpty() {
		return service.isEmpty();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(D entityDTO) {
		try {
			long currentId = service.add(converter.toEntity(entityDTO));
			return Response.status(200).entity(currentId).build();
		} catch (UnsupportedOperationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(List<D> entities) {
		entities.forEach(entity -> this.add(entity));
		return "Done";
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		try {
			E entity = service.get(id);
			return Response.status(200).entity(converter.toDTO(entity)).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(D entityDTO) {
		try {
			E entity = converter.toEntity(entityDTO);
			service.edit(entity);
			return Response.ok().build();
		} catch (UnsupportedOperationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response remove(@PathParam("id") long id) {
		try {
			service.remove(id);
			return Response.ok().build();
		} catch (UnsupportedOperationException e) {
			return Response.status(403).entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
}
