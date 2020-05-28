package org.constructor.web.rest.errors;

public class LoginAlreadyUsedException extends BadRequestAlertException {

	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;

    /**
     * LoginAlreadyUsedException
     */
    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
    }
}
