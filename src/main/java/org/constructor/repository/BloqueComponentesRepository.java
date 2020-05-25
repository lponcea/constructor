package org.constructor.repository;

import org.constructor.domain.BloqueComponentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BloqueComponentes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BloqueComponentesRepository extends JpaRepository<BloqueComponentes, Long>{

}
