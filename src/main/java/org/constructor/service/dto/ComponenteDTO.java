package org.constructor.service.dto;

import org.constructor.domain.TipoComponente;

public class ComponenteDTO {
	
	private TipoComponente tipoComponente;
	
	private String contenido;
	
	private int version;

	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
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

	@Override
	public String toString() {
		return "ComponenteDTO [tipoComponente=" + tipoComponente + ", contenido=" + contenido + ", version=" + version
				+ "]";
	}
}
