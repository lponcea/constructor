/**
 * 
 */
package org.constructor.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.constructor.domain.Componente;
import org.constructor.service.ComponenteService;
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
 * REST controller Component
 */
@RestController
@RequestMapping("/api")
public class ComponenteResource {

	/**
	 * Logger
	 */
    private final Logger log = LoggerFactory.getLogger(ComponenteResource.class);

    /**
     * ENTITY_NAME 
     */
    private static final String ENTITY_NAME = "componente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * ComponenteService
     */
    private final ComponenteService componenteService;

    /**
     * ComponenteResource
     *  
     * @param componenteService
     */
    public ComponenteResource(ComponenteService componenteService) {
        this.componenteService = componenteService;
    }
    

    /**
     * {@code POST  /component} : Create a new componente.
     *
     * @param componente the componente to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new componente, 
     * or with status {@code 400 (Bad Request)} if the componente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/component")
    public ResponseEntity<Componente> createComponente(@RequestBody Componente componente) throws URISyntaxException {
        log.debug("REST request to save Component : {}", componente);
        if (componente.getId() != null) {
            throw new BadRequestAlertException("A new component cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Componente result = componenteService.save(componente);
        return ResponseEntity.created(new URI("/api/component/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
    * {@code PUT  /component} : Updates an existing componente.
    *
    * @param componente the componente to update.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated componente,
    * or with status {@code 400 (Bad Request)} if the componente is not valid,
    * or with status {@code 500 (Internal Server Error)} if the componente couldn't be updated.
    * @throws URISyntaxException if the Location URI syntax is incorrect.
    */
   @PutMapping("/component")
   public ResponseEntity<Componente> updateComponente(@RequestBody Componente componente) throws URISyntaxException {
       log.debug("REST request to update Component : {}", componente);
       if (componente.getId() == null) {
           throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
       }
       Componente result = componenteService.save(componente);
       return ResponseEntity.ok()
           .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, componente.getId().toString()))
           .body(result);
   }
   
   /**
    * {@code GET  /component} : get all the component.
    *
    * @param pageable the pagination information.
    * 
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of component in body.
    */
   @GetMapping("/component")
   public ResponseEntity<List<Componente>> getAllComponente(Pageable pageable) {
       log.debug("REST request to get a page of Component"); 
       Page<Componente> page = componenteService.findAll(pageable);
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
       return ResponseEntity.ok().headers(headers).body(page.getContent());
   }
   
   /**
    * {@code GET  /component/:id} : get the "id" componente.
    *
    * @param id the id of the componente to retrieve.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the componente,
    *  or with status {@code 404 (Not Found)}.
    */
   @GetMapping("/component/{id}")
   public ResponseEntity<Componente> getComponente(@PathVariable Long id) {
       log.debug("REST request to get Component : {}", id);
       Optional<Componente> componente = componenteService.findOne(id);
       return ResponseUtil.wrapOrNotFound(componente);
   }
   
   /**
    * {@code DELETE  /component/:id} : delete the "id" componente.
    *
    * @param id the id of the componente to delete.
    * 
    * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
    */
   @DeleteMapping("/component/{id}")
   public ResponseEntity<Void> deleteComponente(@PathVariable Long id) {
       log.debug("REST request to delete Component : {}", id);
       componenteService.delete(id);
       return ResponseEntity.noContent()
    		   .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
   }
}
