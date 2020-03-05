package org.constructor.service;

import org.constructor.domain.Asignatura;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Asignatura}.
 */
public interface AsignaturaService {

    /**
     * Save a asignatura.
     *
     * @param asignatura the entity to save.
     * @return the persisted entity.
     */
    Asignatura save(Asignatura asignatura);

    /**
     * Get all the asignaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Asignatura> findAll(Pageable pageable);


    /**
     * Get the "id" asignatura.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Asignatura> findOne(Long id);

    /**
     * Delete the "id" asignatura.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
