package org.constructor.service.dto;

public class TipoComponenteDTO {
	
	/**
	 * Long id 
	 */
	private Long id;
	
	
	/**
	 * String nombre
	 */
	private String nombre;

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
	 * toString
	 */
	@Override
	public String toString() {
		return "TipoComponenteDTO [id=" + id + ", Nombre=" + nombre + "]";
	}

}
