package org.constructor.service.dto;

public class TipoComponenteDTO {
	
	private Long id;
	
	private String Nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoComponenteDTO [id=" + id + ", Nombre=" + Nombre + "]";
	}

}
