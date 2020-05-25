package org.constructor.service.dto;

public class TipoBloqueComponentesDTO {
	
	private Long id;
	
	private String nombre;
	
	private String iconPath;
	
	private String tags;

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

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TipoBloqueComponentesDTO [id=" + id + ", nombre=" + nombre + ", iconPath=" + iconPath + ", tags=" + tags
				+ "]";
	}

}
