package io.altar.jseproject.pratica2.models.converters;

import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.pratica2.models.DTOs.ProductDTO;
import io.altar.jseproject.pratica2.models.entities.Product;
import io.altar.jseproject.pratica2.models.entities.Shelf;
import io.altar.jseproject.pratica2.services.ShelfService;

@RequestScoped
public class ProductConverter extends EntityConverter<Product, ProductDTO> {

	@Inject
	private ShelfService SS;

	public Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		if (productDTO.getId() > 0) {
			product.setId(productDTO.getId());
			SS.removeProductsByProductId(productDTO.getId());
		}
		product.setIva(productDTO.getIva());
		product.setPvp(productDTO.getPvp());
		product.setDiscount(productDTO.getDiscount());
		product.setShelves(productDTO.getShelfIds().stream().map(entityId -> {
			Shelf shelf = SS.get(entityId);
			shelf.setProduct(product);
			return shelf;
		}).collect(Collectors.toList()));
		return product;
	}

	public ProductDTO toDTO(Product product) {
		return new ProductDTO(product.getId(),
				product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()), product.getDiscount(),
				product.getIva(), product.getPvp());
	}
}
