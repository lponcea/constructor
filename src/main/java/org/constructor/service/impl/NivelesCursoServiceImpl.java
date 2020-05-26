/**
 * 
 */
package org.constructor.service.impl;

import java.util.Optional;
import java.util.Set;

import org.constructor.domain.NivelesCurso;
import org.constructor.repository.NivelesCursoRepository;
import org.constructor.service.NivelesCursoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * Service Implementation for managing  NivelesCurso.
 */
@Service
@Transactional
public class NivelesCursoServiceImpl implements NivelesCursoService {
	
	
	/** NivelesCursoRepository. */
	private final NivelesCursoRepository nivelesCursoRepository;
	
	/**
	 * NivelesCursoServiceImpl.
	 *
	 * @param nivelesCursoRepository the niveles curso repository
	 */
	public NivelesCursoServiceImpl(NivelesCursoRepository nivelesCursoRepository) {
        this.nivelesCursoRepository = nivelesCursoRepository;
    }

	/**
	 *  save  NivelesCurso.
	 *
	 * @param nivelesCurso the niveles curso
	 * @return the niveles curso
	 */
	@Override
	public NivelesCurso save(NivelesCurso nivelesCurso) {
		return nivelesCursoRepository.save(nivelesCurso);
	}

	/**
	 *  findAll  NivelesCurso.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Override
	public Page<NivelesCurso> findAll(Pageable pageable) {
		return nivelesCursoRepository.findAll(pageable) ;
	}

	/**
	 * findOne   NivelesCurso.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<NivelesCurso> findOne(Long id) {
		return nivelesCursoRepository.findById(id);
	}
	
	/**
	 * Find by curso.
	 *
	 * @param id the id
	 * @return the sets the
	 */
	@Override
	public Set<NivelesCurso> findByCurso(Long id) {
		return nivelesCursoRepository.findByIdCurso(id);
	}

	/**
	 * delete id.
	 *
	 * @param id the id
	 */
	@Override
	public void delete(Long id) {
		nivelesCursoRepository.deleteById(id);
		
	}

}
