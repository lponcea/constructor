package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.BloqueComponentes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link BloqueComponentesService}.
 */
public interface BloqueComponentesService {
	
	/**
     * Save a BloqueComponentes.
     *
     * @param bloqueComponentes the entity to save.
     * @return the persisted entity.
     */
    BloqueComponentes save(BloqueComponentes bloqueComponentes);

    /**
     * Get all the versions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BloqueComponentes> findAll(Pageable pageable);


    /**
     * Get the "id" BloqueComponentes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BloqueComponentes> findOne(Long id);

    /**
     * Delete the "id" BloqueComponentes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
