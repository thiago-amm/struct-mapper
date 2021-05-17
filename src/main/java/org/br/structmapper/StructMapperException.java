package org.br.structmapper;

public class StructMapperException extends RuntimeException {

	private static final long serialVersionUID = -4012645317276652321L;

	public StructMapperException() {
		super();
	}

	public StructMapperException(String message) {
		super(message);
	}

	public StructMapperException(String message, Throwable cause) {
		super(message, cause);
	}

	public StructMapperException(Throwable cause) {
		super(cause);
	}

}
