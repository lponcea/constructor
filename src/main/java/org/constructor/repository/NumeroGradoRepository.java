package org.constructor.repository;

import org.constructor.domain.NumeroGrado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NumeroGrado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NumeroGradoRepository extends JpaRepository<NumeroGrado, Long> {

}
