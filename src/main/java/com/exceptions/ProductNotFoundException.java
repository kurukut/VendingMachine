package com.exceptions;

public class ProductNotFoundException extends Exception{
	private String exceptionMessage;
	
	
	public ProductNotFoundException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public void getExceptionMsg(){
		System.out.println(this.exceptionMessage);
	}
	
}
