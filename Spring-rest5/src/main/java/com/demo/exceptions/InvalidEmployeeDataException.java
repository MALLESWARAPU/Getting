package com.demo.exceptions;

public class InvalidEmployeeDataException extends Exception{
	public InvalidEmployeeDataException(String message) {
		super(message);
	}
}