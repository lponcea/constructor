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
    private Set<RolesColaboradores> rolesColaboradores = new HashSet<>();

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

    public Set<RolesColaboradores> getRolesColaboradores() {
        return rolesColaboradores;
    }

    public RolColaborador rolesColaboradores(Set<RolesColaboradores> rolesColaboradores) {
        this.rolesColaboradores = rolesColaboradores;
        return this;
    }

    public void setRolesColaboradores(Set<RolesColaboradores> rolesColaboradores) {
        this.rolesColaboradores = rolesColaboradores;
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
