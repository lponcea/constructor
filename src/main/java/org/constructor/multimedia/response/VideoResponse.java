package org.constructor.multimedia.response;

import org.constructor.utils.ParamOutputTO;

public class VideoResponse<T> extends ParamOutputTO<T> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * String name
	 */
	private String name;
	
	/**
	 * String path
	 */
	private String path;

	/**
	 * Get 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
}
