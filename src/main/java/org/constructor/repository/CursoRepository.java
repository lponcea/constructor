package org.constructor.repository;

import org.constructor.domain.Curso;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Curso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	@Query("SELECT c.portadaUrl from Curso c where c.id = :id ")
	String findByCourseCoverId(@Param("id")Long Id);

}
