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

/**
* A RolesColaboradores.
*/
@Entity
@Table(name = "roles_colaboradores")
public class RolesColaboradores implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;
    
    
    @ManyToOne
    @JoinColumn(name = "rol_colaborador_id")
    private RolColaborador rolColaborador;
    
    //RolesColaboradores puede tener muchas Fichas (JAM)
    @ManyToMany(mappedBy = "creditosEditoriales")
    @JsonIgnore
    private Set<Ficha> fichas = new HashSet<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Colaborador getColaborador() {
		return colaborador;
	}
	
	public RolesColaboradores colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }


	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	public RolColaborador getRolColaborador() {
		return rolColaborador;
	}

	public RolesColaboradores rolColaborador(RolColaborador rolColaborador) {
        this.rolColaborador = rolColaborador;
        return this;
    }

	public void setRolColaborador(RolColaborador rolColaborador) {
		this.rolColaborador = rolColaborador;
	}
	
	


	public Set<Ficha> getFichas() {
		return fichas;
	}


	public void setFichas(Set<Ficha> fichas) {
		this.fichas = fichas;
	}


	@Override
	public String toString() {
		return "RolesColaboradores [id=" + id + ", colaborador=" + colaborador + ", rolColaborador=" + rolColaborador
				+ "]";
	} 
    
    
    

}
