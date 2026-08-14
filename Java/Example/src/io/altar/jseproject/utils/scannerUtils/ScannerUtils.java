package io.altar.jseproject.utils.scannerUtils;

import java.util.Scanner;

public class ScannerUtils implements IScannerUtils {

	private Scanner sc = new Scanner(System.in);

	@Override
	public int getInt(String msg) {
		System.out.println(msg);
		return sc.nextInt();
	}

	@Override
	public int getInt(String msg, int min, int max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String msg, int[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getString(String msg) {
		System.out.println(msg);

		return sc.nextLine();
	}

	@Override
	public Long getLong(String msg) {
		System.out.println(msg);
		return sc.nextLong();
	}

}
