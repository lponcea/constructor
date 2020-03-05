package org.constructor.web.rest;

import org.constructor.domain.NumeroGrado;
import org.constructor.service.NumeroGradoService;
import org.constructor.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.constructor.domain.NumeroGrado}.
 */
@RestController
@RequestMapping("/api")
public class NumeroGradoResource {

    private final Logger log = LoggerFactory.getLogger(NumeroGradoResource.class);

    private static final String ENTITY_NAME = "numeroGrado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NumeroGradoService numeroGradoService;

    public NumeroGradoResource(NumeroGradoService numeroGradoService) {
        this.numeroGradoService = numeroGradoService;
    }

    /**
     * {@code POST  /numero-grados} : Create a new numeroGrado.
     *
     * @param numeroGrado the numeroGrado to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new numeroGrado, or with status {@code 400 (Bad Request)} if the numeroGrado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/numero-grados")
    public ResponseEntity<NumeroGrado> createNumeroGrado(@RequestBody NumeroGrado numeroGrado) throws URISyntaxException {
        log.debug("REST request to save NumeroGrado : {}", numeroGrado);
        if (numeroGrado.getId() != null) {
            throw new BadRequestAlertException("A new numeroGrado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NumeroGrado result = numeroGradoService.save(numeroGrado);
        return ResponseEntity.created(new URI("/api/numero-grados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /numero-grados} : Updates an existing numeroGrado.
     *
     * @param numeroGrado the numeroGrado to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated numeroGrado,
     * or with status {@code 400 (Bad Request)} if the numeroGrado is not valid,
     * or with status {@code 500 (Internal Server Error)} if the numeroGrado couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/numero-grados")
    public ResponseEntity<NumeroGrado> updateNumeroGrado(@RequestBody NumeroGrado numeroGrado) throws URISyntaxException {
        log.debug("REST request to update NumeroGrado : {}", numeroGrado);
        if (numeroGrado.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NumeroGrado result = numeroGradoService.save(numeroGrado);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, numeroGrado.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /numero-grados} : get all the numeroGrados.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of numeroGrados in body.
     */
    @GetMapping("/numero-grados")
    public ResponseEntity<List<NumeroGrado>> getAllNumeroGrados(Pageable pageable) {
        log.debug("REST request to get a page of NumeroGrados");
        Page<NumeroGrado> page = numeroGradoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /numero-grados/:id} : get the "id" numeroGrado.
     *
     * @param id the id of the numeroGrado to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the numeroGrado, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/numero-grados/{id}")
    public ResponseEntity<NumeroGrado> getNumeroGrado(@PathVariable Long id) {
        log.debug("REST request to get NumeroGrado : {}", id);
        Optional<NumeroGrado> numeroGrado = numeroGradoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(numeroGrado);
    }

    /**
     * {@code DELETE  /numero-grados/:id} : delete the "id" numeroGrado.
     *
     * @param id the id of the numeroGrado to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/numero-grados/{id}")
    public ResponseEntity<Void> deleteNumeroGrado(@PathVariable Long id) {
        log.debug("REST request to delete NumeroGrado : {}", id);
        numeroGradoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
