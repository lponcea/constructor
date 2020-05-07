package org.constructor.service.dto;

import java.util.Set;

import org.constructor.domain.PhoneNumber;
import org.constructor.domain.User;

public class UserPhoneDTO {
	
	private User user;
	private Set<PhoneNumber>phoneNumbers;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	@Override
	public String toString() { 
		return "UserPhoneDTO [user=" + user + ", phoneNumbers=" + phoneNumbers + "]";
	}
	
	
	
}
