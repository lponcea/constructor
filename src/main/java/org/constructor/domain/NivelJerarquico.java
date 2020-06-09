package org.constructor.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A NivelJeraquico.
 */
@Entity
@Table(name = "nivel_jerarquico")
public class NivelJerarquico implements Serializable  {
		
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Long id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * String nombre
	 */
	@Column(name = "nombre")
    private String nombre;
	
	/**
	 * int informacionAdicional
	 */
	@Column(name = "informacion_adicional")
    private int  informacionAdicional;
	
	/**
	 * String tipo 
	 */
	@Column(name = "tipo")
    private String  tipo;
	
	/**
	 * orden_nivel
	 */
	@OneToMany(mappedBy="nivelJerarquico", fetch = FetchType.EAGER)
	@OrderBy ("orden")
	private Set<EstructuraJerarquica> estructuraJerarquica = new HashSet<>();
	
	/**
	 * orden
	 */
	@OneToMany(mappedBy="nivelJerarquico", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("orden")
	private List<BloqueComponentes> bloquesComponentes = new ArrayList<>();
	
	/**
	 * nivelJerarquico
	 */
	@OneToMany(mappedBy = "nivelJerarquico")
	@JsonIgnore
	private Set<NivelesCurso> nivelesCurso = new HashSet<>(); 
	

	/**
	 * Get
	 * @return the id 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Get
	 * @return the informacionAdicional
	 */
	public int getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Set
	 * @param informacionAdicional
	 */
	public void setInformacionAdicional(int informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	/**
	 * Get 
	 * @return the estructuraJerarquica
	 */
	public Set<EstructuraJerarquica> getEstructuraJerarquica() {
		return estructuraJerarquica;
	}

	/**
	 * Set
	 * @param estructuraJerarquica
	 */
	public void setEstructuraJerarquica(Set<EstructuraJerarquica> estructuraJerarquica) {
		this.estructuraJerarquica = estructuraJerarquica;
	}

	/**
	 * Get
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Get
	 * @return the bloquesComponentes
	 */
	public List<BloqueComponentes> getBloquesComponentes() {
		return bloquesComponentes;
	}

	/**
	 * Set
	 * @param bloquesComponentes
	 */
	public void setBloquesComponentes(List<BloqueComponentes> bloquesComponentes) {
		this.bloquesComponentes = bloquesComponentes;
	}

	/**
	 * Get
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Set
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return the nivelesCurso
	 */
	public Set<NivelesCurso> getNivelesCurso() {
		return nivelesCurso;
	}

	/**
	 * @param nivelesCurso the nivelesCurso to set
	 */
	public void setNivelesCurso(Set<NivelesCurso> nivelesCurso) {
		this.nivelesCurso = nivelesCurso;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "NivelJerarquico [id=" + id + ", nombre=" + nombre + ", informacionAdicional=" + informacionAdicional
				+ ", tipo=" + tipo + ", estructuraJerarquica=" + estructuraJerarquica + ", bloquesComponentes="
				+ bloquesComponentes + "]";
	}

}
