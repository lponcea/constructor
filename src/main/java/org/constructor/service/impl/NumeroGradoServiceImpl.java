package org.constructor.service.impl;

import org.constructor.service.NumeroGradoService;
import org.constructor.domain.NumeroGrado;
import org.constructor.repository.NumeroGradoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NumeroGrado}.
 */
@Service
@Transactional
public class NumeroGradoServiceImpl implements NumeroGradoService {

	/**
	 * Longger
	 */
    private final Logger log = LoggerFactory.getLogger(NumeroGradoServiceImpl.class);

    /**
     * Repository 
     */
    private final NumeroGradoRepository numeroGradoRepository;

    /**
     * NumeroGradoServiceImpl
     * 
     * @param numeroGradoRepository
     */
    public NumeroGradoServiceImpl(NumeroGradoRepository numeroGradoRepository) {
        this.numeroGradoRepository = numeroGradoRepository;
    }

    /**
     * Save a numeroGrado.
     *
     * @param numeroGrado the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NumeroGrado save(NumeroGrado numeroGrado) {
        log.debug("Request to save NumeroGrado : {}", numeroGrado);
        return numeroGradoRepository.save(numeroGrado);
    }

    /**
     * Get all the numeroGrados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NumeroGrado> findAll(Pageable pageable) {
        log.debug("Request to get all NumeroGrados");
        return numeroGradoRepository.findAll(pageable);
    }


    /**
     * Get one numeroGrado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NumeroGrado> findOne(Long id) {
        log.debug("Request to get NumeroGrado : {}", id);
        return numeroGradoRepository.findById(id);
    }

    /**
     * Delete the numeroGrado by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NumeroGrado : {}", id);
        numeroGradoRepository.deleteById(id);
    }

	/*@Override
	public Page<NumeroGrado> findGrade(Long id) {
		log.debug("Request to get getNumeroGradoxGradoAcademico  : {}", id);
		
		return numeroGradoRepository.findByIdGrade(id);
	}*/
}
