package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.BloqueComponentes;
import org.constructor.repository.BloqueComponentesRepository;
import org.constructor.service.BloqueComponentesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BloqueComponentes}.
 */
@Service
@Transactional
public class BloqueComponentesServiceImpl implements BloqueComponentesService {

	private final Logger log = LoggerFactory.getLogger(BloqueComponentesServiceImpl.class);
	
	private final BloqueComponentesRepository bloqueComponentesRepository;
	
	public BloqueComponentesServiceImpl(BloqueComponentesRepository bloqueComponentesRepository) {
        this.bloqueComponentesRepository = bloqueComponentesRepository;
    }
	
	@Override
	public BloqueComponentes save(BloqueComponentes bloqueComponentes) {
		
		return bloqueComponentesRepository.save(bloqueComponentes);
	}

	@Override
	public Page<BloqueComponentes> findAll(Pageable pageable) {
		
		return bloqueComponentesRepository.findAll(pageable);
	}

	@Override
	public Optional<BloqueComponentes> findOne(Long id) {
		
		return bloqueComponentesRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		
		bloqueComponentesRepository.deleteById(id);
		
	}

}