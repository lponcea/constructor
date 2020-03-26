package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Asignatura.
 */
@Entity
@Table(name = "asignatura")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "asignatura")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Curso> cursos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Asignatura descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public Asignatura cursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    public Asignatura addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setAsignatura(this);
        return this;
    }

    public Asignatura removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setAsignatura(null);
        return this;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asignatura)) {
            return false;
        }
        return id != null && id.equals(((Asignatura) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
