package org.constructor.web.rest.errors;

public class UserCursoException extends BadRequestAlertException {

	/**
	 * UserCursoException
	 */
	public UserCursoException() {
		super("User with attached book","UserCursoException", "userbook");
	}
	
}
