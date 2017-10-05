package com.ab.exception;

public class AchieversBasketRuntimeException extends RuntimeException{
	private static final long serialVersionUID = -5063156378490476742L;

	public AchieversBasketRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AchieversBasketRuntimeException(String message) {
		super(message);
	}
}
