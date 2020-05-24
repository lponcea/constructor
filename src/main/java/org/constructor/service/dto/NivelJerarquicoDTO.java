package org.constructor.service.dto;

import java.util.Set;

/**
 * The Class NivelJerarquicoDTO.
 */
public class NivelJerarquicoDTO {
	
	/** The curso id. */
	private Long cursoId;
	
	/** The nombre. */
	private String nombre;
	
	/** The tipo. */
	private String tipo;
	
	/** The orden nivel. */
	private int ordenNivel;
	
	/** The informacion adicional. */
	private int informacionAdicional;
	
	/** The bloques componentes. */
	private Set<BloqueComponentesDTO> bloquesComponentes;

	/**
	 * Gets the curso id.
	 *
	 * @return the curso id
	 */
	public Long getCursoId() {
		return cursoId;
	}

	/**
	 * Sets the curso id.
	 *
	 * @param cursoId the new curso id
	 */
	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the informacion adicional.
	 *
	 * @return the informacion adicional
	 */
	public int getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Sets the informacion adicional.
	 *
	 * @param informacionAdicional the new informacion adicional
	 */
	public void setInformacionAdicional(int informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	/**
	 * Gets the bloques componentes.
	 *
	 * @return the bloques componentes
	 */
	public Set<BloqueComponentesDTO> getBloquesComponentes() {
		return bloquesComponentes;
	}

	/**
	 * Sets the bloques componentes.
	 *
	 * @param bloquesComponentes the new bloques componentes
	 */
	public void setBloquesComponentes(Set<BloqueComponentesDTO> bloquesComponentes) {
		this.bloquesComponentes = bloquesComponentes;
	}

	/**
	 * Gets the orden nivel.
	 *
	 * @return the ordenNivel
	 */
	public int getOrdenNivel() {
		return ordenNivel;
	}

	/**
	 * Sets the orden nivel.
	 *
	 * @param ordenNivel the ordenNivel to set
	 */
	public void setOrdenNivel(int ordenNivel) {
		this.ordenNivel = ordenNivel;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "NivelJerarquicoDTO [cursoId=" + cursoId + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", informacionAdicional=" + informacionAdicional + ", bloquesComponentes=" + bloquesComponentes + "]";
	} 
	
	
		
}
