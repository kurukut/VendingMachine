package com.exceptions;

public class InsufficientAmountException extends Exception {
private String exceptionMessage;
	
	
	public InsufficientAmountException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public void getExceptionMsg(){
		System.out.println(this.exceptionMessage);
	}
}
