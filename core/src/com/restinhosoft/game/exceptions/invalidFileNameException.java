package com.restinhosoft.game.exceptions;

public class invalidFileNameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidFileNameException(){
		super("This is not a valid archive name.");
	}
}