package io.altar.jseproject.pratica2.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.altar.jseproject.pratica2.models.DTOs.ProductDTO;
import io.altar.jseproject.pratica2.models.converters.ProductConverter;
import io.altar.jseproject.pratica2.models.entities.Product;
import io.altar.jseproject.pratica2.repositories.ProductRepository;
import io.altar.jseproject.pratica2.services.ProductService;

@RequestScoped
@Path("products")
public class ProductController
		extends EntityController<ProductService, ProductRepository, ProductConverter, Product, ProductDTO> {
	@GET
	@Path("/filter")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ProductDTO> filter(@QueryParam("iva") Integer iva, @QueryParam("discount") Integer discount,
			@QueryParam("sortBy") String sortBy) {
		System.out.println("Iva: " + iva);
		System.out.println("discount: " + discount);
		System.out.println("sortBy: " + sortBy);
		return service.requestFilter(iva, discount, sortBy).stream().map(e -> converter.toDTO(e))
				.collect(Collectors.toList());
	}
}
