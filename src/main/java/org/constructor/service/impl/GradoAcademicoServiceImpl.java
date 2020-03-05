package org.constructor.service.impl;

import org.constructor.service.GradoAcademicoService;
import org.constructor.domain.GradoAcademico;
import org.constructor.repository.GradoAcademicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GradoAcademico}.
 */
@Service
@Transactional
public class GradoAcademicoServiceImpl implements GradoAcademicoService {

    private final Logger log = LoggerFactory.getLogger(GradoAcademicoServiceImpl.class);

    private final GradoAcademicoRepository gradoAcademicoRepository;

    public GradoAcademicoServiceImpl(GradoAcademicoRepository gradoAcademicoRepository) {
        this.gradoAcademicoRepository = gradoAcademicoRepository;
    }

    /**
     * Save a gradoAcademico.
     *
     * @param gradoAcademico the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GradoAcademico save(GradoAcademico gradoAcademico) {
        log.debug("Request to save GradoAcademico : {}", gradoAcademico);
        return gradoAcademicoRepository.save(gradoAcademico);
    }

    /**
     * Get all the gradoAcademicos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GradoAcademico> findAll(Pageable pageable) {
        log.debug("Request to get all GradoAcademicos");
        return gradoAcademicoRepository.findAll(pageable);
    }


    /**
     * Get one gradoAcademico by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GradoAcademico> findOne(Long id) {
        log.debug("Request to get GradoAcademico : {}", id);
        return gradoAcademicoRepository.findById(id);
    }

    /**
     * Delete the gradoAcademico by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GradoAcademico : {}", id);
        gradoAcademicoRepository.deleteById(id);
    }
}
