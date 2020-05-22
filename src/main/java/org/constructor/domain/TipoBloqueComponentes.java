package org.constructor.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A TipoBloqueComponentes.
 */
@Entity
@Table(name = "tipo_bloque_componentes")
public class TipoBloqueComponentes implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nombre_bloque_componentes")
    private String nombre;
	
	@Column(name = "icon_path")
    private String iconPath;
	
	@Column(name = "tags")
    private String tags;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tipos_bloques_componentes", 
            joinColumns = @JoinColumn(name = "tipo_bloque_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="tipo_componente_id", referencedColumnName = "id", nullable = false))
	private Set<TipoComponente> tiposComponentes;
	
	@JsonIgnore
	@OneToMany(mappedBy="tipoBloqueComponentes", fetch = FetchType.EAGER)
	private Set<BloqueComponentes> bloqueComponentes = new HashSet<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreBloqueComponentes() {
		return nombre;
	}

	public void setNombreBloqueComponentes(String nombre) {
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
	

	public Set<TipoComponente> getTiposComponentes() {
		return tiposComponentes;
	}

	public void setTiposComponentes(Set<TipoComponente> tiposComponentes) {
		this.tiposComponentes = tiposComponentes;
	}

	public Set<BloqueComponentes> getBloqueComponentes() {
		return bloqueComponentes;
	}

	public void setBloqueComponentes(Set<BloqueComponentes> bloqueComponentes) {
		this.bloqueComponentes = bloqueComponentes;
	}

	@Override
	public String toString() {
		return "TipoBloqueComponente [id=" + id + ", nombreBloqueComponente=" + nombre + ", iconPath="
				+ iconPath + ", tag=" + tags + ", tipoComponente=" + tiposComponentes + "]";
	}
	
}
