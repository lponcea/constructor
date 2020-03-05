package org.constructor.service;

import org.constructor.domain.GradoAcademico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link GradoAcademico}.
 */
public interface GradoAcademicoService {

    /**
     * Save a gradoAcademico.
     *
     * @param gradoAcademico the entity to save.
     * @return the persisted entity.
     */
    GradoAcademico save(GradoAcademico gradoAcademico);

    /**
     * Get all the gradoAcademicos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GradoAcademico> findAll(Pageable pageable);


    /**
     * Get the "id" gradoAcademico.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GradoAcademico> findOne(Long id);

    /**
     * Delete the "id" gradoAcademico.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
