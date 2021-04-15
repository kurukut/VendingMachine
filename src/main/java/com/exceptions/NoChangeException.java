package com.exceptions;

public class NoChangeException  extends Exception{
	private String exceptionMessage;
	
	
	public NoChangeException(String exceptionMessage){
		super(exceptionMessage);
	}
	
	public void getExceptionMsg(){
		System.out.println(this.exceptionMessage);
	}
}
