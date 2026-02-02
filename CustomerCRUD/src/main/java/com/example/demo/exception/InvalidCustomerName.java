package com.example.demo.exception;

public class InvalidCustomerName extends RuntimeException {

	public InvalidCustomerName(String message) {
		super(message);
	}

}
