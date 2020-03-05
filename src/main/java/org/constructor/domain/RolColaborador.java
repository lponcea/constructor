package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RolColaborador.
 */
@Entity
@Table(name = "rol_colaborador")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RolColaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "rolColaborador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Colaborador> colaboradors = new HashSet<>();

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

    public RolColaborador descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Colaborador> getColaboradors() {
        return colaboradors;
    }

    public RolColaborador colaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
        return this;
    }

    public RolColaborador addColaborador(Colaborador colaborador) {
        this.colaboradors.add(colaborador);
        colaborador.setRolColaborador(this);
        return this;
    }

    public RolColaborador removeColaborador(Colaborador colaborador) {
        this.colaboradors.remove(colaborador);
        colaborador.setRolColaborador(null);
        return this;
    }

    public void setColaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RolColaborador)) {
            return false;
        }
        return id != null && id.equals(((RolColaborador) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RolColaborador{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
