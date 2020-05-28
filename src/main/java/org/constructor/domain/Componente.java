package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Componente.
 */
@Entity
@Table(name = "componente")
public class Componente implements Serializable {
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Long Id 
	 */
	@Id
	@OrderBy(value = "id ASC")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * String Contenido 
	 */
	@Column(name = "contenido")
    private String contenido;
	
	/**
	 * Int Version 
	 */
	@Column(name = "version")
    private int version;
	
	/**
	 * tipoComponente
	 */
	
	@ManyToOne
    @JoinColumn(name = "tipo_componente_id", nullable=false)
    private TipoComponente tipoComponente;
	
	/**
	 * bloqueComponentes
	 */
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "bloque_componentes_id", nullable=false)
    private BloqueComponentes bloqueComponentes; 

	
	/**
	 * Get
	 * @return id
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
	 * @return contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * Set
	 * @param contenido
	 */
	public void setContenido(final String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Get
	 * @return version 
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Set 
	 * @param version
	 */
	public void setVersion(final int version) {
		this.version = version;
	}

	/**
	 * Get
	 * @return tipoComponente
	 */
	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}
	
	/**
	 * Set 
	 * @param tipoComponente
	 */
	public void setTipoComponente(final TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	/**
	 * Get 
	 * @return bloqueComponentes
	 */
	public BloqueComponentes getBloqueComponentes() {
		return bloqueComponentes;
	}
	
	/**
	 * Set 
	 * @param bloqueComponentes
	 */
	public void setBloqueComponentes(final BloqueComponentes bloqueComponentes) {
		this.bloqueComponentes = bloqueComponentes;
	}

	/**
	 * TO String componente
	 */
	@Override
	public String toString() {
		return "Componente [id=" + id + ", contenido=" + contenido + ", version=" + version + "]";
	}
	
}
