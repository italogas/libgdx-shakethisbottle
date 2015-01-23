package com.restinho.exceptions;

public class invalidFileNameException extends Exception {
	public invalidFileNameException(){
		super("This is not a valid archive name.");
	}
}
