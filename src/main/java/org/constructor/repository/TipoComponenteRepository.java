package org.constructor.repository;

import org.constructor.domain.TipoComponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoComponente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoComponenteRepository extends JpaRepository<TipoComponente, Long>{

}
