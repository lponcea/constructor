/**
 * 
 */
package org.constructor.service.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Edukai
 *
 */
public class MultimediaDTO implements Serializable  {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = -8505792956918813479L;
	
	
	/**
	 * Id Long
	 */
	private String id;
	
	/**
	 * MultipartFile
	 */
	private MultipartFile file;
	

	/**
	 * Empty constructor
	 */
	public MultimediaDTO() {
		//Default constructor
	}


	/**
	 * Constructor con parametros 
	 * @param id de id 
	 * @param file de file
	 */
	public MultimediaDTO(final String id,final  MultipartFile file) {
		this.id = id;
		this.file = file;
	}


	/**
	 * Get 
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * Set
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}


	/**
	 * Get
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}


	/**
	 * Set
	 * @param file the file to set
	 */
	public void setFile(final MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * Returns a string representation of the object. In general, the toString
	 * method returns a string that "textually represents" this object. The result
	 * should be a concise but informative representation that is easy for a person
	 * to read. It is recommended that all subclasses override this method.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	

}
