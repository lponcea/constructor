package org.constructor.service;

public class InvalidPasswordException extends RuntimeException {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1561139627907209249L;

	public InvalidPasswordException() {
        super("Incorrect password");
    }

}
