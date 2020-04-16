package org.constructor.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ficha.
 */
@Entity
@Table(name = "ficha")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    @JsonIgnore
    private Curso curso;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ficha_colaborador",
               joinColumns = @JoinColumn(name = "ficha_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "colaborador_id", referencedColumnName = "id"))
    private Set<Colaborador> colaboradors = new HashSet<>();
    
    //Ficha puede tener muchos RolesColaboradores (JAM)
    @ManyToMany
    @JoinTable(
            name = "creditos_editoriales", 
            joinColumns = @JoinColumn(name = "ficha_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="roles_colaboradores_id", referencedColumnName = "id", nullable = false))
    private Set<RolesColaboradores> creditosEditoriales = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("fichas")
    private Editorial editorial;

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

    public Ficha descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Ficha fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Curso getCurso() {
        return curso;
    }

    public Ficha curso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<Colaborador> getColaboradors() {
        return colaboradors;
    }

    public Ficha colaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
        return this;
    }

    public Ficha addColaborador(Colaborador colaborador) {
        this.colaboradors.add(colaborador);
        colaborador.getFichas().add(this);
        return this;
    }

    public Ficha removeColaborador(Colaborador colaborador) {
        this.colaboradors.remove(colaborador);
        colaborador.getFichas().remove(this);
        return this;
    }

    public void setColaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public Ficha editorial(Editorial editorial) {
        this.editorial = editorial;
        return this;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    public Set<RolesColaboradores> getCreditosEditoriales() {
		return creditosEditoriales;
	}

	public void setCreditosEditoriales(Set<RolesColaboradores> creditosEditoriales) {
		this.creditosEditoriales = creditosEditoriales;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ficha)) {
            return false;
        }
        return id != null && id.equals(((Ficha) o).id);
    }


	@Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ficha{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            "}";
    }
}