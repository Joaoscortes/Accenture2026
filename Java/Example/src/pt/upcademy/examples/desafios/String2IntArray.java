package pt.upcademy.examples.desafios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class String2IntArray {
	// "1 2 3"
	// [1,2,3]
	private String s; // "1 2 3 4"

	public String2IntArray(String s) {
		this.s = s;
	}

	public List<Integer> resolve() {
		// s.split(" ");
		List<Integer> result = new ArrayList<Integer>();

		Scanner sc = new Scanner(s);
		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				result.add(sc.nextInt());
			} else {
				sc.next();
			}
		}
		sc.close();

		return result;

	}

	public int[] resolveIntArray() {
		return Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String2IntArray s2a = new String2IntArray("1 2 a 3 4");
		s2a.resolve();
	}

}
