package io.altar.jseproject.pratica2.textinterface.states;

import io.altar.jseproject.pratica2.models.entities.Shelf;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;

public class CreateShelves extends State {
	private ShelfRepository DBS;

	public int on() {
		System.out.println("Criar prateleira");
		int capacity = SCANNER_UTILS.getInt("Insira a capacidade");
		float dailyPrice = SCANNER_UTILS.getFloat("Insira o preco diario");
		Shelf shelf = new Shelf(capacity, dailyPrice);
		DBS.add(shelf);

		return 1;
	}
}
