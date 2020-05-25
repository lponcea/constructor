package org.constructor.repository;

import org.constructor.domain.NivelJerarquico;
import org.constructor.domain.RolesColaboradores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NivelJerarquico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NivelJerarquicoRepository extends JpaRepository<NivelJerarquico, Long> {

}
