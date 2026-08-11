package pt.upcademy.examples.fizzbuzz;

import java.util.Scanner;

public class Fizzbuzz {

	public void init() {
		System.out.println("Num [1,40]");
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			this.resolve(sc.nextInt());
			sc.nextLine();
			sc.close();
		} else {
			this.init();
		}

	}

	private void resolve(int max) {
		for (int index = 0; index <= max; index++) {

		}
	}
}
