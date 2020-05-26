package org.constructor.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A BloqueComponentes.
 */
@Entity
@Table(name = "bloque_componentes")
public class BloqueComponentes implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/** The orden componente. */
	@Column(name = "orden_componente")
    private int orden;
	
	/** The componente. */
	@OneToMany(mappedBy="bloqueComponentes", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<Componente> componente = new HashSet<>();
	
	/** The nivel jerarquico. */
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "nivel_jerarquico_id", nullable=false)
    private NivelJerarquico nivelJerarquico;
	
	
	/** The tipo bloque componentes. */
	@ManyToOne
    @JoinColumn(name = "tipo_bloque_componentes_id", nullable=false)
    private TipoBloqueComponentes tipoBloqueComponentes;

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
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the orden componente.
	 *
	 * @return the orden componente
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * Sets the orden componente.
	 *
	 * @param ordenComponente the new orden componente
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}

	/**
	 * Gets the componente.
	 *
	 * @return the componente
	 */
	public Set<Componente> getComponentes() {
		return componente;
	}

	/**
	 * Sets the componente.
	 *
	 * @param componente the new componente
	 */
	public void setComponente(Set<Componente> componente) {
		this.componente = componente;
	}

	/**
	 * Gets the nivel jerarquico.
	 *
	 * @return the nivel jerarquico
	 */
	public NivelJerarquico getNivelJerarquico() {
		return nivelJerarquico;
	}

	/**
	 * Sets the nivel jerarquico.
	 *
	 * @param nivelJerarquico the new nivel jerarquico
	 */
	public void setNivelJerarquico(NivelJerarquico nivelJerarquico) {
		this.nivelJerarquico = nivelJerarquico;
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
	public void setTipoBloqueComponentes(TipoBloqueComponentes tipoBloqueComponentes) {
		this.tipoBloqueComponentes = tipoBloqueComponentes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "BloqueComponentes [id=" + id + ", orden=" + orden + ", componentes=" + componente
				+ ", tipoBloqueComponentes=" + tipoBloqueComponentes + "]";
	}
	
	
}
