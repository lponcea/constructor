package org.constructor.web.rest.vm;

/**
 * View Model object for storing the user's key and password.
 */
public class KeyAndPasswordVM {

	/**
	 * String key
	 */
    private String key;

    /**
     * String newPassword
     */
    private String newPassword;

    /**
     * Get 
     * @return the key 
     */
    public String getKey() {
        return key;
    }

    /**
     * Set
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Get
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Set
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
