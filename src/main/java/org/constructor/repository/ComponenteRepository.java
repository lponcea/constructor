/**
 * 
 */
package org.constructor.repository;

import org.constructor.domain.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Editorial entity.
 *
 */
@SuppressWarnings("unused")
@Repository
public interface ComponenteRepository  extends JpaRepository<Componente, Long>{

}
