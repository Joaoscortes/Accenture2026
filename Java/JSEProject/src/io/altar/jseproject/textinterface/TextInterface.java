package io.altar.jseproject.textinterface;

import io.altar.jseproject.utils.ScannerUtils;

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
	 * Options include creating, editing, viewing, and removing products.
	 */
	public void showProductsMenu() {
		int option = 0;
		do {
			System.out.println("Por favor selecione uma das seguintes opções:");
			System.out.println("1)\t Criar produtos");
			System.out.println("2)\t Editar produtos");
			System.out.println("3)\t Consultar produtos");
			System.out.println("4)\t Remover produtos");
			System.out.println("5)\t Voltar");
			int[] options = { 1, 2, 3, 4, 5 };
			option = sc.getValidInt("Selecionar opção : ", options);
			switch (option) {
			case 1:
				System.out.println("TODO - New Logic"); // TODO - New Logic
				break;
			case 2:
				System.out.println("TODO - Edit Logic"); // TODO - Edit Logic
				break;
			case 3:
				System.out.println("TODO - View Logic"); // TODO - View Logic
				break;
			case 4:
				System.out.println("TODO - Delete Logic"); // TODO - Delete Logic
				break;
			}
		} while (option != 5);
	}

	/**
	 * Displays the shelves management menu and handles user actions for shelves.
	 * Options include creating, editing, viewing, and removing shelves.
	 */
	public void showShelvesMenu() {
		int option = 0;
		do {
			System.out.println("Por favor selecione uma das seguintes opções:");
			System.out.println("1)\t Criar prateleiras");
			System.out.println("2)\t Editar prateleiras");
			System.out.println("3)\t Consultar prateleiras");
			System.out.println("4)\t Remover prateleiras");
			System.out.println("5)\t Voltar");
			int[] options = { 1, 2, 3, 4, 5 };
			option = sc.getValidInt("Selecionar opção : ", options);
			switch (option) {
			case 1:
				System.out.println("TODO - New Logic"); // TODO - New Logic
				break;
			case 2:
				System.out.println("TODO - Edit Logic"); // TODO - Edit Logic
				break;
			case 3:
				System.out.println("TODO - View Logic"); // TODO - View Logic
				break;
			case 4:
				System.out.println("TODO - Delete Logic"); // TODO - Delete Logic
				break;
			}
		} while (option != 5);
	}
}
