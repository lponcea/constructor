package org.constructor.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NumeroGrado.
 */
@Entity
@Table(name = "numero_grado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NumeroGrado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "numeroGrado")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Curso> cursos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("numeroGrados")
    private GradoAcademico gradoAcademico;

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

    public NumeroGrado descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public NumeroGrado cursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    public NumeroGrado addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setNumeroGrado(this);
        return this;
    }

    public NumeroGrado removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setNumeroGrado(null);
        return this;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public GradoAcademico getGradoAcademico() {
        return gradoAcademico;
    }

    public NumeroGrado gradoAcademico(GradoAcademico gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
        return this;
    }

    public void setGradoAcademico(GradoAcademico gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumeroGrado)) {
            return false;
        }
        return id != null && id.equals(((NumeroGrado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NumeroGrado{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
