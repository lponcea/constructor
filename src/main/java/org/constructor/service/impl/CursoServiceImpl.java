package org.constructor.service.impl;

import org.constructor.service.CursoService;
import org.constructor.service.FichaService;
import org.constructor.service.UserService;
import org.constructor.domain.Curso;
import org.constructor.domain.CursoFicha;
import org.constructor.domain.Ficha;
import org.constructor.domain.User;
import org.constructor.repository.CursoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing {@link Curso}.
 */
@Service
@Transactional
public class CursoServiceImpl implements CursoService {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(CursoServiceImpl.class);

    /**
     * Repository CursoRepository
     */
    private final CursoRepository cursoRepository;
    
    /**
     * Service FichaService 
     */
    @Autowired
    private FichaService fichaService;
    
    /**
     * Service UserService 
     */
    @Autowired
    private UserService userService; 

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
     * findAllCursoUserId
     */
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAllCursoUserId(Authentication authentication) {
        log.debug("Request to get all Cursos by User ");
        Set<User> user = new HashSet<>();
        User userName = new User();
        
        //Get userbyLogin
        String usuarioNombre = authentication.getName();
        user = userService.findUserByLogin(usuarioNombre);
        
        for (User usuario : user ) {
        	userName = usuario;
        }
        return cursoRepository.findAllCursoUserId(userName.getId());
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
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Curso : {}", id);
        cursoRepository.deleteById(id);
    }

    /**
     * save
     */
    @Override
	@Transactional
	public CursoFicha save(Authentication authentication, CursoFicha cursoFicha) {
			log.debug("Request to save Curso : {}", cursoFicha);
			Set<User> user = new HashSet<>();
			Curso curso = new Curso();
			Ficha ficha =  new Ficha();
			CursoFicha cf = new CursoFicha();
			
			String usuarioNombre = authentication.getName();
			user = userService.findUserByLogin(usuarioNombre);
			
			
			curso = cursoFicha.getCurso();
			ficha = cursoFicha.getFicha();
			
			log.debug("Request to save Curso : {}", curso);
			//Insert User whit Curso (JAM)
			curso.setUser(user);
			log.debug("update curso whit user  : {}", curso.getUser());
			curso = cursoRepository.save(curso);
			
			log.debug("Request to save ficha : {}", ficha);
			ficha.setCurso(curso);
			ficha = fichaService.save(ficha);
			
			log.debug("ficha id {}", ficha.getCurso().getId());
			log.debug("curso id {}", curso.getId());
			
			cf.setCurso(curso);
			cf.setFicha(ficha);
			
		return cf;
	}

    /**
     * FindCourseCover
     */
	@Override
	public String FindCourseCover(Long id) {
		return cursoRepository.findByCourseCoverId(id);
	}

	/**
	 * FindByContentCourseCover
	 */
	@Override
	public Long FindByContentCourseCover(String content) {
		return cursoRepository.findByContentCourseCover(content);
	}
}
