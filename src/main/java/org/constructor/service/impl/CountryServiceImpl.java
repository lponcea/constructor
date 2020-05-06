package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.Country;
import org.constructor.repository.CountryRepository;
import org.constructor.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Country}.
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	
	private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);
	private final CountryRepository countryRepository;
	
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

	@Override
	public Country save(Country country) {
		 log.debug("Request Service to save Country : {}",country);
		return countryRepository.save(country);
	}

	@Override
	public Page<Country> findAll(Pageable pageable) {
		log.debug("Request Service to get all Countries");
		return countryRepository.findAll(pageable);
	}

	@Override
	public Optional<Country> findOne(Long id) {
		log.debug("Request Service to get Country : {}",id);
		return countryRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request Service to delete Country : {}",id);
		countryRepository.deleteById(id);
	}

}
