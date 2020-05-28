package org.constructor.service.impl;

import org.constructor.service.EditorialService;
import org.constructor.domain.Editorial;
import org.constructor.repository.EditorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Editorial}.
 */
@Service
@Transactional
public class EditorialServiceImpl implements EditorialService {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(EditorialServiceImpl.class);

    /**
     * Repository 
     */
    private final EditorialRepository editorialRepository;

    /**
     * EditorialServiceImpl
     * 
     * @param editorialRepository
     */
    public EditorialServiceImpl(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    /**
     * Save a editorial.
     *
     * @param editorial the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Editorial save(Editorial editorial) {
        log.debug("Request to save Editorial : {}", editorial);
        return editorialRepository.save(editorial);
    }

    /**
     * Get all the editorials.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Editorial> findAll(Pageable pageable) {
        log.debug("Request to get all Editorials");
        return editorialRepository.findAll(pageable);
    }


    /**
     * Get one editorial by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Editorial> findOne(Long id) {
        log.debug("Request to get Editorial : {}", id);
        return editorialRepository.findById(id);
    }

    /**
     * Delete the editorial by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Editorial : {}", id);
        editorialRepository.deleteById(id);
    }
}
