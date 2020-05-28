package org.constructor.web.rest.vm;

import org.constructor.service.dto.UserDTO;
import javax.validation.constraints.Size;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

	/**
	 * Int
	 */
    public static final int PASSWORD_MIN_LENGTH = 4;

    /**
	 * Int
	 */
    public static final int PASSWORD_MAX_LENGTH = 100;

    /**
     * password
     */
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    /**
     * Empty Constructor
     */
    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    /**
     * Get 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}
