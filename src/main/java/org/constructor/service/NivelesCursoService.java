/**
 * 
 */
package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.NivelesCurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing NivelesCurso .
 */
public interface NivelesCursoService {
	
	 /**
     * Save a NivelesCurso.
     *
     * @param nivelesCurso the entity to save.
     * @return the persisted entity.
     */
    NivelesCurso save(NivelesCurso nivelesCurso);

    /**
     * Get all the NivelesCurso.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NivelesCurso> findAll(Pageable pageable);


    /**
     * Get the "id" NivelesCurso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NivelesCurso> findOne(Long id);

    /**
     * Delete the "id" NivelesCurso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
