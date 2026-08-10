package pt.upcademy.examples.hello;

import java.util.Scanner;

public class Hello {
	private Scanner sc = new Scanner(System.in);

	protected void start() {
		System.out.println("What´s your name and age?");
		String userInfo = sc.nextLine();
		Scanner scAut = new Scanner(userInfo);
		String name = scAut.next();
		int age = 0;
		if (scAut.hasNextInt()) {
			age = scAut.nextInt();
		}

		scAut.close();

		System.out.println("Hello! " + name + " , " + age);
	}
}
