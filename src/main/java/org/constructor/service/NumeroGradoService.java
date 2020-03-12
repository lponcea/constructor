package org.constructor.service;

import org.constructor.domain.NumeroGrado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link NumeroGrado}.
 */
public interface NumeroGradoService {

    /**
     * Save a numeroGrado.
     *
     * @param numeroGrado the entity to save.
     * @return the persisted entity.
     */
    NumeroGrado save(NumeroGrado numeroGrado);

    /**
     * Get all the numeroGrados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NumeroGrado> findAll(Pageable pageable);


    /**
     * Get the "id" numeroGrado.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NumeroGrado> findOne(Long id);

    /**
     * Delete the "id" numeroGrado.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
   // Page<NumeroGrado> findGrade(Long id);
}
