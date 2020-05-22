package org.constructor.web.rest;

import org.constructor.service.dto.ComponenteDTO;

public class BloquesComponentesDTO {
	
	private int orden;
	
	private int tipoBloqueComponentes;
	
	private ComponenteDTO componente;

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getTipoBloqueComponentes() {
		return tipoBloqueComponentes;
	}

	public void setTipoBloqueComponentes(int tipoBloqueComponentes) {
		this.tipoBloqueComponentes = tipoBloqueComponentes;
	}

	public ComponenteDTO getComponente() {
		return componente;
	}

	public void setComponente(ComponenteDTO componente) {
		this.componente = componente;
	}

	@Override
	public String toString() {
		return "BloquesComponentesDTO [orden=" + orden + ", tipoBloqueComponentes=" + tipoBloqueComponentes
				+ ", componente=" + componente + "]";
	}
}
