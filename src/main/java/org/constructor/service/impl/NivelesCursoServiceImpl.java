/**
 * 
 */
package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.NivelesCurso;
import org.constructor.repository.NivelesCursoRepository;
import org.constructor.service.NivelesCursoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing  NivelesCurso.
 */
@Service
@Transactional
public class NivelesCursoServiceImpl implements NivelesCursoService {
	
	
	/**
	 * NivelesCursoRepository
	 */
	private final NivelesCursoRepository nivelesCursoRepository;
	
	/**
	 * NivelesCursoServiceImpl
	 * @param nivelesCursoRepository
	 */
	public NivelesCursoServiceImpl(NivelesCursoRepository nivelesCursoRepository) {
        this.nivelesCursoRepository = nivelesCursoRepository;
    }

	/**
	 *  save  NivelesCurso
	 */
	@Override
	public NivelesCurso save(NivelesCurso nivelesCurso) {
		return nivelesCursoRepository.save(nivelesCurso);
	}

	/**
	 *  findAll  NivelesCurso
	 */
	@Override
	public Page<NivelesCurso> findAll(Pageable pageable) {
		return nivelesCursoRepository.findAll(pageable) ;
	}

	/**
	 * findOne   NivelesCurso
	 */
	@Override
	public Optional<NivelesCurso> findOne(Long id) {
		return nivelesCursoRepository.findById(id);
	}

	/**
	 * delete id
	 */
	@Override
	public void delete(Long id) {
		nivelesCursoRepository.deleteById(id);
		
	}

}
