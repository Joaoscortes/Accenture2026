package io.altar.jeeexample.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import io.altar.jeeexample.model.Product;
import io.altar.jeeexample.persistence.ProductPersistence;
import io.altar.jeeexample.service.ProductService;

@RequestScoped
@Path("products")
public class ProductController extends EntityController<ProductService, ProductPersistence, Product> {

}
