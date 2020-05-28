package org.constructor.service.dto;

public class TipoBloqueComponentesDTO {
	
	/**
	 * Long id 
	 */
	private Long id;
	
	/**
	 * String nombre
	 */
	private String nombre;
	
	/**
	 * String iconPath
	 * 
	 */
	private String iconPath;
	
	/**
	 * String tags
	 */
	private String tags;

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
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Get
	 * @return the nombre 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set
	 * @param nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get
	 * @return the iconPath
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * Set
	 * @param iconPath
	 */
	public void setIconPath(final String iconPath) {
		this.iconPath = iconPath;
	}

	/**
	 * Get
	 * @return the tags 
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Set
	 * @param tags
	 */
	public void setTags(final String tags) {
		this.tags = tags;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "TipoBloqueComponentesDTO [id=" + id + ", nombre=" + nombre + ", iconPath=" + iconPath + ", tags=" + tags
				+ "]";
	}

}
