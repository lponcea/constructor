package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class Contenido.
 */
@Entity
@Table(name = "contenido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contenido  implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/** The contenido. */
	@Column(name = "contenido")
	private String contenido;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "componente_id")
    @JsonIgnore
	private Componente componente;

	/**
	 * Instantiates a new contenido.
	 *
	 * @param id the id
	 * @param contenido the contenido
	 */
	public Contenido(Long id, String contenido) {
		super();
		this.id = id;
		this.contenido = contenido;
	}

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

	/**
	 * @return the componente
	 */
	public Componente getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	
}
