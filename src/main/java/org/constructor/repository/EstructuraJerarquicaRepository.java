package org.constructor.repository;

import org.constructor.domain.EstructuraJerarquica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EstructuraJerarquica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstructuraJerarquicaRepository extends JpaRepository<EstructuraJerarquica, Long>{
	

}
