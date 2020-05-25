package org.constructor.repository;

import org.constructor.domain.TipoBloqueComponentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoBloqueComponente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoBloqueComponentesRepository extends JpaRepository<TipoBloqueComponentes, Long>{

}
