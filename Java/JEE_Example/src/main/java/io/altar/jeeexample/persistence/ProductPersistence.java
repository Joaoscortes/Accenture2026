package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Product;

@ApplicationScoped
public class ProductPersistence extends EntityPersistence<Product> {

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}
}
