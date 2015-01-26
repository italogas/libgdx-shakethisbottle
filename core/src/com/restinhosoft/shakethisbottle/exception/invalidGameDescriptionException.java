package com.restinhosoft.shakethisbottle.exception;

public class invalidGameDescriptionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidGameDescriptionException(){
		super("This is not a valid game description.");
	}
}
