package org.constructor.repository;

import java.util.Optional;

import org.constructor.domain.NumeroGrado;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NumeroGrado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NumeroGradoRepository extends JpaRepository<NumeroGrado, Long> {
	
	/*@Query("SELECT ng FROM NumeroGrado ng JOIN FETCH ng.gradoAcademico ga where ga.id = :id")
	Page<NumeroGrado> findByIdGrade(@Param("id")Long id);*/

}
