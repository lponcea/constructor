package org.constructor.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A EstructuraJerarquica.
 */
@Entity
@Table(name = "estructura_jerarquica")
public class EstructuraJerarquica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "nivel_jerarquico_id", nullable=false)
    private NivelJerarquico nivelJerarquico;
	
	@Column(name = "subnivel_jerarquico_id", nullable=false)
	private int subNivelJerarquico;
	
	@Column(name = "orden_nivel")
    private int ordenNivel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NivelJerarquico getNivelJerarquico() {
		return nivelJerarquico;
	}

	public void setNivelJerarquico(NivelJerarquico nivelJerarquico) {
		this.nivelJerarquico = nivelJerarquico;
	}

	public int getSubNivelJerarquico() {
		return subNivelJerarquico;
	}

	public void setSubNivelJerarquico(int subNivelJerarquico) {
		this.subNivelJerarquico = subNivelJerarquico;
	}

	public int getOrdenNivel() {
		return ordenNivel;
	}

	public void setOrdenNivel(int ordenNivel) {
		this.ordenNivel = ordenNivel;
	}

	@Override
	public String toString() {
		return "EstructuraJerarquica [id=" + id + ", subNivelJerarquico="
				+ subNivelJerarquico + ", ordenNivel=" + ordenNivel + "]";
	}
	
}
