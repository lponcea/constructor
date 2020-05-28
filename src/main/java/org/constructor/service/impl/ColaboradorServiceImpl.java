package org.constructor.service.impl;

import org.constructor.service.ColaboradorService;
import org.constructor.domain.Colaborador;
import org.constructor.repository.ColaboradorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Colaborador}.
 */
@Service
@Transactional
public class ColaboradorServiceImpl implements ColaboradorService {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(ColaboradorServiceImpl.class);

    /**
     * Repository
     */
    private final ColaboradorRepository colaboradorRepository;

    /**
     * ColaboradorServiceImpl
     * 
     * @param colaboradorRepository
     */
    public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    /**
     * Save a colaborador.
     *
     * @param colaborador the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Colaborador save(Colaborador colaborador) {
        log.debug("Request to save Colaborador : {}", colaborador);
        return colaboradorRepository.save(colaborador);
    }

    /**
     * Get all the colaboradors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Colaborador> findAll(Pageable pageable) {
        log.debug("Request to get all Colaboradors");
        return colaboradorRepository.findAll(pageable);
    }


    /**
     * Get one colaborador by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Colaborador> findOne(Long id) {
        log.debug("Request to get Colaborador : {}", id);
        return colaboradorRepository.findById(id);
    }

    /**
     * Delete the colaborador by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Colaborador : {}", id);
        colaboradorRepository.deleteById(id);
    }
}
