package com.svecw.oes.exception;

public class oesException extends Exception {
	String message;

	public oesException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "oesException [message=" + message + "]";
	}

}
