package org.constructor.service;

import java.util.Optional;

import org.constructor.domain.NivelJerarquico;
import org.constructor.response.NivelJerarquicoResponse;
import org.constructor.service.dto.NivelJerarquicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link NivelJerarquico}.
 */
public interface NivelJerarquicoService {
	
	 /**
     * Save a NivelJerarquico.
     *
     * @param nivelJerarquico the entity to save.
     * @return the persisted entity.
     */
    NivelJerarquico save(NivelJerarquicoDTO nivelJerarquico) throws Exception;
    
    Optional<NivelJerarquico> updateNivelJerarquico(NivelJerarquicoDTO nivelJerarquicoDTO) throws Exception;

    /**
     * Get all the nivelJerarquico.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NivelJerarquico> findAll(Pageable pageable);
    
    /**
     * Get the "id" nivelJerarquico.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    NivelJerarquicoResponse findOne(Long id);

    /**
     * Delete the "id" nivelJerarquico.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
