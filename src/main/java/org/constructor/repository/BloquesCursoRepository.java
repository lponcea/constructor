package org.constructor.repository;

import org.constructor.domain.BloquesCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface BloquesCursoRepository.
 */
@SuppressWarnings("unused")
@Repository
public interface BloquesCursoRepository extends JpaRepository<BloquesCurso, Long> {

}
