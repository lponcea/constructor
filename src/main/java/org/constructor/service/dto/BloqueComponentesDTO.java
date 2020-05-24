package org.constructor.service.dto;

import java.util.Set;

import org.constructor.domain.TipoBloqueComponentes;



public class BloqueComponentesDTO {
	
	private int orden;
	
	private TipoBloqueComponentes tipoBloqueComponente;
	
	private Set<ComponenteDTO> componentes;

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public TipoBloqueComponentes getTipoBloqueComponente() {
		return tipoBloqueComponente;
	}

	public void setTipoBloqueComponente(TipoBloqueComponentes tipoBloqueComponente) {
		this.tipoBloqueComponente = tipoBloqueComponente;
	}

	public Set<ComponenteDTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	@Override
	public String toString() {
		return "BloqueComponentesDTO [orden=" + orden + ", tipoBloqueComponente=" + tipoBloqueComponente
				+ ", componentes=" + componentes + "]";
	}
}
