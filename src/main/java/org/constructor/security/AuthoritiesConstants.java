package org.constructor.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

	/**
	 * Constants ADMIN
	 */
    public static final String ADMIN = "ROLE_ADMIN";
    
    /**
     * Constants USER
     */
    public static final String USER = "ROLE_USER";
    
    /**
     * Constants ANONYMOUS
     */
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
