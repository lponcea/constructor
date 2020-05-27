package org.constructor.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.constructor.domain.enumeration.ModoDistribucion;

import org.constructor.domain.enumeration.EtapaEditorial;

/**
 * A Curso.
 */
@Entity
@Table(name = "curso")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "modo_distribucion")
    private ModoDistribucion modoDistribucion;

    @Enumerated(EnumType.STRING)
    @Column(name = "etapa_editorial")
    private EtapaEditorial etapaEditorial;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_creacion_sys")
    private LocalDate fechaCreacionSys;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "fecha_publicacion_sys")
    private LocalDate fechaPublicacionSys;

    @Column(name = "numero_edicion")
    private String numeroEdicion;

    @Column(name = "version_str")
    private String versionStr;

    @Column(name = "palabra_clave")
    private String palabraClave;

    @Column(name = "resumen_contenido")
    private String resumenContenido;

    @Column(name = "clave")
    private String clave;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "portada_url")
    private String portadaUrl;

    
    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ficha ficha;
    
    @ManyToOne
    @JsonIgnoreProperties("cursos") 
    private Modalidad modalidad;

    @ManyToOne
    @JsonIgnoreProperties("cursos")
    private Version version;

    @ManyToOne
    @JsonIgnoreProperties("cursos")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties("cursos")
    private Asignatura asignatura;

    @ManyToOne
    @JsonIgnoreProperties("cursos")
    private NumeroGrado numeroGrado;
    
    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<NivelesCurso> nivelesCurso = new HashSet<>();
    
    @JsonIgnore
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(
            name = "curso_usuario", 
            joinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id", nullable = false))
    private Set<User> user = new HashSet<>();
    
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Curso titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Curso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ModoDistribucion getModoDistribucion() {
        return modoDistribucion;
    }

    public Curso modoDistribucion(ModoDistribucion modoDistribucion) {
        this.modoDistribucion = modoDistribucion;
        return this;
    }

    public void setModoDistribucion(ModoDistribucion modoDistribucion) {
        this.modoDistribucion = modoDistribucion;
    }

    public EtapaEditorial getEtapaEditorial() {
        return etapaEditorial;
    }

    public Curso etapaEditorial(EtapaEditorial etapaEditorial) {
        this.etapaEditorial = etapaEditorial;
        return this;
    }

    public void setEtapaEditorial(EtapaEditorial etapaEditorial) {
        this.etapaEditorial = etapaEditorial;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Curso fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(String fechaCreacion) {
		if (fechaCreacion == null) {
			this.fechaCreacion = null;
		} else {
			this.fechaCreacion = LocalDate.parse(fechaCreacion,
					DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz"));
		}
    }

    public LocalDate getFechaCreacionSys() {
        return fechaCreacionSys;
    }

    public Curso fechaCreacionSys(LocalDate fechaCreacionSys) {
        this.fechaCreacionSys = fechaCreacionSys;
        return this;
    }

    public void setFechaCreacionSys(LocalDate fechaCreacionSys) {
        this.fechaCreacionSys = fechaCreacionSys;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Curso fechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
    	
    	if(fechaPublicacion == null) {
    		this.fechaPublicacion = null;
    	}else {
    		this.fechaPublicacion = LocalDate.parse(fechaPublicacion, DateTimeFormatter.ofPattern ( "yyyy-MM-dd'T'HH:mm:ss.SSSz" ));	
    	}
    }

    public LocalDate getFechaPublicacionSys() {
        return fechaPublicacionSys;
    }

    public Curso fechaPublicacionSys(LocalDate fechaPublicacionSys) {
        this.fechaPublicacionSys = fechaPublicacionSys;
        return this;
    }

    public void setFechaPublicacionSys(LocalDate fechaPublicacionSys) {
        this.fechaPublicacionSys = fechaPublicacionSys;
    }

    public String getNumeroEdicion() {
        return numeroEdicion;
    }

    public Curso numeroEdicion(String numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
        return this;
    }

    public void setNumeroEdicion(String numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    public String getVersionStr() {
        return versionStr;
    }

    public Curso versionStr(String versionStr) {
        this.versionStr = versionStr;
        return this;
    }

    public void setVersionStr(String versionStr) {
        this.versionStr = versionStr;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public Curso palabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
        return this;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public String getResumenContenido() {
        return resumenContenido;
    }

    public Curso resumenContenido(String resumenContenido) {
        this.resumenContenido = resumenContenido;
        return this;
    }

    public void setResumenContenido(String resumenContenido) {
        this.resumenContenido = resumenContenido;
    }

    public String getClave() {
        return clave;
    }

    public Curso clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstatus() {
        return estatus;
    }

    public Curso estatus(String estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getPortadaUrl() {
        return portadaUrl;
    }

    public Curso portadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
        return this;
    }

    public void setPortadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
    }
    
    public Ficha getFicha() {
        return ficha;
    }

    public Curso ficha(Ficha ficha) {
        this.ficha = ficha;
        return this;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public Curso modalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Version getVersion() {
        return version;
    }

    public Curso version(Version version) {
        this.version = version;
        return this;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Curso categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public Curso asignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
        return this;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public NumeroGrado getNumeroGrado() {
        return numeroGrado;
    }

    public Curso numeroGrado(NumeroGrado numeroGrado) {
        this.numeroGrado = numeroGrado;
        return this;
    }

    public void setNumeroGrado(NumeroGrado numeroGrado) {
        this.numeroGrado = numeroGrado;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
	

	/**
	 * @return the nivelesCurso
	 */
	public Set<NivelesCurso> getNivelesCurso() {
		return nivelesCurso;
	}

	/**
	 * @param nivelesCurso the nivelesCurso to set
	 */
	public void setNivelesCurso(Set<NivelesCurso> nivelesCurso) {
		this.nivelesCurso = nivelesCurso;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Curso)) {
            return false;
        }
        return id != null && id.equals(((Curso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Curso{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", modoDistribucion='" + getModoDistribucion() + "'" +
            ", etapaEditorial='" + getEtapaEditorial() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaCreacionSys='" + getFechaCreacionSys() + "'" +
            ", fechaPublicacion='" + getFechaPublicacion() + "'" +
            ", fechaPublicacionSys='" + getFechaPublicacionSys() + "'" +
            ", numeroEdicion='" + getNumeroEdicion() + "'" +
            ", versionStr='" + getVersionStr() + "'" +
            ", palabraClave='" + getPalabraClave() + "'" +
            ", resumenContenido='" + getResumenContenido() + "'" +
            ", clave='" + getClave() + "'" +
            ", estatus='" + getEstatus() + "'" +
            ", portadaUrl='" + getPortadaUrl() + "'" +
            "}";
    }
}
