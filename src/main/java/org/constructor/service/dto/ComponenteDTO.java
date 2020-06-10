package org.constructor.service.dto;

import org.constructor.domain.TipoComponente;

/**
 * The Class ComponenteDTO.
 */
public class ComponenteDTO {
	
	/** The id. */
	private Long id;
	
	/** The tipo componente. */
	private TipoComponente tipoComponente;
	
	/** The contenido. */
	private String contenido;
	
	/** The version. */
	private Long version;
	
	/** The orden. */
	private Long orden;

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
	 * Gets the tipo componente.
	 *
	 * @return the tipo componente
	 */
	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	/**
	 * Sets the tipo componente.
	 *
	 * @param tipoComponente the new tipo componente
	 */
	public void setTipoComponente(TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
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
	 * @param contenido the new contenido
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the orden
	 */
	public Long getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		return "ComponenteDTO [id=" + id + ", tipoComponente=" + tipoComponente + ", contenido=" + contenido
				+ ", version=" + version + ", orden=" + orden + "]";
	}
	
}
