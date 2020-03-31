package org.constructor.service.impl;

import org.constructor.service.FichaService;
import org.constructor.domain.Ficha;
import org.constructor.repository.FichaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.imap.protocol.ID;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ficha}.
 */
@Service
@Transactional
public class FichaServiceImpl implements FichaService {

    private final Logger log = LoggerFactory.getLogger(FichaServiceImpl.class);

    private final FichaRepository fichaRepository;

    public FichaServiceImpl(FichaRepository fichaRepository) {
        this.fichaRepository = fichaRepository;
    }

    /**
     * Save a ficha.
     *
     * @param ficha the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Ficha save(Ficha ficha) {
        log.debug("Request to save Ficha : {}", ficha);
        return fichaRepository.save(ficha);
    }

    /**
     * Get all the fichas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Ficha> findAll(Pageable pageable) {
        log.debug("Request to get all Fichas");
        return fichaRepository.findAll(pageable);
    }

    /**
     * Get all the fichas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Ficha> findAllWithEagerRelationships(Pageable pageable) {
        return fichaRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one ficha by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Ficha> findOne(Long id) {
        log.debug("Request to get Ficha : {}", id);
        return fichaRepository.findOneWithEagerRelationships(id);
    }


    /**
     * Delete the ficha by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ficha : {}", id);
        fichaRepository.deleteById(id);
    }

}
