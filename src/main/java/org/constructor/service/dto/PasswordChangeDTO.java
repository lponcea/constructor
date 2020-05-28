package org.constructor.service.dto;

/**
 * A DTO representing a password change required data - current and new password.
 */
public class PasswordChangeDTO {
	
	/**
	 * String currentPassword
	 */
    private String currentPassword;
    
    /**
     *  String newPassword
     */
    private String newPassword;

    /**
     * PasswordChangeDTO
     */
    public PasswordChangeDTO() {
        // Empty constructor needed for Jackson.
    }

    /**
     * Constructor con parametros 
     * @param currentPassword
     * @param newPassword
     */
    public PasswordChangeDTO(final String currentPassword,final  String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    /**
     * Get 
     * @return the currentPassword
     */
    public String getCurrentPassword() {

        return currentPassword;
    }

    /**
     * Set
     * @param currentPassword
     */
    public void setCurrentPassword(final String currentPassword) {
        this.currentPassword = currentPassword;
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
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
