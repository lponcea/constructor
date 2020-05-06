package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Country}.
 */

public interface CountryService {
    /**
     * Save a Country.
     *
     * @param country the entity to save.
     * @return the persisted entity.
     */
    Country save(Country country);

    /**
     * Get all the countries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Country> findAll(Pageable pageable);


    /**
     * Get the "id" Country.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Country> findOne(Long id);

    /**
     * Delete the "id" Country.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
