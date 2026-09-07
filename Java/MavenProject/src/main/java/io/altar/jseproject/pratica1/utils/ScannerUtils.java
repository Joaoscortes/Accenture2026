package io.altar.jseproject.pratica1.utils;

import java.util.Scanner;
import java.util.Set;

/**
 * Utility class for handling user input from the console using Scanner.
 * Provides methods to read and validate integer and string values.
 * 
 * @author Joao Cortes
 */
public class ScannerUtils {
	/**********
	 * Fields
	 *********/

	/**
	 * Scanner for reading input from the console.
	 */
	private Scanner sc = new Scanner(System.in);
	/**
	 * Scanner for parsing individual input lines.
	 */
	private Scanner lineSc;

	/***********
	 * Methods
	 **********/

	/**
	 * Reads a line of input from the console.
	 * 
	 * @return the input string entered by the user
	 */
	public String getValue() {
		return sc.nextLine();
	}

	/**
	 * Checks if the provided string can be parsed as an integer.
	 * 
	 * @param value the string to check
	 * @return true if the string is an integer, false otherwise
	 */
	public boolean isInt(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextInt();
	}

	/**
	 * Checks if the provided string can be parsed as an long.
	 * 
	 * @param value the string to check
	 * @return true if the string is an long, false otherwise
	 */
	public boolean isLong(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextLong();
	}

	/**
	 * Checks if the provided string can be parsed as an float.
	 * 
	 * @param value the string to check
	 * @return true if the string is an float, false otherwise
	 */
	public boolean isFloat(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextFloat();
	}

	/**
	 * Converts the provided string to an integer. Assumes the string is a valid
	 * integer.
	 * 
	 * @param value the string to convert
	 * @return the integer value
	 */
	public int toInt(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextInt();
	}

	/**
	 * Converts the provided string to an integer. Assumes the string is a valid
	 * long.
	 * 
	 * @param value the string to convert
	 * @return the long value
	 */
	public long toLong(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextLong();
	}

	/**
	 * Converts the provided string to an float. Assumes the string is a valid
	 * float.
	 * 
	 * @param value the string to convert
	 * @return the float value
	 */
	public float toFloat(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextFloat();
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Keeps prompting until a valid integer is entered.
	 * 
	 * @param msg the prompt message
	 * @return the integer entered by the user
	 */
	public int getInt(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isInt(value)) {
				return toInt(value);
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid long from the console.
	 * Keeps prompting until a valid long is entered.
	 * 
	 * @param msg the prompt message
	 * @return the long entered by the user
	 */
	public long getLong(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isLong(value)) {
				return toLong(value);
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid float from the console.
	 * Keeps prompting until a valid float is entered.
	 * 
	 * @param msg the prompt message
	 * @return the float entered by the user
	 */
	public float getFloat(String msg) {
		do {
			System.out.println(msg);
			String value = getValue();
			if (isInt(value)) {
				return toFloat(value);
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Only accepts integers that are present in the provided array.
	 * 
	 * @param msg    the prompt message
	 * @param values the array of valid integer values
	 * @return the valid integer entered by the user
	 */
	public int getValidInt(String msg, int[] values) {
		do {
			StringBuilder validStringBuilder = new StringBuilder(msg + "(");
			for (int i : values) {
				validStringBuilder.append(" ").append(i);
			}
			validStringBuilder.append(" )");
			String validString = validStringBuilder.toString();
			int result = getInt(validString);
			for (int i : values) {
				if (result == i) {
					return result;
				}
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid long from the console. Only
	 * accepts long that are present in the provided set.
	 * 
	 * @param msg    the prompt message
	 * @param values the set of valid long values
	 * @return the valid long entered by the user
	 */
	public int getValidLong(String msg, Set<Long> values) {
		do {
			StringBuilder validStringBuilder = new StringBuilder(msg + "(");
			for (long i : values) {
				validStringBuilder.append(" ").append(i);
			}
			validStringBuilder.append(" )");
			String validString = validStringBuilder.toString();
			int result = getInt(validString);
			for (long i : values) {
				if (result == i) {
					return result;
				}
			}
		} while (true);
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Only accepts integers within the specified min and max range (inclusive).
	 * 
	 * @param msg the prompt message
	 * @param min the minimum valid value
	 * @param max the maximum valid value
	 * @return the valid integer entered by the user
	 */
	public int getValidInt(String msg, int min, int max) {
		int result;
		do {
			String myMsg = msg + " Valores validos entre " + min + " e " + max + ".";
			result = getInt(myMsg);
		} while (result < min || result > max);
		return result;
	}

}
