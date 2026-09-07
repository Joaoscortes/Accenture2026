package pt.upacademy.examples.stateMachine;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.utils.scannerUtils.ScannerUtils;

//1. Create a "wrapper" class that models the state machine
public class MyStateMachine {
	public static void main(String[] args) {
		MyStateMachine fsm = new MyStateMachine();
		fsm.start();
	}

	// 2. states
	private State[] states = { new MenuInit(), // State 0
			new MenuProduct(), // State 1
//			new MenuShelf(), // State 2
//			new CreateProduct(), // State 3
//			new EditProduct(), // State 4
//			new ViewProduct(), // State 5
//			new DeleteProduct(), // State 6
			new CreateShelf(), // State 7
//			new EditShelf(), // State 8
//			new ViewShelf(), // State 9
//			new DeleteShelf(), // State 10
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

// 6. Create a state base class that makes the concrete states interchangeable
// 7. The State base class specifies default behavior
abstract class State {
	protected ScannerUtils sc = new ScannerUtils();

	public abstract int on();
}

class MenuInit extends State {
	public int on() {
		System.out.println("Por favor selecione uma das seguintes opções:");
		System.out.println("\t1) Listar produtos");
		System.out.println("\t2) Listar prateleiras");
		System.out.println("\t3) Sair");
		return sc.getInt("Selecionar opção : ", 1, 3);
	}
}

class MenuProduct extends State {
	public int on() {
		System.out.println("1)\t Criar Produtos");
		System.out.println("2)\t Editar Produto");
		System.out.println("3)\t Consultar Produto");
		System.out.println("4)\t Remover Produto");
		System.out.println("5)\t Voltar");
		
		int[] options = new int[] { 1, 2, 3, 4, 5 };

		return sc.getInt("Selecionar opção : ", options);
	}
}

class CreateShelf extends State {
	public int on() {
		// TODO

		return 1;
	}
}
