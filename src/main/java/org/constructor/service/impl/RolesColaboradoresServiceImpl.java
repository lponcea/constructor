package org.constructor.service.impl;

import org.constructor.service.RolColaboradorService;
import org.constructor.service.RolesColaboradoresService;
import org.constructor.domain.RolColaborador;
import org.constructor.domain.RolesColaboradores;
import org.constructor.repository.RolColaboradorRepository;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RolesColaboradores> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return rolesColaboradoresRepository.findAll(pageable);
	}

	@Override
	public Optional<RolesColaboradores> findOne(Long id) {
		// TODO Auto-generated method stub
		return rolesColaboradoresRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		rolesColaboradoresRepository.deleteById(id);
	}

    
}
