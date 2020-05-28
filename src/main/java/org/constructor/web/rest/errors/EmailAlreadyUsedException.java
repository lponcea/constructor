package org.constructor.web.rest.errors;

public class EmailAlreadyUsedException extends BadRequestAlertException {

	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;

    /**
     * EmailAlreadyUsedException
     */
    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists");
    }
}
