package org.constructor.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Version.
 */
@Entity
@Table(name = "version")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Version implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version")
    private Integer version;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_version")
    private LocalDate fechaVersion;

    @OneToMany(mappedBy = "version")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Curso> cursos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public Version version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getComentario() {
        return comentario;
    }

    public Version comentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaVersion() {
        return fechaVersion;
    }

    public Version fechaVersion(LocalDate fechaVersion) {
        this.fechaVersion = fechaVersion;
        return this;
    }

    public void setFechaVersion(LocalDate fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public Version cursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    public Version addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setVersion(this);
        return this;
    }

    public Version removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setVersion(null);
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
        if (!(o instanceof Version)) {
            return false;
        }
        return id != null && id.equals(((Version) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Version{" +
            "id=" + getId() +
            ", version=" + getVersion() +
            ", comentario='" + getComentario() + "'" +
            ", fechaVersion='" + getFechaVersion() + "'" +
            "}";
    }
}
