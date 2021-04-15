package com.exceptions;

public class InsufficientProductQuantityException extends Exception {
	private String exceptionMessage;
	
	
	public InsufficientProductQuantityException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public void getExceptionMsg(){
		System.out.println(this.exceptionMessage);
	}
	
}
