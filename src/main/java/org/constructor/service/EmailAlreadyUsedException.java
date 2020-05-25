package org.constructor.service;

public class EmailAlreadyUsedException extends RuntimeException {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }

}
