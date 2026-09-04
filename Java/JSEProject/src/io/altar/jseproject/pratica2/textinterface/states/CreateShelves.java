package io.altar.jseproject.pratica2.textinterface.states;

import io.altar.jseproject.pratica2.model.Shelf;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;

public class CreateShelves extends State {
	private ShelfRepository DBS = ShelfRepository.getInstance();

	public int on() {
		System.out.println("Criar prateleira");
		int capacity = sc.getInt("Insira a capacidade");
		float dailyPrice = sc.getFloat("Insira o preco diario");
		Shelf shelf = new Shelf(capacity, dailyPrice);
		DBS.addEntity(shelf);

		return 1;
	}
}
