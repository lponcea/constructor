package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	 * Long Version 
	 */
	@Column(name = "version")
    private Long version;
	
	@Column(name = "orden")
    private Long orden;
	
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
	
	@OneToOne(mappedBy = "componente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Contenido contenido;

	
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
	 * @return version 
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Set 
	 * @param version
	 */
	public void setVersion(final Long version) {
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
	
	/**
	 * @return the contenido
	 */
	public Contenido getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "Componente [id=" + id + ", contenido=" + contenido + ", version=" + version + ", orden=" + orden + "]";
	}

	
}
