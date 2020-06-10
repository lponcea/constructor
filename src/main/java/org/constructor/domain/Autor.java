/**
 * 
 */
package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Edukai
 * 
 * A Author
 *
 */
@Entity
@Table(name = "autor")
public class Autor  implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -1248773501039688662L;

	/**
	 * Long id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor")
    private Long id;
	
	/**
	 * String rol
	 */
	@JsonIgnore
	@Column(name = "rol")
    private String rol;

	/**
	 * Get
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Get
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Set
	 * @param rol the rol to set
	 */
	public void setRol(final String rol) {
		this.rol = rol;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Author [id_autor=" + id + ", rol=" + rol + "]";
	}
	
}
