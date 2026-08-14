package io.altar.jseproject.utils.scannerUtils;

public interface IScannerUtils {
	int getInt(String msg);

	int getInt(String msg, int min, int max);

	int getInt(String msg, int[] values);

	String getString(String msg);
	
	Long getLong(String msg);

}
