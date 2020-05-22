/**
 * 
 */
package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.Componente;
import org.constructor.repository.ComponenteRepository;
import org.constructor.service.ComponenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Component
 */
@Service
@Transactional
public class ComponenteServiceImpl implements ComponenteService {
	
	
	/**
	 * Logger  
	 */
	private final Logger log = LoggerFactory.getLogger(ComponenteServiceImpl.class);
	
	/**
	 * Repository   
	 */
	private final ComponenteRepository componenteRepository;

	/**
	 * ComponenteServiceImpl
	 * 
	 * @param componenteRepository
	 */
	public ComponenteServiceImpl(ComponenteRepository componenteRepository) {
	        this.componenteRepository = componenteRepository;
	    }
	
	  /**
     * Save a component.
     *
     * @param modalidad the entity to save.
     * @return the persisted entity.
     */
	@Override
	public Componente save(Componente componente) {
		 log.debug("Request to save Component : {}", componente);
	        return componenteRepository.save(componente);
	}

	/**
     * Get all the component.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
	@Override
	@Transactional(readOnly = true)
	public Page<Componente> findAll(Pageable pageable) {
	    log.debug("Request to get all Component");
        return componenteRepository.findAll(pageable);
	}

	 /**
     * Get one component by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
	@Override
    @Transactional(readOnly = true)
	public Optional<Componente> findOne(Long id) {
		log.debug("Request to get Component : {}", id);
        return componenteRepository.findById(id);
	}

	/**
     * Delete the component by id.
     *
     * @param id the id of the entity.
     */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Component : {}", id);
		componenteRepository.deleteById(id);
		
	}

}
