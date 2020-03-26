package org.constructor.service.impl;

import org.constructor.service.RolColaboradorService;
import org.constructor.domain.RolColaborador;
import org.constructor.repository.RolColaboradorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RolColaborador}.
 */
@Service
@Transactional
public class RolColaboradorServiceImpl implements RolColaboradorService {

    private final Logger log = LoggerFactory.getLogger(RolColaboradorServiceImpl.class);

    private final RolColaboradorRepository rolColaboradorRepository;

    public RolColaboradorServiceImpl(RolColaboradorRepository rolColaboradorRepository) {
        this.rolColaboradorRepository = rolColaboradorRepository;
    }

    /**
     * Save a rolColaborador.
     *
     * @param rolColaborador the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RolColaborador save(RolColaborador rolColaborador) {
        log.debug("Request to save RolColaborador : {}", rolColaborador);
        return rolColaboradorRepository.save(rolColaborador);
    }

    /**
     * Get all the rolColaboradors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RolColaborador> findAll(Pageable pageable) {
        log.debug("Request to get all RolColaboradors");
        return rolColaboradorRepository.findAll(pageable);
    }


    /**
     * Get one rolColaborador by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RolColaborador> findOne(Long id) {
        log.debug("Request to get RolColaborador : {}", id);
        return rolColaboradorRepository.findById(id);
    }

    /**
     * Delete the rolColaborador by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RolColaborador : {}", id);
        rolColaboradorRepository.deleteById(id);
    }
}
