package org.constructor.service.impl;

import org.constructor.service.RolesColaboradoresService;
import org.constructor.domain.RolesColaboradores;
import org.constructor.repository.RolesColaboradoresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RolesColaboradores}.
 */
@Service
@Transactional
public class RolesColaboradoresServiceImpl implements RolesColaboradoresService {

    private final Logger log = LoggerFactory.getLogger(RolesColaboradoresServiceImpl.class);

    private final RolesColaboradoresRepository rolesColaboradoresRepository;

    public RolesColaboradoresServiceImpl(RolesColaboradoresRepository rolesColaboradoresRepository) {
        this.rolesColaboradoresRepository = rolesColaboradoresRepository;
    }

	@Override
	public RolesColaboradores save(RolesColaboradores rolColaborador) {
		return rolesColaboradoresRepository.save(rolColaborador);
	}

	@Override
	public Page<RolesColaboradores> findAll(Pageable pageable) {
		return rolesColaboradoresRepository.findAll(pageable);
	}

	@Override
	public Optional<RolesColaboradores> findOne(Long id) {
		return rolesColaboradoresRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		rolesColaboradoresRepository.deleteById(id);
	}
	
}
