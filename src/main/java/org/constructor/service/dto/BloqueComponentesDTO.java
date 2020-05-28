package org.constructor.service.dto;

import java.util.Set;

import org.constructor.domain.TipoBloqueComponentes;


/**
 * The Class BloqueComponentesDTO.
 */
public class BloqueComponentesDTO {
	
	/** The id. */
	private Long id;
	
	/** The orden. */
	private int orden;
	
	/** The tipo bloque componentes. */
	private TipoBloqueComponentes tipoBloqueComponentes;
	
	/** The componentes. */
	private Set<ComponenteDTO> componentes;
	
	

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
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden the new orden
	 */
	public void setOrden(final int orden) {
		this.orden = orden;
	}

	/**
	 * Gets the tipo bloque componentes.
	 *
	 * @return the tipo bloque componentes
	 */
	public TipoBloqueComponentes getTipoBloqueComponentes() {
		return tipoBloqueComponentes;
	}

	/**
	 * Sets the tipo bloque componentes.
	 *
	 * @param tipoBloqueComponentes the new tipo bloque componentes
	 */
	public void setTipoBloqueComponentes(final TipoBloqueComponentes tipoBloqueComponentes) {
		this.tipoBloqueComponentes = tipoBloqueComponentes;
	}

	/**
	 * Gets the componentes.
	 *
	 * @return the componentes
	 */
	public Set<ComponenteDTO> getComponentes() {
		return componentes;
	}

	/**
	 * Sets the componentes.
	 *
	 * @param componentes the new componentes
	 */
	public void setComponentes(Set<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "BloqueComponentesDTO [id=" + id + ", orden=" + orden + ", tipoBloqueComponentes="
				+ tipoBloqueComponentes + ", componentes=" + componentes + "]";
	}

	

}
