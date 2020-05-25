package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.TipoBloqueComponentes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link TipoBloqueComponenteService}.
 */
public interface TipoBloqueComponentesService {
	
	/**
     * Save a TipoBloqueComponente.
     *
     * @param tipoBloqueComponente the entity to save.
     * @return the persisted entity.
     */
    TipoBloqueComponentes save(TipoBloqueComponentes tipoBloqueComponente);
    
    /**
     * Get all the tipoBloqueComponente.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoBloqueComponentes> findAll(Pageable pageable);
    
    /**
     * Get the "id" tipoBloqueComponente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoBloqueComponentes> findOne(Long id);
    
    /**
     * Delete the "id" tipoBloqueComponente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
