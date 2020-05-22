package org.constructor.service.dto;

import java.util.Set;

import org.constructor.domain.TipoBloqueComponentes;


public class BloqueComponentesDTO {
	
	private int orden;
	
	private Long tipoBloqueComponentes;
	
	private Set<ComponenteDTO> componentes;

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public Long getTipoBloqueComponentes() {
		return tipoBloqueComponentes;
	}

	public void setTipoBloqueComponentes(Long tipoBloqueComponentes) {
		this.tipoBloqueComponentes = tipoBloqueComponentes;
	}

	public Set<ComponenteDTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	@Override
	public String toString() {
		return "BloqueComponentesDTO [orden=" + orden + ", tipoBloqueComponente=" + tipoBloqueComponentes
				+ ", componentes=" + componentes + "]";
	}
}
