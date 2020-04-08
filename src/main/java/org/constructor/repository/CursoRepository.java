package org.constructor.repository;

import java.util.List;

import org.constructor.domain.Curso;
import org.springframework.data.domain.Page;
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
	
	@Query("SELECT count(*) from Curso c where c.portadaUrl LIKE %:content%")
	Long findByContentCourseCover(@Param("content")String content);
	
	@Query("SELECT c from Curso c JOIN FETCH  c.user cu where cu.id = :id")
	List<Curso> findAllCursoUserId (@Param("id")Long id);

}
