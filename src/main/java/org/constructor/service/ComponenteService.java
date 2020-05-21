/**
 * 
 */
package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.Componente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Component
 */
public interface ComponenteService {
	
    /**
     * Save a Component.
     *
     * @param modalidad the entity to save.
     * @return the persisted entity.
     */
    Componente save(Componente componente);

    /**
     * Get all the Component.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Componente> findAll(Pageable pageable);


    /**
     * Get the "id" Component.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Componente> findOne(Long id);

    /**
     * Delete the "id" Component.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
