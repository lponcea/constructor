package org.constructor.service.impl;

import org.constructor.service.VersionService;
import org.constructor.domain.Version;
import org.constructor.repository.VersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Version}.
 */
@Service
@Transactional
public class VersionServiceImpl implements VersionService {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(VersionServiceImpl.class);

    /**
     * Repository
     */
    private final VersionRepository versionRepository;

    /**
     * VersionServiceImpl
     * 
     * @param versionRepository
     */
    public VersionServiceImpl(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    /**
     * Save a version.
     *
     * @param version the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Version save(Version version) {
        log.debug("Request to save Version : {}", version);
        return versionRepository.save(version);
    }

    /**
     * Get all the versions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Version> findAll(Pageable pageable) {
        log.debug("Request to get all Versions");
        return versionRepository.findAll(pageable);
    }


    /**
     * Get one version by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Version> findOne(Long id) {
        log.debug("Request to get Version : {}", id);
        return versionRepository.findById(id);
    }

    /**
     * Delete the version by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Version : {}", id);
        versionRepository.deleteById(id);
    }
}
