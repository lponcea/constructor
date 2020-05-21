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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A TipoComponente.
 */
@Entity
@Table(name = "tipo_componente")
public class TipoComponente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nombre_componente")
    private String nombreComponente;
	
	@ManyToMany(mappedBy = "tipoComponente")
	@JsonIgnore
    private Set<TipoBloqueComponentes> tipoBloqueComponentes = new HashSet<>();
	
	@OneToMany(mappedBy="tipoComponente", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Componente> componente = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreComponente() {
		return nombreComponente;
	}

	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}


	public Set<TipoBloqueComponentes> getTipoBloqueComponentes() {
		return tipoBloqueComponentes;
	}

	public void setTipoBloqueComponentes(Set<TipoBloqueComponentes> tipoBloqueComponentes) {
		this.tipoBloqueComponentes = tipoBloqueComponentes;
	}
	
	public Set<Componente> getComponente() {
		return componente;
	}

	public void setComponente(Set<Componente> componente) {
		this.componente = componente;
	}

	@Override
	public String toString() {
		return "TipoComponente [id=" + id + ", nombreComponente=" + nombreComponente + "]";
	}
}
