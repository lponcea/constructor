package org.constructor.service;

import org.constructor.domain.Editorial;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Editorial}.
 */
public interface EditorialService {

    /**
     * Save a editorial.
     *
     * @param editorial the entity to save.
     * @return the persisted entity.
     */
    Editorial save(Editorial editorial);

    /**
     * Get all the editorials.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Editorial> findAll(Pageable pageable);


    /**
     * Get the "id" editorial.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Editorial> findOne(Long id);

    /**
     * Delete the "id" editorial.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
