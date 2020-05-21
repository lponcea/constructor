package org.constructor.domain;

import java.io.Serializable;
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
import javax.persistence.Table;

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
    private String nombreBloqueComponentes;
	
	@Column(name = "icon_path")
    private String iconPath;
	
	@Column(name = "tags")
    private String tags;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tipos_bloques_componentes", 
            joinColumns = @JoinColumn(name = "tipo_bloque_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="tipo_componente_id", referencedColumnName = "id", nullable = false))
	private Set<TipoComponente> tipoComponente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreBloqueComponente() {
		return nombreBloqueComponentes;
	}

	public void setNombreBloqueComponentes(String nombreBloqueComponentes) {
		this.nombreBloqueComponentes = nombreBloqueComponentes;
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
	
	public Set<TipoComponente> getTiposBloquesComponentes() {
		return tipoComponente;
	}

	public void setTiposBloquesComponentes(Set<TipoComponente> tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	@Override
	public String toString() {
		return "TipoBloqueComponente [id=" + id + ", nombreBloqueComponente=" + nombreBloqueComponentes + ", iconPath="
				+ iconPath + ", tag=" + tags + ", tipoComponente=" + tipoComponente + "]";
	}
	
}
