package io.altar.jseproject.pratica2.services;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.pratica2.model.Product;
import io.altar.jseproject.pratica2.repositories.ProductRepository;

@ApplicationScoped
public class ProductService extends EntityService<ProductRepository, Product>{
	
//	public ProductService() {
//		repo = ProductRepository.getInstance();
//	}

	@Override
	public void edit(Product p) {
		repo.edit(p);
		
	}
}
