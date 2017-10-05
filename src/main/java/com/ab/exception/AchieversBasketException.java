package com.ab.exception;

public class AchieversBasketException extends Exception {

	private static final long serialVersionUID = -8206008998892892417L;

	public AchieversBasketException(String message, Throwable cause) {
		super(message, cause);
	}

	public AchieversBasketException(String message) {
		super(message);
	}
}
