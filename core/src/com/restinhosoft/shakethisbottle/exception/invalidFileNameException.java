package com.restinhosoft.shakethisbottle.exception;

public class invalidFileNameException extends Exception {
	public invalidFileNameException(){
		super("This is not a valid archive name.");
	}
}
