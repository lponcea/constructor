package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.EstructuraJerarquica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link EstructuraJerarquica}.
 */
public interface EstructuraJerarquicaService {
	
	 /**
     * Save a EstructuraJerarquica.
     *
     * @param EstructuraJerarquica the entity to save.
     * @return the persisted entity.
     */
    EstructuraJerarquica save(EstructuraJerarquica estructuraJerarquica);

    /**
     * Get all the estructuraJerarquica.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EstructuraJerarquica> findAll(Pageable pageable);


    /**
     * Get the "id" EstructuraJerarquica.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstructuraJerarquica> findOne(Long id);

    /**
     * Delete the "id" estructuraJerarquica.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
