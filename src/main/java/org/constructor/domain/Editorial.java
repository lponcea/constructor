package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Editorial.
 */
@Entity
@Table(name = "editorial")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Editorial implements Serializable {

	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Long id 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * String nombre
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * String descripcion
     */
    @Column(name = "descripcion")
    private String descripcion;
    

    /**
     * String direccion 
     * 
     */
    @Column(name = "direccion")
    private String direccion;

    /**
     * String telefono
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * editorial
     */
    @OneToMany(mappedBy = "editorial")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ficha> fichas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    
    /**
     * Get
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Editorial nombre
     * @param nombre
     * @return the this 
     */
    public Editorial nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     * Set
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Editorial descripcion
     * @param descripcion
     * @return the this 
     */
    public Editorial descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    /**
     * Set
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Editorial direccion
     * @param direccion
     * @return the this 
     */
    public Editorial direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    /**
     * Set
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Get 
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Editorial telefono
     * @param telefono
     * @return the this 
     */
    public Editorial telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    /**
     * Set
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Get 
     * @return the fichas
     */
    public Set<Ficha> getFichas() {
        return fichas;
    }

    /**
     *  Editorial fichas
     * @param fichas
     * @return the this 
     */
    public Editorial fichas(Set<Ficha> fichas) {
        this.fichas = fichas;
        return this;
    }

    /**
     * Editorial addFicha
     * @param ficha
     * @return the this 
     */
    public Editorial addFicha(Ficha ficha) {
        this.fichas.add(ficha);
        ficha.setEditorial(this);
        return this;
    }

    /**
     * Editorial removeFicha
     * @param ficha
     * @return the this 
     */
    public Editorial removeFicha(Ficha ficha) {
        this.fichas.remove(ficha);
        ficha.setEditorial(null);
        return this;
    }

    /**
     * Set
     * @param fichas
     */
    public void setFichas(Set<Ficha> fichas) {
        this.fichas = fichas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    /**
     * equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Editorial)) {
            return false;
        }
        return id != null && id.equals(((Editorial) o).id);
    }

    /**
     * hashCode
     */
    @Override
    public int hashCode() {
        return 31;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "Editorial{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
}
