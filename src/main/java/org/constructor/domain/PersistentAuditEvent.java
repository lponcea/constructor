package org.constructor.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Persist AuditEvent managed by the Spring Boot actuator.
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
@Entity
@Table(name = "jhi_persistent_audit_event")
public class PersistentAuditEvent implements Serializable {

	/**
	 * Serializable
	 */
    private static final long serialVersionUID = 1L;

    /**
     * Long id 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    /**
     * String principal
     */
    @NotNull
    @Column(nullable = false)
    private String principal;

    /**
     * Instant auditEventDate
     */
    @Column(name = "event_date")
    private Instant auditEventDate;

    /**
     * String auditEventType
     */
    @Column(name = "event_type")
    private String auditEventType;

    /**
     * jhi_persistent_audit_evt_data
     */
    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "jhi_persistent_audit_evt_data", joinColumns=@JoinColumn(name="event_id"))
    private Map<String, String> data = new HashMap<>();

    
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
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * Set
     * @param principal
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    /**
     * Get
     * @return the auditEventDate
     */
    public Instant getAuditEventDate() {
        return auditEventDate;
    }

    /**
     * Set
     * @param auditEventDate
     */
    public void setAuditEventDate(Instant auditEventDate) {
        this.auditEventDate = auditEventDate;
    }

    /**
     * Get
     * @return the auditEventType
     */
    public String getAuditEventType() {
        return auditEventType;
    }

    /**
     * Set
     * @param auditEventType
     */
    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    /**
     * Get
     * @return the data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * Set
     * @param data
     */
    public void setData(Map<String, String> data) {
        this.data = data;
    }

    /**
     * equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersistentAuditEvent)) {
            return false;
        }
        return id != null && id.equals(((PersistentAuditEvent) o).id);
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
        return "PersistentAuditEvent{" +
            "principal='" + principal + '\'' +
            ", auditEventDate=" + auditEventDate +
            ", auditEventType='" + auditEventType + '\'' +
            '}';
    }
}
