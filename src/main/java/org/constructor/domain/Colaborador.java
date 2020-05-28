package org.constructor.domain;


import javax.persistence.*;

import java.io.Serializable;


/**
 * A Colaborador.
 */
@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable {

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
     * String nombres
     */
    @Column(name = "nombres")
    private String nombres;

    /**
     * String apellido1
     */
    @Column(name = "apellido_1")
    private String apellido1;

    /**
     * String apellido2
     */
    @Column(name = "apellido_2")
    private String apellido2;
   

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
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Colaborador nombres
     * @param nombres
     * @return the this
     */
    public Colaborador nombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    /**
     * Set
     * @param nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Get
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Colaborador apellido
     * @param apellido1
     * @return the this
     */
    public Colaborador apellido1(String apellido1) {
        this.apellido1 = apellido1;
        return this;
    }

    /**
     * Set
     * @param apellido1
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Get
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Colaborador apellido2
     * @param apellido2
     * @return the this 
     */
    public Colaborador apellido2(String apellido2) {
        this.apellido2 = apellido2;
        return this;
    }

    /**
     * Set
     * @param apellido2
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    

    /**
     * equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Colaborador)) {
            return false;
        }
        return id != null && id.equals(((Colaborador) o).id);
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
        return "Colaborador{" +
            "id=" + getId() +
            ", nombres='" + getNombres() + "'" +
            ", apellido1='" + getApellido1() + "'" +
            ", apellido2='" + getApellido2() + "'" +
            "}";
    }
}
