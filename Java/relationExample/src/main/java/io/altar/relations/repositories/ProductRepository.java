package io.altar.relations.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.altar.relations.models.Product;

@ApplicationScoped
public class ProductRepository extends EntityRepository<Product> {
	
	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}
	
	public List<Product> getAll() {
		return entityManager.createNamedQuery(Product.GET_ALL_PRODUCTS, getEntityClass()).getResultList();
	}
}
