package org.constructor.service.impl;

import org.constructor.service.ModalidadService;
import org.constructor.domain.Modalidad;
import org.constructor.repository.ModalidadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Modalidad}.
 */
@Service
@Transactional
public class ModalidadServiceImpl implements ModalidadService {

    private final Logger log = LoggerFactory.getLogger(ModalidadServiceImpl.class);

    private final ModalidadRepository modalidadRepository;

    public ModalidadServiceImpl(ModalidadRepository modalidadRepository) {
        this.modalidadRepository = modalidadRepository;
    }

    /**
     * Save a modalidad.
     *
     * @param modalidad the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Modalidad save(Modalidad modalidad) {
        log.debug("Request to save Modalidad : {}", modalidad);
        return modalidadRepository.save(modalidad);
    }

    /**
     * Get all the modalidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Modalidad> findAll(Pageable pageable) {
        log.debug("Request to get all Modalidads");
        return modalidadRepository.findAll(pageable);
    }


    /**
     * Get one modalidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Modalidad> findOne(Long id) {
        log.debug("Request to get Modalidad : {}", id);
        return modalidadRepository.findById(id);
    }

    /**
     * Delete the modalidad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modalidad : {}", id);
        modalidadRepository.deleteById(id);
    }
}
