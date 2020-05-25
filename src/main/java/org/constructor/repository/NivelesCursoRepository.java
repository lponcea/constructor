/**
 * 
 */
package org.constructor.repository;

import org.constructor.domain.NivelesCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NivelesCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NivelesCursoRepository extends JpaRepository<NivelesCurso, Long>  {

}
