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
     * gradoAcademico
     */
    @OneToMany(mappedBy = "gradoAcademico", fetch = FetchType.EAGER)
    private Set<NumeroGrado> numeroGrados = new HashSet<>();

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
     * GradoAcademico descripcion
     * @param descripcion
     * @return the this 
     */
    public GradoAcademico descripcion(String descripcion) {
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
     * @return the numeroGrados
     */
    public Set<NumeroGrado> getNumeroGrados() {
        return numeroGrados;
    }

    /**
     * GradoAcademico numeroGrados
     * @param numeroGrados
     * @return the this 
     */
    public GradoAcademico numeroGrados(Set<NumeroGrado> numeroGrados) {
        this.numeroGrados = numeroGrados;
        return this;
    }

    /**
     * GradoAcademico addNumeroGrad
     * @param numeroGrado
     * @return the this 
     */
    public GradoAcademico addNumeroGrado(NumeroGrado numeroGrado) {
        this.numeroGrados.add(numeroGrado);
        numeroGrado.setGradoAcademico(this);
        return this;
    }

    /**
     * GradoAcademico removeNumeroGrado
     * @param numeroGrado
     * @return the this 
     */
    public GradoAcademico removeNumeroGrado(NumeroGrado numeroGrado) {
        this.numeroGrados.remove(numeroGrado);
        numeroGrado.setGradoAcademico(null);
        return this;
    }

    /**
     * Set
     * @param numeroGrados
     */
    public void setNumeroGrados(Set<NumeroGrado> numeroGrados) {
        this.numeroGrados = numeroGrados;
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
        if (!(o instanceof GradoAcademico)) {
            return false;
        }
        return id != null && id.equals(((GradoAcademico) o).id);
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
        return "GradoAcademico{" +
            "id=" + getId() +
            " numeroGrados=" + getNumeroGrados() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
