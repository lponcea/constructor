package org.constructor.service;

public class UsernameAlreadyUsedException extends RuntimeException {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -766492952117260011L;

	public UsernameAlreadyUsedException() {
        super("Login name already used!");
    }

}
