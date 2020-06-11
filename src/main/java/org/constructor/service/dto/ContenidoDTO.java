package org.constructor.service.dto;

/**
 * The Class ContenidoDTO.
 */
public class ContenidoDTO {
	
	/** The id. */
	private Long id;
	
	/** The contenido. */
	private String contenido;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the contenido.
	 *
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * Sets the contenido.
	 *
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "ContenidoDTO [id=" + id + ", contenido=" + contenido + "]";
	}
	
}
