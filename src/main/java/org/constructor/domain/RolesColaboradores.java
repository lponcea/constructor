package org.constructor.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

/**
* A RolesColaboradores.
*/
@Entity
@Table(name = "roles_colaboradores")
public class RolesColaboradores implements Serializable{ 
	
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
     * colaborador_id
     */
    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;
    
    /**
     * rol_colaborador_id
     */
    @ManyToOne
    @JoinColumn(name = "rol_colaborador_id")
    private RolColaborador rolColaborador;
    
    //RolesColaboradores puede tener muchas Fichas (JAM)
    @ManyToMany(mappedBy = "creditosEditoriales")
    @JsonIgnore
    private Set<Ficha> fichas = new HashSet<>();


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
	 * @return the colaborador
	 */
	public Colaborador getColaborador() {
		return colaborador;
	}
	
	/**
	 * colaborador
	 * @param colaborador
	 * @return the this 
	 */
	public RolesColaboradores colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

	/**
	 * Set
	 * @param colaborador
	 */
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	/**
	 * Get
	 * @return the rolColaborador
	 */
	public RolColaborador getRolColaborador() {
		return rolColaborador;
	}

	/**
	 * rolColaborador
	 * @param rolColaborador
	 * @return the this 
	 */
	public RolesColaboradores rolColaborador(RolColaborador rolColaborador) {
        this.rolColaborador = rolColaborador;
        return this;
    }

	/**
	 * Set
	 * @param rolColaborador
	 */
	public void setRolColaborador(RolColaborador rolColaborador) {
		this.rolColaborador = rolColaborador;
	}
	
	/**
	 * Get
	 * @return the fichas
	 */
	public Set<Ficha> getFichas() {
		return fichas;
	}

	/**
	 * Set
	 * @param fichas
	 */
	public void setFichas(Set<Ficha> fichas) {
		this.fichas = fichas;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "RolesColaboradores [id=" + id + ", colaborador=" + colaborador + ", rolColaborador=" + rolColaborador
				+ "]";
	} 
    
    
    

}
