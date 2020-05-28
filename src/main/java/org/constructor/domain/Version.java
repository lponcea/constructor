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
     * Integer version
     */
    @Column(name = "version")
    private Integer version;

    /**
     * String comentario
     */
    @Column(name = "comentario")
    private String comentario;

    /**
     * LocalDate fechaVersion
     */
    @Column(name = "fecha_version")
    private LocalDate fechaVersion;

    /**
     * version
     */
    @OneToMany(mappedBy = "version")
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
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * version
     * @param version
     * @return the this 
     */
    public Version version(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Set
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Get 
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * comentario
     * @param comentario
     * @return the this 
     */
    public Version comentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    /**
     * Set
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Get
     * @return the fechaVersion
     */
    public LocalDate getFechaVersion() {
        return fechaVersion;
    }

    /**
     * fechaVersion
     * @param fechaVersion
     * @return the this 
     */
    public Version fechaVersion(LocalDate fechaVersion) {
        this.fechaVersion = fechaVersion;
        return this;
    }

    /**
     * Set
     * @param fechaVersion
     */
    public void setFechaVersion(LocalDate fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    /**
     * Get 
     * @return the cursos
     */
    public Set<Curso> getCursos() {
        return cursos;
    }

    /**
     * cursos
     * @param cursos
     * @return the this 
     */
    public Version cursos(Set<Curso> cursos) {
        this.cursos = cursos;
        return this;
    }

    /**
     * addCurso
     * @param curso
     * @return the this 
     */
    public Version addCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setVersion(this);
        return this;
    }

    /**
     * removeCurso
     * @param curso
     * @return the this
     */
    public Version removeCurso(Curso curso) {
        this.cursos.remove(curso);
        curso.setVersion(null);
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
        if (!(o instanceof Version)) {
            return false;
        }
        return id != null && id.equals(((Version) o).id);
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
        return "Version{" +
            "id=" + getId() +
            ", version=" + getVersion() +
            ", comentario='" + getComentario() + "'" +
            ", fechaVersion='" + getFechaVersion() + "'" +
            "}";
    }
}
