package com.restinhosoft.shakethisbottle.exception;

public class invalidGameNameException extends Exception {
	public invalidGameNameException(){
		super("This is not a valid game name.");
	}
}
