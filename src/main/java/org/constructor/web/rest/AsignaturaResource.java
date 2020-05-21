package org.constructor.web.rest;

import org.constructor.domain.Asignatura;
import org.constructor.service.AsignaturaService;
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
 * REST controller for managing {@link org.constructor.domain.Asignatura}.
 */
@RestController
@RequestMapping("/api")
public class AsignaturaResource {

    private final Logger log = LoggerFactory.getLogger(AsignaturaResource.class);

    private static final String ENTITY_NAME = "asignatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AsignaturaService asignaturaService;

    public AsignaturaResource(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    /**
     * {@code POST  /asignaturas} : Create a new asignatura.
     *
     * @param asignatura the asignatura to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new asignatura, or with status {@code 400 (Bad Request)} if the asignatura has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/asignaturas")
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) throws URISyntaxException {
        log.debug("REST request to save Asignatura : {}", asignatura);
        if (asignatura.getId() != null) {
            throw new BadRequestAlertException("A new asignatura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Asignatura result = asignaturaService.save(asignatura);
        return ResponseEntity.created(new URI("/api/asignaturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /asignaturas} : Updates an existing asignatura.
     *
     * @param asignatura the asignatura to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated asignatura,
     * or with status {@code 400 (Bad Request)} if the asignatura is not valid,
     * or with status {@code 500 (Internal Server Error)} if the asignatura couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/asignaturas")
    public ResponseEntity<Asignatura> updateAsignatura(@RequestBody Asignatura asignatura) throws URISyntaxException {
        log.debug("REST request to update Asignatura : {}", asignatura);
        if (asignatura.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Asignatura result = asignaturaService.save(asignatura);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, asignatura.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /asignaturas} : get all the asignaturas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of asignaturas in body.
     */
    @GetMapping("/asignaturas")
    public ResponseEntity<List<Asignatura>> getAllAsignaturas(Pageable pageable) {
        log.debug("REST request to get a page of Asignaturas");
        Page<Asignatura> page = asignaturaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /asignaturas/:id} : get the "id" asignatura.
     *
     * @param id the id of the asignatura to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the asignatura, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/asignaturas/{id}")
    public ResponseEntity<Asignatura> getAsignatura(@PathVariable Long id) {
        log.debug("REST request to get Asignatura : {}", id);
        Optional<Asignatura> asignatura = asignaturaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(asignatura);
    }

    /**
     * {@code DELETE  /asignaturas/:id} : delete the "id" asignatura.
     *
     * @param id the id of the asignatura to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/asignaturas/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        log.debug("REST request to delete Asignatura : {}", id);
        asignaturaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
