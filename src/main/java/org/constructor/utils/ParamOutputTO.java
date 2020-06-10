/**
 * 
 */
package org.constructor.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.constructor.web.rest.errors.ErrorConstants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * @author Edukai
 */
@JsonInclude(Include.NON_NULL)
public class ParamOutputTO<T>   implements Serializable{

	/**
	 * Default Serializable
	 */
	private static final long serialVersionUID = 6986753086823845132L;
	
	/**
	 * Data
	 */
	private T data;
	/**
	 * result code
	 */
	private Boolean success;
	/**
	 * result message
	 */
	private String message;

	/**
	 * Default constructor
	 */
	public ParamOutputTO() {
		this.success = Boolean.TRUE;
		this.message = ErrorConstants.STATUS_MENSSAGE_200;
	}

	/**
	 * Constructor with data argument
	 * 
	 * @param data data object
	 */
	public ParamOutputTO(final T data) {
		this.data = data;
		this.success = Boolean.TRUE;
		this.message = ErrorConstants.STATUS_MENSSAGE_200;
	}

	/**
	 * Constructor with data argument
	 * 
	 * @param data data object
	 */
	public ParamOutputTO(final T data, final Boolean success, final String message) {
		this.data = data;
		this.success = success;
		this.message = message;
	}

	/**
	 * get data
	 * 
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * set data
	 * 
	 * @param data the data to set
	 */
	public void setData(final T data) {
		this.data = data;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(final Boolean success) {
		this.success = success;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
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
