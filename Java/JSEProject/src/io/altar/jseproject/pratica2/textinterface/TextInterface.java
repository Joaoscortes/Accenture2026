package io.altar.jseproject.pratica2.textinterface;

import io.altar.jseproject.pratica1.utils.ScannerUtils;
import io.altar.jseproject.pratica2.model.Shelf;
import io.altar.jseproject.pratica2.repositories.ProductRepository;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;

/**
 * Provides a text-based interface for managing products and shelves. Allows
 * users to navigate menus and perform CRUD operations.
 */
public class TextInterface {
	/**
	 * Utility for reading and validating user input from the console.
	 */
	private ScannerUtils sc = new ScannerUtils();
	/**
	 * Singleton instance of the product repository.
	 */
	private ProductRepository DBP = ProductRepository.getInstance();
	/**
	 * Singleton instance of the shelf repository.
	 */
	private ShelfRepository DBS = ShelfRepository.getInstance();

	/**
	 * Displays the main menu and handles user navigation between product and shelf
	 * management screens.
	 */
	public void start() {
		int option = 0;

		do {
			System.out.println("Por favor selecione uma das seguintes opções:");
			System.out.println("\t1) Listar produtos");
			System.out.println("\t2) Listar prateleiras");
			System.out.println("\t3) Sair");
			option = sc.getValidInt("Selecionar opção : ", 1, 3);
			switch (option) {
			case 1:
				showProductsMenu();
				break;
			case 2:
				showShelvesMenu();
				break;
			case 3:
				System.out.println("Saida");
				break;
			}
		} while (option != 3);
	}

	/**
	 * Displays the products management menu and handles user actions for products.
	 * Shows all product IDs and allows creating, editing, viewing, or removing
	 * products. The available options depend on whether there are products in the
	 * repository.
	 */
	public void showProductsMenu() {
		int option = 0;
		do {
			DBP.getAllIds().forEach(id -> System.out.println("ID do produto : " + id));
			int[] options = new int[] { 1, 5 };
			System.out.println("1)\t Criar Produtos");
			if (!DBP.isEmpty()) {
				options = new int[] { 1, 2, 3, 4, 5 };
				System.out.println("2)\t Editar Produto");
				System.out.println("3)\t Consultar Produto");
				System.out.println("4)\t Remover Produto");
			}
			System.out.println("5)\t Voltar");

			option = sc.getValidInt("Selecionar opção : ", options);
			switch (option) {
			case 1:
				System.out.println("TODO - New Logic");
				break;
			case 2:
				System.out.println("TODO - Edit Logic");
				break;
			case 3:
				System.out.println("TODO - View Logic");
				break;
			case 4:
				System.out.println("TODO - Delete Logic");
				break;
			}
		} while (option != 5);
	}

	/**
	 * Displays the shelves management menu and handles user actions for shelves.
	 * Shows all shelf IDs and allows creating, editing, viewing, or removing
	 * shelves. The available options depend on whether there are shelves in the
	 * repository.
	 */
	public void showShelvesMenu() {
		int option = 0;
		do {
			DBS.getAllIds().forEach(id -> System.out.println("ID do prateleira : " + id));
			int[] options = new int[] { 1, 5 };
			System.out.println("1)\t Criar Prateleira");
			if (!DBS.isEmpty()) {
				options = new int[] { 1, 2, 3, 4, 5 };
				System.out.println("2)\t Editar Prateleira");
				System.out.println("3)\t Consultar Prateleira");
				System.out.println("4)\t Remover Prateleira");
			}
			System.out.println("5)\t Voltar");

			option = sc.getValidInt("Selecionar opção : ", options);
			switch (option) {
			case 1:
				ecraNewShelf();
				break;
			case 2:
				System.out.println("TODO - Edit Logic");
				break;
			case 3:
				System.out.println("TODO - View Logic");
				break;
			case 4:
				ecraDeleteShelf();
				break;
			}
		} while (option != 5);
	}

	/**
	 * Handles the creation of a new shelf by prompting the user for capacity and
	 * daily price. Adds the new shelf to the shelf repository.
	 */
	private void ecraNewShelf() {
		System.out.println("Criar prateleira");
		int capacity = sc.getInt("Insira a capacidade");
		float dailyPrice = sc.getFloat("Insira o preco diario");
		Shelf shelf = new Shelf(capacity, dailyPrice);
		DBS.addEntity(shelf);
	}

	/**
	 * Handles the removal of a shelf by prompting the user for a shelf ID. Removes
	 * the shelf from the shelf repository.
	 */
	private void ecraDeleteShelf() {
		System.out.println("Remover prateleira");
		long shelfId = sc.getValidLong("Introduzir ID da prateleira que quer remover : ", DBS.getAllIds());
		DBS.removeEntity(shelfId);
		System.out.println("Prateleira removida");
	}

}
