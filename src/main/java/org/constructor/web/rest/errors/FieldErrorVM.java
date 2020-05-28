package org.constructor.web.rest.errors;

import java.io.Serializable;

public class FieldErrorVM implements Serializable {

	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;
    
    /**
     * String objectName
     */
    private final String objectName;
    
    /**
     * String field
     */
    private final String field;
    
    /**
     * String message
     */
    private final String message;

    /**
     * Constructor 
     * @param dto
     * @param field
     * @param message
     */
    public FieldErrorVM(String dto, String field, String message) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
    }

    /**
     * Get
     * @return the objectName
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Get
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * Get
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
