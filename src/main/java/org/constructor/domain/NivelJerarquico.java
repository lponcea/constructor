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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A NivelJeraquico.
 */
@Entity
@Table(name = "nivel_jerarquico")
public class NivelJerarquico implements Serializable  {
		
	private static final long serialVersionUID = 1L;
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "informacion_adicional")
    private int  informacionAdicional;
	
	@Column(name = "tipo")
    private String  tipo;
	
	
	@OneToMany(mappedBy="nivelJerarquico", fetch = FetchType.EAGER)
	@OrderBy ("orden_nivel")
	private Set<EstructuraJerarquica> estructuraJerarquica = new HashSet<>();
	
	@OneToMany(mappedBy="nivelJerarquico", fetch = FetchType.EAGER)
	private Set<BloqueComponentes> bloquesComponentes = new HashSet<>();
	
	@OneToMany(mappedBy = "nivelJerarquico")
	@JsonIgnore
	private Set<NivelesCurso> nivelesCurso = new HashSet<>(); 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(int informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public Set<EstructuraJerarquica> getEstructuraJerarquica() {
		return estructuraJerarquica;
	}

	public void setEstructuraJerarquica(Set<EstructuraJerarquica> estructuraJerarquica) {
		this.estructuraJerarquica = estructuraJerarquica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<BloqueComponentes> getBloquesComponentes() {
		return bloquesComponentes;
	}

	public void setBloquesComponentes(Set<BloqueComponentes> bloquesComponentes) {
		this.bloquesComponentes = bloquesComponentes;
	}

	public String getTipo() {
		return tipo;
	}

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

	@Override
	public String toString() {
		return "NivelJerarquico [id=" + id + ", nombre=" + nombre + ", informacionAdicional=" + informacionAdicional
				+ ", tipo=" + tipo + ", estructuraJerarquica=" + estructuraJerarquica + ", bloquesComponentes="
				+ bloquesComponentes + "]";
	}

}
