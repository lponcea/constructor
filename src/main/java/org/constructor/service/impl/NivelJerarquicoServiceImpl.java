package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.NivelJerarquico;
import org.constructor.repository.NivelJerarquicoRepository;
import org.constructor.service.NivelJerarquicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NivelJerarquico}.
 */
@Service
@Transactional
public class NivelJerarquicoServiceImpl  implements NivelJerarquicoService {
	
	private final Logger log = LoggerFactory.getLogger(NivelJerarquicoServiceImpl.class);
	
	 private final NivelJerarquicoRepository nivelJerarquicoRepository;
	 
	    public NivelJerarquicoServiceImpl(NivelJerarquicoRepository nivelJerarquicoRepository) {
	        this.nivelJerarquicoRepository = nivelJerarquicoRepository;
	    }

	@Override
	public NivelJerarquico save(NivelJerarquico nivelJerarquico) {
		
		return nivelJerarquicoRepository.save(nivelJerarquico);
	}

	@Override
	public Page<NivelJerarquico> findAll(Pageable pageable) {
		
		return nivelJerarquicoRepository.findAll(pageable);
	}

	@Override
	public Optional<NivelJerarquico> findOne(Long id) {
		
		return nivelJerarquicoRepository.findById(id) ;
	}

	@Override
	public void delete(Long id) {
		
		nivelJerarquicoRepository.deleteById(id);
	}

}
