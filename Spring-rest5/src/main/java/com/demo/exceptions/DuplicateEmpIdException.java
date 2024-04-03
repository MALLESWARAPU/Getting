package com.demo.exceptions;

public class DuplicateEmpIdException extends Exception{
	public DuplicateEmpIdException(String message) {
		super(message);
	}
}