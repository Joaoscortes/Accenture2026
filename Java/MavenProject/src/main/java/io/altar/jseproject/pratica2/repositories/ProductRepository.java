package io.altar.jseproject.pratica2.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.pratica2.models.entities.Product;

@ApplicationScoped
public class ProductRepository extends EntityRepository<Product> {

	public List<Product> requestFilter(Integer iva, Integer discount, String sortBy) {
		List<Product> result = getAll().stream().filter(p -> iva == null || p.getIva() == iva)
				.filter(p -> discount == null || p.getDiscount() == discount).collect(Collectors.toCollection(ArrayList::new));

		if (sortBy != null && !sortBy.isEmpty()) {
			Comparator<Product> comparator = switch (sortBy) {
			case "iva" -> Comparator.comparingInt(Product::getIva);
			case "discount" -> Comparator.comparingInt(Product::getDiscount);
			case "pvp" -> Comparator.comparingDouble(Product::getPvp);
			case "id" -> Comparator.comparingLong(Product::getId);
			default -> throw new IllegalArgumentException("Invalid sortBy field: " + sortBy);
			};
			result.sort(comparator);
		}

		return result;
	}
}