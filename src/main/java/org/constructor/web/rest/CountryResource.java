package org.constructor.web.rest;

import java.util.List;
import java.util.Optional;

import org.constructor.domain.Country;
import org.constructor.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.constructor.domain.Country}.
 */
@RestController
public class CountryResource {
	
	 private final Logger log = LoggerFactory.getLogger(CountryResource.class);
	 
	 private static final String ENTITY_NAME = "country";
	 
	 @Value("${jhipster.clientApp.name}")
	    private String applicationName;
	 
	 private final CountryService countryService;
	 
	   public CountryResource(CountryService countryService) {
	        this.countryService = countryService;
	    }
	   
	   /**
	     * {@code GET  /country} : get all the countries.
	     *

	     * @param pageable the pagination information.

	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Country in body.
	     */
	    @GetMapping("/country")
	    public ResponseEntity<List<Country>> getAllCountries(Pageable pageable) {
	        log.debug("REST request to get a page of countries");
	        Page<Country> page = countryService.findAll(pageable);
	        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
	        return ResponseEntity.ok().headers(headers).body(page.getContent());
	    }
	    
	    /**
	     * {@code GET  /countries/:id} : get the "id" country.
	     *
	     * @param id the id of the country to retrieve.
	     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the country, or with status {@code 404 (Not Found)}.
	     */
	    @GetMapping("/country/{id}")
	    public ResponseEntity<Country> getCountry(@PathVariable Long id) {
	        log.debug("REST request to get country : {}", id);
	        Optional<Country> country = countryService.findOne(id);
	        return ResponseUtil.wrapOrNotFound(country);
	    }
}
