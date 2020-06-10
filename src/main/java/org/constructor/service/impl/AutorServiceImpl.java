/**
 * 
 */
package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.Autor;
import org.constructor.repository.AutorRepository;
import org.constructor.service.AutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Edukai
 *
 */
@Service
@Transactional
public class AutorServiceImpl implements AutorService {
	
	/**
	 * Logger 
	 */
	private final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	
	/**
	 * Repository
	 */
	private final AutorRepository repository;
	

	/**
	 * AutorServiceImpl
	 * 
	 * @param repository
	 */
    public AutorServiceImpl(AutorRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Save
     */
	@Override
	public Autor save(Autor autor) {
		log.debug("Request Service to save Autor : {}",autor);
		return repository.save(autor);
	}
	
	/**
	 * findAll
	 */
	@Override
	public Page<Autor> findAll(Pageable pageable) {
		log.debug("Request service to get all Author");
		return repository.findAll(pageable);
	}
	
	/**
	 * findOne
	 */
	@Override
	public Optional<Autor> findOne(Long id) {
		log.debug("Request Service to get Author : {}",id);
		return repository.findById(id);
	}
	
	/**
	 * Delete by id 
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request Service to delete Author : {}",id);
		repository.deleteById(id);
		
	}

}
