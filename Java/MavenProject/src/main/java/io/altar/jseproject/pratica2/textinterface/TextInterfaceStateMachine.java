package io.altar.jseproject.pratica2.textinterface;

import io.altar.jseproject.pratica2.textinterface.states.CreateProduct;
import io.altar.jseproject.pratica2.textinterface.states.CreateShelves;
import io.altar.jseproject.pratica2.textinterface.states.DeleteProduct;
import io.altar.jseproject.pratica2.textinterface.states.DeleteShelf;
import io.altar.jseproject.pratica2.textinterface.states.EditProduct;
import io.altar.jseproject.pratica2.textinterface.states.EditShelf;
import io.altar.jseproject.pratica2.textinterface.states.MenuInit;
import io.altar.jseproject.pratica2.textinterface.states.MenuProducts;
import io.altar.jseproject.pratica2.textinterface.states.MenuShelf;
import io.altar.jseproject.pratica2.textinterface.states.State;
import io.altar.jseproject.pratica2.textinterface.states.ViewProduct;
import io.altar.jseproject.pratica2.textinterface.states.ViewShelf;

public class TextInterfaceStateMachine {
	// 2. states
	private State[] states = { new MenuInit(), // State 0
			new MenuProducts(), // State 1
			new MenuShelf(), // State 2
			new CreateProduct(), // State 3
			new EditProduct(), // State 4
			new ViewProduct(), // State 5
			new DeleteProduct(), // State 6
			new CreateShelves(), // State 7
			new EditShelf(), // State 8
			new ViewShelf(), // State 9
			new DeleteShelf(), // State 10
	};
	// 3. current
	private int current = 0;
	// 4. transitions
	private int[][] transition = { { 1, 2 }, // State 0
			{ 3, 4, 5, 6, 0 }, // State 1
			{ 7, 8, 9, 10, 0 }, // State 2
			{ 1 }, // State 3
			{ 1 }, // State 4
			{ 1 }, // State 5
			{ 1 }, // State 6
			{ 2 }, // State 7
			{ 2 }, // State 8
			{ 2 }, // State 9
			{ 2 }, // State 10
	};

	// 5. All client requests are simply delegated to the current state object
	public void start() {

		while (true) {
			int option = states[current].on();
			if (current == 0 && option == 3) {
				System.out.println("Sair.");
				break;
			}
			current = transition[current][option - 1];
		}
	}
}
