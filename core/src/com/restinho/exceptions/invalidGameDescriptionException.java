package com.restinho.exceptions;

public class invalidGameDescriptionException extends Exception {
	public invalidGameDescriptionException(){
		super("This is not a valid game description.");
	}
}
