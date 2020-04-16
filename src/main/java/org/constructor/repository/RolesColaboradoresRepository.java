package org.constructor.repository;

import org.constructor.domain.RolColaborador;
import org.constructor.domain.RolesColaboradores;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RolColaborador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RolesColaboradoresRepository extends JpaRepository<RolesColaboradores, Long> {

}
