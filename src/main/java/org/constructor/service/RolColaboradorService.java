package org.constructor.service;

import org.constructor.domain.RolColaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link RolColaborador}.
 */
public interface RolColaboradorService {

    /**
     * Save a rolColaborador.
     *
     * @param rolColaborador the entity to save.
     * @return the persisted entity.
     */
    RolColaborador save(RolColaborador rolColaborador);

    /**
     * Get all the rolColaboradors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RolColaborador> findAll(Pageable pageable);


    /**
     * Get the "id" rolColaborador.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RolColaborador> findOne(Long id);

    /**
     * Delete the "id" rolColaborador.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
