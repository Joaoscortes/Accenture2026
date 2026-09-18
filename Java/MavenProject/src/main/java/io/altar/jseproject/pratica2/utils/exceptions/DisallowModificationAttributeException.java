package io.altar.jseproject.pratica2.utils.exceptions;

public class DisallowModificationAttributeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DisallowModificationAttributeException() {
		super("My error msg");
	}
	
	public DisallowModificationAttributeException(String msg) {
		super(msg);
	}

}
