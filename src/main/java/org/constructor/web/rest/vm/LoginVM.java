package org.constructor.web.rest.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View Model object for storing a user's credentials.
 */
public class LoginVM {

	/**
	 * String username
	 */
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    /**
     * String password
     */
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    /**
     * Boolean rememberMe
     */
    private Boolean rememberMe;

    /**
     * Get 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Get
     * @return the rememberMe
     */
    public Boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * Set
     * @param rememberMe
     */
    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "LoginVM{" +
            "username='" + username + '\'' +
            ", rememberMe=" + rememberMe +
            '}';
    }
}
