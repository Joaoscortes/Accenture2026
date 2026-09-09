package io.altar.jseproject.pratica2.textinterface.states;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.altar.jseproject.pratica2.model.Product;
import io.altar.jseproject.pratica2.model.Shelf;

public class CreateProduct extends State {

	@Override
	public int on() {
		System.out.println("Criar Product");
		int[] ivaOptions = { 6, 13, 23 };
		int iva = SCANNER_UTILS.getValidInt("Insira o iva", ivaOptions);
		float pvp = SCANNER_UTILS.getFloat("Insira o pvp");
		int discount = SCANNER_UTILS.getValidInt("Insira o discount", 0, 100);
		if (ss.getShelfIdsByProductId(0L).size() == 0) {
			save(discount, iva, pvp);
		} else {
			System.out.println("Quer adicionar o produto a uma prateleira vazia?");
			int[] options = { 1, 2 };
			int option = SCANNER_UTILS.getValidInt("1) Sim,\t 2 ou 'Enter') Nao ", options);
			if (option == 1) {
				List<Long> shelfIds = new ArrayList<Long>();
				addToShelf(discount, iva, pvp, shelfIds);
			} else {
				save(discount, iva, pvp);
			}
		}

		return 1;
	}

	private void save(int discount, int iva, float pvp) {
		Product product = new Product(discount, iva, pvp);
		ps.add(product);
	}

	private void save(List<Long> shelfIds, int discount, int iva, float pvp) {
		List<Shelf> shelves = shelfIds.stream().map(entityId -> {
			return ss.get(entityId);
		}).collect(Collectors.toList());
		Product product = new Product(shelves, discount, iva, pvp);
		ps.add(product);
	}

	private void addToShelf(int discount, int iva, float pvp, List<Long> shelfIds) {
		Set<Long> options = ss.getShelfIdsByProductId(0L).stream()
				.filter(shelfId -> shelfIds.indexOf(shelfId) == -1).collect(Collectors.toSet());
		System.out.println("Prateleiras disponiveis" + options);
		long option = SCANNER_UTILS.getValidLong("Selecionar Id da prateleira ", options);
		shelfIds.add(option);
		if (options.size() == 1) {
			save(shelfIds, discount, iva, pvp);
		} else {
			System.out.println("Quer adicionar o producto a uma outra prateleira vazia?");
			int[] options2 = { 1, 2 };
			int option2 = SCANNER_UTILS.getValidInt("1) Sim,\t 2 ou 'Enter') Nao ", options2);
			if (option2 == 1) {
				addToShelf(discount, iva, pvp, shelfIds);
			} else {
				save(shelfIds, discount, iva, pvp);
			}
		}
	}

}
