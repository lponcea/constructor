package org.constructor.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A BloqueComponentes.
 */
@Entity
@Table(name = "bloque_componentes")
public class BloqueComponentes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "orden_componente")
    private int ordenComponente;
	
	@OneToMany(mappedBy="bloqueComponentes", fetch = FetchType.EAGER)
	private Set<Componente> componente = new HashSet<>();
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "nivel_jerarquico_id", nullable=false)
    private NivelJerarquico nivelJerarquico;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "tipo_bloque_componentes_id", nullable=false)
    private TipoBloqueComponentes tipoBloqueComponentes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrdenComponente() {
		return ordenComponente;
	}

	public void setOrdenComponente(int ordenComponente) {
		this.ordenComponente = ordenComponente;
	}

	public Set<Componente> getComponente() {
		return componente;
	}

	public void setComponente(Set<Componente> componente) {
		this.componente = componente;
	}

	public NivelJerarquico getNivelJerarquico() {
		return nivelJerarquico;
	}

	public void setNivelJerarquico(NivelJerarquico nivelJerarquico) {
		this.nivelJerarquico = nivelJerarquico;
	}

	public TipoBloqueComponentes getTipoBloqueComponentes() {
		return tipoBloqueComponentes;
	}

	public void setTipoBloqueComponentes(TipoBloqueComponentes tipoBloqueComponentes) {
		this.tipoBloqueComponentes = tipoBloqueComponentes;
	}

	@Override
	public String toString() {
		return "BloqueComponentes [id=" + id + ", ordenComponente=" + ordenComponente + ", componente=" + componente
				+ ", tipoBloqueComponentes=" + tipoBloqueComponentes + "]";
	}
	
	
}
