package org.constructor.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadRequestAlertException extends AbstractThrowableProblem {
  
	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;

    /**
     * String entityName
     */
    private final String entityName;

    /*+
     * String errorKey
     */
    private final String errorKey;

    /**
     *  Constructor
     * @param defaultMessage
     * @param entityName
     * @param errorKey
     */
    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
        this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey);
    }

    /**
     * Constructor
     * @param type
     * @param defaultMessage
     * @param entityName
     * @param errorKey
     */
    public BadRequestAlertException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    /**
     * Get
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Get
     * @return the errorKey
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * getAlertParameters
     * @param entityName
     * @param errorKey
     * @return
     */
    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}
