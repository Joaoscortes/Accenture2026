package io.altar.jseproject.pratica2.controllers;

import java.util.Collection;

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
import io.altar.jseproject.pratica2.services.ProductService;
import io.altar.jseproject.pratica2.utils.exceptions.MyException;

@Path("products")
public class ProductController {
	private ProductService ps = new ProductService();

//	GET | POST / PUT | DELETE

	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return "Url is Ok ";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> getAll() {
		return ps.getAll();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		try {
			Product p = ps.get(id);
			return Response.status(200).entity(p).build();
		} catch (MyException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Product p) {
		try {
			long id = ps.add(p);
			return Response.status(200).entity(id).build();
		} catch (MyException e) {
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response edit(Product p) {
		try {
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
