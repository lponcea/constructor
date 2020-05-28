package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Modalidad.
 */
@Entity
@Table(name = "modalidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Modalidad implements Serializable {

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
     * String descripcio
     */
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * modalidad
     */
    @OneToMany(mappedBy = "modalidad")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Curso> cursos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    
    /**
     *Get
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modalidad descripcion
     * @param descripcion
     * @return the this 
     */
    public Modalidad descripcion(String descripcion) {
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
     * @return the cursos 
     */
    public Set<Curso> getCursos() {
        return cursos;
    }

    /**
     * Modalidad cursos 
     * @param cursos
     * @return the this 
     */
    public Modalidad cursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    /**
     * Modalidad addCurso
     * @param curso
     * @return the this 
     */
    public Modalidad addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setModalidad(this);
        return this;
    }

    /**
     * Modalidad removeCurso
     * @param curso
     * @return the this 
     */
    public Modalidad removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setModalidad(null);
        return this;
    }

    /**
     * Set
     * @param cursos
     */
    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
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
        if (!(o instanceof Modalidad)) {
            return false;
        }
        return id != null && id.equals(((Modalidad) o).id);
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
        return "Modalidad{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
