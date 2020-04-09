package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GradoAcademico.
 */
@Entity
@Table(name = "grado_academico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GradoAcademico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "gradoAcademico")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NumeroGrado> numeroGrados = new HashSet<>();

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

    public GradoAcademico descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<NumeroGrado> getNumeroGrados() {
        return numeroGrados;
    }

    public GradoAcademico numeroGrados(Set<NumeroGrado> numeroGrados) {
        this.numeroGrados = numeroGrados;
        return this;
    }

    public GradoAcademico addNumeroGrado(NumeroGrado numeroGrado) {
        this.numeroGrados.add(numeroGrado);
        numeroGrado.setGradoAcademico(this);
        return this;
    }

    public GradoAcademico removeNumeroGrado(NumeroGrado numeroGrado) {
        this.numeroGrados.remove(numeroGrado);
        numeroGrado.setGradoAcademico(null);
        return this;
    }

    public void setNumeroGrados(Set<NumeroGrado> numeroGrados) {
        this.numeroGrados = numeroGrados;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GradoAcademico)) {
            return false;
        }
        return id != null && id.equals(((GradoAcademico) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GradoAcademico{" +
            "id=" + getId() +
            " numeroGrados=" + getNumeroGrados() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
