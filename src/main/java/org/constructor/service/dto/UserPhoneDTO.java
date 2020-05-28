package org.constructor.service.dto;

import java.util.Set;

import org.constructor.domain.PhoneNumber;
import org.constructor.domain.User;

public class UserPhoneDTO {
	
	/**
	 * User
	 */
	private User user;
	
	/**
	 * phoneNumbers
	 */
	private Set<PhoneNumber>phoneNumbers;
	
	/**
	 * Get
	 * @return the user 
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Set
	 * @param user
	 */
	public void setUser(final User user) {
		this.user = user;
	}
	
	/**
	 * Get 
	 * @return the phoneNumbers
	 */
	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	
	/**
	 * Set
	 * @param phoneNumbers
	 */
	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString() { 
		return "UserPhoneDTO [user=" + user + ", phoneNumbers=" + phoneNumbers + "]";
	}
	
	
	
}
