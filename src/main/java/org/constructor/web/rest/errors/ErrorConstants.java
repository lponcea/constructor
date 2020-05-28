package org.constructor.web.rest.errors;

import java.net.URI;

/**
 * 
 * Error Constants
 *
 */
public final class ErrorConstants {

	/**
	 * ERR_CONCURRENCY_FAILURE 
	 */
    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    
    /**
     * ERR_VALIDATION
     */
    public static final String ERR_VALIDATION = "error.validation";
    
    /**
     * PROBLEM_BASE_URL 
     */
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    
    /**
     * DEFAULT_TYPE
     */
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    
    /**
     * CONSTRAINT_VIOLATION_TYPE
     */
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    
    /**
     * INVALID_PASSWORD_TYPE
     */
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    
    /**
     * EMAIL_ALREADY_USED_TYPE
     */
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    
    /**
     * LOGIN_ALREADY_USED_TYPE
     */
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
    
    /**
     * EMAIL_NOT_FOUND_TYPE
     */
    public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");

    private ErrorConstants() {
    }
}
