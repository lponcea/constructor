package org.constructor.service.impl;

import java.util.Optional;

import org.constructor.domain.PhoneNumber;
import org.constructor.repository.PhoneNumberRepository;
import org.constructor.service.PhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PhoneNumber }.
 */

@Service
@Transactional
public class PhoneNumberServiceImpl implements PhoneNumberService {
	
	private final Logger log = LoggerFactory.getLogger(PhoneNumberServiceImpl.class);
	private final PhoneNumberRepository phoneNumberRepository;
	
    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

	@Override
	public PhoneNumber save(PhoneNumber phoneNumber) {
		log.debug("Request Service to save PhoneNumber : {}",phoneNumber);
		return phoneNumberRepository.save(phoneNumber);
	}

	@Override
	public Page<PhoneNumber> findAll(Pageable pageable) {
		log.debug("Request Service to get all PhoneNumber");
		return phoneNumberRepository.findAll(pageable);
	}

	@Override
	public Optional<PhoneNumber> findOne(Long id) {
		log.debug("Request Service to get PhoneNumber : {}",id);
		return phoneNumberRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request Service to delete PhoneNumber : {}",id);
		phoneNumberRepository.deleteById(id);
	}

}
