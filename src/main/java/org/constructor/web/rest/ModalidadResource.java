package org.constructor.web.rest;

import org.constructor.domain.Modalidad;
import org.constructor.service.ModalidadService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.constructor.domain.Modalidad}.
 */
@RestController
@RequestMapping("/api")
public class ModalidadResource {

    private final Logger log = LoggerFactory.getLogger(ModalidadResource.class);

    private static final String ENTITY_NAME = "modalidad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModalidadService modalidadService;

    public ModalidadResource(ModalidadService modalidadService) {
        this.modalidadService = modalidadService;
    }

    /**
     * {@code POST  /modalidads} : Create a new modalidad.
     *
     * @param modalidad the modalidad to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modalidad, or with status {@code 400 (Bad Request)} if the modalidad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modalidads")
    public ResponseEntity<Modalidad> createModalidad(@RequestBody Modalidad modalidad) throws URISyntaxException {
        log.debug("REST request to save Modalidad : {}", modalidad);
        if (modalidad.getId() != null) {
            throw new BadRequestAlertException("A new modalidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Modalidad result = modalidadService.save(modalidad);
        return ResponseEntity.created(new URI("/api/modalidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modalidads} : Updates an existing modalidad.
     *
     * @param modalidad the modalidad to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modalidad,
     * or with status {@code 400 (Bad Request)} if the modalidad is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modalidad couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modalidads")
    public ResponseEntity<Modalidad> updateModalidad(@RequestBody Modalidad modalidad) throws URISyntaxException {
        log.debug("REST request to update Modalidad : {}", modalidad);
        if (modalidad.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Modalidad result = modalidadService.save(modalidad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modalidad.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /modalidads} : get all the modalidads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modalidads in body.
     */
    @GetMapping("/modalidads")
    public ResponseEntity<List<Modalidad>> getAllModalidads(Pageable pageable) {
        log.debug("REST request to get a page of Modalidads");
        Page<Modalidad> page = modalidadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modalidads/:id} : get the "id" modalidad.
     *
     * @param id the id of the modalidad to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modalidad, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modalidads/{id}")
    public ResponseEntity<Modalidad> getModalidad(@PathVariable Long id) {
        log.debug("REST request to get Modalidad : {}", id);
        Optional<Modalidad> modalidad = modalidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modalidad);
    }

    /**
     * {@code DELETE  /modalidads/:id} : delete the "id" modalidad.
     *
     * @param id the id of the modalidad to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modalidads/{id}")
    public ResponseEntity<Void> deleteModalidad(@PathVariable Long id) {
        log.debug("REST request to delete Modalidad : {}", id);
        modalidadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
