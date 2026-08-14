package pt.upacademy.examples.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hello {
	private Scanner sc = new Scanner(System.in);
	private List<User> users = new ArrayList<User>();

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

		User user = new User(name, age);

		users.add(user);

		System.out.println("Hello! " + user.toString());
	}
}
