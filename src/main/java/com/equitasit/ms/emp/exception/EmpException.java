package com.equitasit.ms.emp.exception;

public class EmpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;

	public EmpException() {

	}

	public EmpException(String message) {
		super(message);
	}

	public EmpException(String message, Throwable cause) {
		super(message, cause);
	}

}
