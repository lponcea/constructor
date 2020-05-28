package org.constructor.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class UserNotActivatedException extends AuthenticationException {

	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;

    /**
     * UserNotActivatedException
     * @param message
     */
    public UserNotActivatedException(String message) {
        super(message);
    }

    /**
     * UserNotActivatedException
     * @param message
     * @param t
     */
    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
