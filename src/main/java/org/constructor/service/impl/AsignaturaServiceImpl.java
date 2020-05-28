package org.constructor.service.impl;

import org.constructor.service.AsignaturaService;
import org.constructor.domain.Asignatura;
import org.constructor.repository.AsignaturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Asignatura}.
 */
@Service
@Transactional
public class AsignaturaServiceImpl implements AsignaturaService {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(AsignaturaServiceImpl.class);

    /**
     * Repository 
     */
    private final AsignaturaRepository asignaturaRepository;

    /**
     * AsignaturaServiceImpl
     * 
     * @param asignaturaRepository
     */
    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    /**
     * Save a asignatura.
     *
     * @param asignatura the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Asignatura save(Asignatura asignatura) {
        log.debug("Request to save Asignatura : {}", asignatura);
        return asignaturaRepository.save(asignatura);
    }

    /**
     * Get all the asignaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Asignatura> findAll(Pageable pageable) {
        log.debug("Request to get all Asignaturas");
        return asignaturaRepository.findAll(pageable);
    }


    /**
     * Get one asignatura by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Asignatura> findOne(Long id) {
        log.debug("Request to get Asignatura : {}", id);
        return asignaturaRepository.findById(id);
    }

    /**
     * Delete the asignatura by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Asignatura : {}", id);
        asignaturaRepository.deleteById(id);
    }
}
