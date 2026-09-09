package io.altar.jseproject.pratica2.utils.exceptions;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MyException() {
		super("My error msg");
	}
	
	public MyException(String msg) {
		super(msg);
	}

}
