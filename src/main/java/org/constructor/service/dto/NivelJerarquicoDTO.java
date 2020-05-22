package org.constructor.service.dto;

import java.util.Set;

public class NivelJerarquicoDTO {
	
	private Long cursoId;
	
	private String nombre;
	
	private String tipo;
	
	private int informacionAdicional;
	
	private Set<BloqueComponentesDTO> bloquesComponentes;

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(int informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public Set<BloqueComponentesDTO> getBloquesComponentes() {
		return bloquesComponentes;
	}

	public void setBloquesComponentes(Set<BloqueComponentesDTO> bloquesComponentes) {
		this.bloquesComponentes = bloquesComponentes;
	}

	@Override
	public String toString() {
		return "NivelJerarquicoDTO [cursoId=" + cursoId + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", informacionAdicional=" + informacionAdicional + ", bloquesComponentes=" + bloquesComponentes + "]";
	} 
	
	
		
}
