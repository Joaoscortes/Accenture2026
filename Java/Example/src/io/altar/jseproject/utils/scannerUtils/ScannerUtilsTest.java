package io.altar.jseproject.utils.scannerUtils;

public class ScannerUtilsTest {

	public static void main(String[] args) {
		ScannerUtils sc = new ScannerUtils();

		// int -> (msg)
		int value = sc.getInt("Preço do produto");
		System.out.println("Expect any int : " + value);

		// int -> (msg, [1,2,3])
		int[] options = { 1, 2, 3 };
		int value1 = sc.getInt("", options);
		System.out.println("Expect [1,2,3] : " + value1);

		// int -> (msg, [1-5])
		int value2 = sc.getInt("", 1, 5);
		System.out.println(value2);

		// string -> (msg)
		String productname = sc.getString("Nome do produto.");
		System.out.println(productname);

	}

}
