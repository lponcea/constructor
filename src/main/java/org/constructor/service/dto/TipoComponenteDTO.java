package org.constructor.service.dto;

public class TipoComponenteDTO {
	
	private Long id;
	
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoComponenteDTO [id=" + id + ", Nombre=" + nombre + "]";
	}

}
