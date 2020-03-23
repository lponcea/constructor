package org.constructor.service.impl;

import org.constructor.service.CursoService;
import org.constructor.service.FichaService;
import org.constructor.domain.Curso;
import org.constructor.domain.CursoFicha;
import org.constructor.domain.Ficha;
import org.constructor.repository.CursoRepository;
import org.constructor.repository.FichaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Curso}.
 */
@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    private final Logger log = LoggerFactory.getLogger(CursoServiceImpl.class);

    private final CursoRepository cursoRepository;
    
    @Autowired
    private FichaService fichaService;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    /**
     * Save a curso.
     *
     * @param curso the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Curso save(Curso curso) {
        log.debug("Request to save Curso : {}", curso);
        return cursoRepository.save(curso);
    }

    /**
     * Get all the cursos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Curso> findAll(Pageable pageable) {
        log.debug("Request to get all Cursos");
        return cursoRepository.findAll(pageable);
    }


    /**
     * Get one curso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findOne(Long id) {
        log.debug("Request to get Curso : {}", id);
        return cursoRepository.findById(id);
    }

    /**
     * Delete the curso by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Curso : {}", id);
        cursoRepository.deleteById(id);
    }

	@Override
	public CursoFicha save(CursoFicha cursoFicha) {
	
		log.debug("Request to save Curso : {}", cursoFicha);
		Curso curso = new Curso();
		Ficha ficha =  new Ficha();
		CursoFicha cf = new CursoFicha();
		
		curso = cursoFicha.getCurso();
		ficha = cursoFicha.getFicha();
		
		log.debug("Request to save Curso : {}", curso);
		log.debug("Request to save ficha : {}", ficha);
		
		curso = cursoRepository.save(curso);
		
		ficha.setCurso(curso);
		ficha = fichaService.save(ficha);
		
		log.debug("ficha id {}", ficha.getCurso().getId());
		log.debug("curso id {}", curso.getId());
		
		cf.setCurso(curso);
		cf.setFicha(ficha);
		
		return cf;
	}
}
