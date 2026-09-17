package io.altar.jseproject.pratica2.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.pratica2.model.Product;
import io.altar.jseproject.pratica2.model.converters.EntityConverter;
import io.altar.jseproject.pratica2.model.converters.ProductConverter;
import io.altar.jseproject.pratica2.model.dtos.ProductDTO;
import io.altar.jseproject.pratica2.services.ProductService;
import io.altar.jseproject.pratica2.utils.exceptions.MyException;

@RequestScoped
@Path("products")
public class ProductController {

	@Inject
	private ProductService ps;
	
	@Inject
	private ProductConverter pc;

//	GET | POST / PUT | DELETE

	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return "Url is Ok ";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ProductDTO> getAll() {
		return ps.getAll().stream().map(e -> pc.toDTO(e)).collect(Collectors.toList());
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		try {
			ProductDTO p = pc.toDTO(ps.get(id));
			return Response.status(200).entity(p).build();
		} catch (MyException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(ProductDTO dto) {
		try {
			Product p = pc.toEntity(dto);
			long id = ps.add(p);
			return Response.status(200).entity(id).build();
		} catch (MyException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(ProductDTO dto) {
		try {
			Product p = pc.toEntity(dto);
			ps.edit(p);
			return Response.ok().build();
		} catch (MyException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") long id) {
		try {
			ps.remove(id);
			return Response.ok().build();
		} catch (MyException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

}
