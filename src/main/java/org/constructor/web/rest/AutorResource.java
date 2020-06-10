/**
 * 
 */
package org.constructor.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.constructor.domain.Autor;
import org.constructor.service.AutorService;
import org.constructor.utils.RestConstants;
import org.constructor.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * @author Edukai
 *
 */
@RestController
@RequestMapping(RestConstants.PATH_API)
public class AutorResource {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(AutorResource.class);

    /**
     * ENTITY_NAME 
     */
    private static final String ENTITY_NAME = "autor";

    /**
     * applicationName
     */
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * AutorService
     */
    private final AutorService autorService;
    
    /**
     * AutorResource
     *  
     * @param autorService
     */
    public AutorResource(AutorService autorService) {
        this.autorService = autorService;
    }


    /**
     * Post  autor
     * 
     * @param autor
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(path = RestConstants.PATH_AUTHOR)
    public ResponseEntity<Autor> createAuthor(@RequestBody Autor autor) throws URISyntaxException {
        log.debug("REST request to save Author : {}", autor);
        if (autor.getId() != null) {
            throw new BadRequestAlertException("A new component cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Autor result = autorService.save(autor);
        return ResponseEntity.created(new URI("/api/autor/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT autor
     * @param autor
     * @return
     * @throws URISyntaxException
     */
    @PutMapping(path = RestConstants.PATH_AUTHOR)
    public ResponseEntity<Autor> updateAuthor(@RequestBody Autor autor) throws URISyntaxException {
        log.debug("REST request to update Author : {}", autor);
        if (autor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Autor result = autorService.save(autor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, autor.getId().toString()))
            .body(result);
    }
    
    /**
     * Get  autor
     * @param pageable
     * @return
     */
    @GetMapping(path = RestConstants.PATH_AUTHOR)
    public ResponseEntity<List<Autor>> getAllAuthor(Pageable pageable) {
        log.debug("REST request to get a page of Author"); 
        Page<Autor> page = autorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * Get author by id
     * @param id
     * @return
     */
    @GetMapping(path = RestConstants.PATH_AUTHOR_ID)
    public ResponseEntity<Autor> getAuthor(@PathVariable Long id) {
        log.debug("REST request to get Author : {}", id);
        Optional<Autor> autor = autorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(autor);
    }
    
    
    /**
     * Delete author by id
     * 
     * @param id
     * @return
     */
    @DeleteMapping(path = RestConstants.PATH_AUTHOR_ID)
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.debug("REST request to delete Author : {}", id);
        autorService.delete(id);
        return ResponseEntity.noContent()
     		   .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
}
