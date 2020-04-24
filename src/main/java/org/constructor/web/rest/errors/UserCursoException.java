package org.constructor.web.rest.errors;

public class UserCursoException extends BadRequestAlertException {

	public UserCursoException() {
		super("User with attached book","UserCursoException", "error.userbook");
	}
	
}
