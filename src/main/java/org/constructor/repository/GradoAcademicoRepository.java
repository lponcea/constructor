package org.constructor.repository;

import org.constructor.domain.GradoAcademico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GradoAcademico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GradoAcademicoRepository extends JpaRepository<GradoAcademico, Long> {

}
