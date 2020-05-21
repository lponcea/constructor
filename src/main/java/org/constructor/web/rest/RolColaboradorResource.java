package org.constructor.web.rest;

import org.constructor.domain.RolColaborador;
import org.constructor.service.RolColaboradorService;
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
 * REST controller for managing {@link org.constructor.domain.RolColaborador}.
 */
@RestController
@RequestMapping("/api")
public class RolColaboradorResource {

    private final Logger log = LoggerFactory.getLogger(RolColaboradorResource.class);

    private static final String ENTITY_NAME = "rolColaborador";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RolColaboradorService rolColaboradorService;

    public RolColaboradorResource(RolColaboradorService rolColaboradorService) {
        this.rolColaboradorService = rolColaboradorService;
    }

    /**
     * {@code POST  /rol-colaboradors} : Create a new rolColaborador.
     *
     * @param rolColaborador the rolColaborador to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rolColaborador, or with status {@code 400 (Bad Request)} if the rolColaborador has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rol-colaboradors")
    public ResponseEntity<RolColaborador> createRolColaborador(@RequestBody RolColaborador rolColaborador) throws URISyntaxException {
        log.debug("REST request to save RolColaborador : {}", rolColaborador);
        if (rolColaborador.getId() != null) {
            throw new BadRequestAlertException("A new rolColaborador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RolColaborador result = rolColaboradorService.save(rolColaborador);
        return ResponseEntity.created(new URI("/api/rol-colaboradors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rol-colaboradors} : Updates an existing rolColaborador.
     *
     * @param rolColaborador the rolColaborador to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rolColaborador,
     * or with status {@code 400 (Bad Request)} if the rolColaborador is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rolColaborador couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rol-colaboradors")
    public ResponseEntity<RolColaborador> updateRolColaborador(@RequestBody RolColaborador rolColaborador) throws URISyntaxException {
        log.debug("REST request to update RolColaborador : {}", rolColaborador);
        if (rolColaborador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RolColaborador result = rolColaboradorService.save(rolColaborador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rolColaborador.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rol-colaboradors} : get all the rolColaboradors.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rolColaboradors in body.
     */
    @GetMapping("/rol-colaboradors")
    public ResponseEntity<List<RolColaborador>> getAllRolColaboradors(Pageable pageable) {
        log.debug("REST request to get a page of RolColaboradors");
        Page<RolColaborador> page = rolColaboradorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rol-colaboradors/:id} : get the "id" rolColaborador.
     *
     * @param id the id of the rolColaborador to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rolColaborador, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rol-colaboradors/{id}")
    public ResponseEntity<RolColaborador> getRolColaborador(@PathVariable Long id) {
        log.debug("REST request to get RolColaborador : {}", id);
        Optional<RolColaborador> rolColaborador = rolColaboradorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rolColaborador);
    }

    /**
     * {@code DELETE  /rol-colaboradors/:id} : delete the "id" rolColaborador.
     *
     * @param id the id of the rolColaborador to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rol-colaboradors/{id}")
    public ResponseEntity<Void> deleteRolColaborador(@PathVariable Long id) {
        log.debug("REST request to delete RolColaborador : {}", id);
        rolColaboradorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
