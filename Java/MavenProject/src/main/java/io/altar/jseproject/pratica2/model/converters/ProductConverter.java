package io.altar.jseproject.pratica2.model.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.pratica2.model.Product;
import io.altar.jseproject.pratica2.model.Shelf;
import io.altar.jseproject.pratica2.model.dtos.ProductDTO;
import io.altar.jseproject.pratica2.services.ShelfService;

@RequestScoped
public class ProductConverter extends EntityConverter<Product, ProductDTO> {

	@Inject
	private ShelfService ss;

	@Override
	public Product toEntity(ProductDTO dto) {
		Product product = new Product();
		if (dto.getId() > 0) {
			product.setId(dto.getId());
		}
		product.setDiscount(dto.getDiscount());
		product.setIva(dto.getIva());
		product.setPvp(dto.getPvp());
		List<Shelf> shelves = new ArrayList<Shelf>();
		if (dto.getShelvesIds().size() > 0) {
			shelves = dto.getShelvesIds().stream().map(shelfId -> {
				// TODO check if product exist on shelf
				return ss.get(shelfId);
			}).collect(Collectors.toList());
		}
		product.setShelves(shelves);

		return product;
	}

	@Override
	public ProductDTO toDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		if (dto.getId() > 0) {
			dto.setId(product.getId());
		}
		dto.setDiscount(product.getDiscount());
		dto.setIva(product.getIva());
		dto.setPvp(product.getPvp());
		dto.setShelvesIds(product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()));
		return dto;
	}

}
