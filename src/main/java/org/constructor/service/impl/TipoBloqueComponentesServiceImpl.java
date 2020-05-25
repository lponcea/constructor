package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.TipoBloqueComponentes;
import org.constructor.repository.TipoBloqueComponentesRepository;
import org.constructor.service.TipoBloqueComponentesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TipoBloqueComponente}.
 */
@Service
@Transactional
public class TipoBloqueComponentesServiceImpl implements TipoBloqueComponentesService {
	
	private final Logger log = LoggerFactory.getLogger(TipoBloqueComponentesServiceImpl.class);
	
	private final TipoBloqueComponentesRepository tipoBloqueComponentesRepository;
	
	public TipoBloqueComponentesServiceImpl(TipoBloqueComponentesRepository tipoBloqueComponentesRepository) {
        this.tipoBloqueComponentesRepository = tipoBloqueComponentesRepository;
    }

	@Override
	public TipoBloqueComponentes save(TipoBloqueComponentes tipoBloqueComponentes) {
		
		return tipoBloqueComponentesRepository.save(tipoBloqueComponentes);
	}

	@Override
	public Page<TipoBloqueComponentes> findAll(Pageable pageable) {
		
		return tipoBloqueComponentesRepository.findAll(pageable);
	}

	@Override
	public Optional<TipoBloqueComponentes> findOne(Long id) {
		
		return tipoBloqueComponentesRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		tipoBloqueComponentesRepository.deleteById(id);
	}

}
