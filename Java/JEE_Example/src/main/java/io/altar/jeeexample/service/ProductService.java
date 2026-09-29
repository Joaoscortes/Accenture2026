package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Product;
import io.altar.jeeexample.persistence.ProductPersistence;

@ApplicationScoped
public class ProductService extends EntityService<ProductPersistence, Product> {

}
