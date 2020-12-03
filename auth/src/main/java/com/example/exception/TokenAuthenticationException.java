package com.example.exception;

public class TokenAuthenticationException extends RuntimeException {

	public TokenAuthenticationException(String message) {
		super(message);
	}

	public TokenAuthenticationException(int a, String message) {
		super(a + message);
	}

	public TokenAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
