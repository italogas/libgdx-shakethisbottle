package com.restinhosoft.shakethisbottle.exception;

public class invalidGameNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidGameNameException(){
		super("This is not a valid game name.");
	}
}
