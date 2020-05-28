package org.constructor.multimedia.response;

public class MultimediaResponse {
	
	/**
	 *  boolean status
	 */
	private boolean status;
	
	/**
	 * String message
	 */
	private String message;
	
	/**
	 * Get
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	
	/**
	 * Set
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Get 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
