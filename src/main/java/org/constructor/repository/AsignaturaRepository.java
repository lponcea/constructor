package org.constructor.repository;

import org.constructor.domain.Asignatura;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Asignatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

}
