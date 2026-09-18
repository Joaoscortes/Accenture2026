package io.altar.jseproject.pratica2.controllers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.pratica2.models.DTOs.ShelfDTO;
import io.altar.jseproject.pratica2.models.converters.ShelfConverter;
import io.altar.jseproject.pratica2.models.entities.Shelf;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;
import io.altar.jseproject.pratica2.services.ShelfService;

@RequestScoped
@Path("shelves")
public class ShelfController extends EntityController<ShelfService, ShelfRepository, ShelfConverter, Shelf, ShelfDTO> {

	@GET
	@Path("empties")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Shelf> getEmptyShelves() {
		return service.getEmptyShelves();
	}

	@GET
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShelfDTO> getShelvesByProductID(@PathParam("id") long id) {
		return service.findByProductId(id).stream().map(self -> converter.toDTO(self)).collect(Collectors.toList());
	}

	@DELETE
	@Path("/product/{id}")
	public Response removeProductsByProductId(@PathParam("id") long id) {
		service.removeProductsByProductId(id);
		return Response.ok().build();
	}
}
