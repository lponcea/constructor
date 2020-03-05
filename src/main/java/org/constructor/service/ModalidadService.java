package org.constructor.service;

import org.constructor.domain.Modalidad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Modalidad}.
 */
public interface ModalidadService {

    /**
     * Save a modalidad.
     *
     * @param modalidad the entity to save.
     * @return the persisted entity.
     */
    Modalidad save(Modalidad modalidad);

    /**
     * Get all the modalidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Modalidad> findAll(Pageable pageable);


    /**
     * Get the "id" modalidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Modalidad> findOne(Long id);

    /**
     * Delete the "id" modalidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
