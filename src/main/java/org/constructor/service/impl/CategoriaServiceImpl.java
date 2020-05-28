package org.constructor.service.impl;

import org.constructor.service.CategoriaService;
import org.constructor.domain.Categoria;
import org.constructor.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Categoria}.
 */
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    /**
     * Repository 
     */
    private final CategoriaRepository categoriaRepository;

    /**
     * CategoriaServiceImpl
     * 
     * @param categoriaRepository
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Save a categoria.
     *
     * @param categoria the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Categoria save(Categoria categoria) {
        log.debug("Request to save Categoria : {}", categoria);
        return categoriaRepository.save(categoria);
    }

    /**
     * Get all the categorias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Categoria> findAll(Pageable pageable) {
        log.debug("Request to get all Categorias");
        return categoriaRepository.findAll(pageable);
    }


    /**
     * Get one categoria by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> findOne(Long id) {
        log.debug("Request to get Categoria : {}", id);
        return categoriaRepository.findById(id);
    }

    /**
     * Delete the categoria by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Categoria : {}", id);
        categoriaRepository.deleteById(id);
    }
}
