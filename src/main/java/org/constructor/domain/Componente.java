package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Componente.
 */
@Entity
@Table(name = "componente")
public class Componente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "contenido")
    private String contenido;
	
	@Column(name = "version")
    private int version;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "tipo_componente_id", nullable=false)
    private TipoComponente tipoComponente;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "bloque_componentes_id", nullable=false)
    private BloqueComponentes bloqueComponentes; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public BloqueComponentes getBloqueComponentes() {
		return bloqueComponentes;
	}

	public void setBloqueComponentes(BloqueComponentes bloqueComponentes) {
		this.bloqueComponentes = bloqueComponentes;
	}

	@Override
	public String toString() {
		return "Componente [id=" + id + ", contenido=" + contenido + ", version=" + version + "]";
	}
	
}
