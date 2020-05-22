package org.constructor.service;

import org.constructor.domain.Ficha;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

/**
 * Service Interface for managing {@link Ficha}.
 */
public interface FichaService {

    /**
     * Save a ficha.
     *
     * @param ficha the entity to save.
     * @return the persisted entity.
     */
    Ficha save(Ficha ficha);

    /**
     * Get all the fichas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Ficha> findAll(Pageable pageable);

    /**
     * Get all the fichas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Ficha> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" ficha.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Ficha> findOne(Long id);

    /**
     * Delete the "id" ficha.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
}
