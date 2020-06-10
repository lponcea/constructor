/**
 * 
 */
package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Edukai
 *
 */
public interface AutorService {

	  /**
     * Save a Author.
     *
     * @param country the entity to save.
     * @return the persisted entity.
     */
	Autor save(Autor autor);

    /**
     * Get all the countries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Autor> findAll(Pageable pageable);


    /**
     * Get the "id" Author.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Autor> findOne(Long id);

    /**
     * Delete the "id" Author.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
