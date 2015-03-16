package com.restinhosoft.exception;

public class exceededCharsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public exceededCharsException(){
		super("Exceeded the number of characters");
	}
}
