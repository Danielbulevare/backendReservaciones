package com.dan.backendReservaciones.error;

public class EmailAlreadyExistsException extends Exception {
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
