package org.constructor.repository;

import org.constructor.domain.Colaborador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Colaborador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

}
