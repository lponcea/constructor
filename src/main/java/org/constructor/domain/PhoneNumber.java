package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A PhoneNumber.
 */
@Entity
@Table(name = "phone_number")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PhoneNumber implements Serializable {
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Long id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * User user
	 */
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	/**
	 * Country country
	 */
	@ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
	
	/**
	 * Long phoneNumbe
	 */
	@Column(name = "phone_number")
    private Long phoneNumber;
	

	/**
	 * Get
	 * @return the id 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Get
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * user
	 * @param user
	 * @return the this 
	 */
	public PhoneNumber user(User user) {
        this.user = user;
        return this;
    }


	/**
	 * Set
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Get
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * country
	 * @param country
	 * @return the this
	 */
	public PhoneNumber country(Country country) {
        this.country = country;
        return this;
    }

	/**
	 * Set
	 * @param country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Get
	 * @return the phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set
	 * @param phoneNumber
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "PhoneNumber [id=" + id + ", country=" + country + ", phoneNumber=" + phoneNumber + "]";
	}


	
}
