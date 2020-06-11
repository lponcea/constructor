package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.BloquesCurso;
import org.constructor.repository.BloquesCursoRepository;
import org.constructor.service.BloquesCursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class BloquesCursoServiceImpl.
 */
@Service
@Transactional
public class BloquesCursoServiceImpl implements BloquesCursoService {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BloquesCursoServiceImpl.class);
	
	/** The bloques curso repository. */
	@Autowired
	private  BloquesCursoRepository bloquesCursoRepository;

	/**
	 * Save.
	 *
	 * @param bloquesCurso the bloques curso
	 * @return the bloques curso
	 */
	@Override
	public BloquesCurso save(BloquesCurso bloquesCurso) {
		
		return bloquesCursoRepository.save(bloquesCurso);
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Override
	public Page<BloquesCurso> findAll(Pageable pageable) {
		
		return bloquesCursoRepository.findAll(pageable);
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<BloquesCurso> findOne(Long id) {
		
		return bloquesCursoRepository.findById(id);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Long id) {
		bloquesCursoRepository.deleteById(id);
	}

}
