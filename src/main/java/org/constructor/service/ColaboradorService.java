package org.constructor.service;

import org.constructor.domain.Colaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Colaborador}.
 */
public interface ColaboradorService {

    /**
     * Save a colaborador.
     *
     * @param colaborador the entity to save.
     * @return the persisted entity.
     */
    Colaborador save(Colaborador colaborador);

    /**
     * Get all the colaboradors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Colaborador> findAll(Pageable pageable);


    /**
     * Get the "id" colaborador.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Colaborador> findOne(Long id);

    /**
     * Delete the "id" colaborador.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
