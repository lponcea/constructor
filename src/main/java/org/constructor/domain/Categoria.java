package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Categoria.
 */
@Entity
@Table(name = "categoria")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Categoria implements Serializable {

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
     * String descripcion
     */
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * categoria
     */
    @OneToMany(mappedBy = "categoria")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Curso> cursos = new HashSet<>();

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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Categoria descripcio
     * @param descripcion
     * @return the this
     */
    public Categoria descripcion(String descripcion) {
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
     * Categoria cursos
     * @param cursos
     * @return the this
     */
    public Categoria cursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    /**
     * Categoria addCurso
     * @param curso
     * @return the this
     */
    public Categoria addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setCategoria(this);
        return this;
    }

    /**
     * Categoria removeCurso
     * @param curso
     * @return this
     */
    public Categoria removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setCategoria(null);
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
        if (!(o instanceof Categoria)) {
            return false;
        }
        return id != null && id.equals(((Categoria) o).id);
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
        return "Categoria{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
