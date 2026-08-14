package pt.upacademy.examples.desafios;

import java.util.Scanner;

public class DesafioScanner {
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		DesafioScanner d = new DesafioScanner();
		int[] values = { 1, 2, 3, 4, 5 };
		System.out.println("Numero : { 1, 2, 3, 4, 5 }");
		System.out.println(d.getInt(values));

	}

	public int getInt(int[] values) {
		if (!sc.hasNextInt()) {
			System.out.println("Num errado, tem de ser " + values.toString());
			
		} else {
			int value = sc.nextInt();
			for (int i = 0; i < values.length; i++) {
				if (values[i] == value) {
					return value;
				}
			}
		}
		return -1;
	}

}
