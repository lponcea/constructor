package org.constructor.repository;

import org.constructor.domain.RolColaborador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RolColaborador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RolColaboradorRepository extends JpaRepository<RolColaborador, Long> {

}
