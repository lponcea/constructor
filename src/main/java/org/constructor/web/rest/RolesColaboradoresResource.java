package org.constructor.web.rest;

import org.constructor.domain.RolColaborador;
import org.constructor.domain.RolesColaboradores;
import org.constructor.service.RolColaboradorService;
import org.constructor.service.RolesColaboradoresService;
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
 * REST controller for managing {@link org.constructor.domain.RolColaborador}.
 */
@RestController
@RequestMapping("/api")
public class RolesColaboradoresResource {

    private final Logger log = LoggerFactory.getLogger(RolesColaboradoresResource.class);

    private static final String ENTITY_NAME = "rolColaborador";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RolesColaboradoresService rolesColaboradoresService;

    public RolesColaboradoresResource(RolesColaboradoresService rolesColaboradoresService) {
        this.rolesColaboradoresService = rolesColaboradoresService;
    }

    /**
     * {@code POST  /rol-colaboradors} : Create a new rolColaborador.
     *
     * @param rolColaborador the rolColaborador to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rolColaborador, or with status {@code 400 (Bad Request)} if the rolColaborador has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/roles-colaboradores")
    public ResponseEntity<RolesColaboradores> createRolesColaboradores(@RequestBody RolesColaboradores rolesColaboradores) throws URISyntaxException {
        log.debug("REST request to save RolColaborador : {}", rolesColaboradores);
        if (rolesColaboradores.getId() != null) {
            throw new BadRequestAlertException("A new rolColaborador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RolesColaboradores result = rolesColaboradoresService.save(rolesColaboradores);
        return ResponseEntity.created(new URI("/api/roles-colaboradores/" + result.getId()))
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
    @PutMapping("/roles-colaboradores")
    public ResponseEntity<RolesColaboradores> updateRolesColaboradores(@RequestBody RolesColaboradores rolesColaboradores) throws URISyntaxException {
        log.debug("REST request to update RolesColaboradores : {}", rolesColaboradores);
        if (rolesColaboradores.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RolesColaboradores result = rolesColaboradoresService.save(rolesColaboradores);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rolesColaboradores.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rol-colaboradors} : get all the rolColaboradors.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rolColaboradors in body.
     */
    @GetMapping("/roles-colaboradores")
    public ResponseEntity<List<RolesColaboradores>> getAllRolesColaboradores(Pageable pageable) {
        log.debug("REST request to get a page of RolesColaboradores");
        Page<RolesColaboradores> page = rolesColaboradoresService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rol-colaboradors/:id} : get the "id" rolColaborador.
     *
     * @param id the id of the rolColaborador to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rolColaborador, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/roles-colaboradores/{id}")
    public ResponseEntity<RolesColaboradores> getRolesColaboradores(@PathVariable Long id) {
        log.debug("REST request to get RolesColaboradores : {}", id);
        Optional<RolesColaboradores> rolesColaboradores = rolesColaboradoresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rolesColaboradores);
    }

    /**
     * {@code DELETE  /rol-colaboradors/:id} : delete the "id" rolColaborador.
     *
     * @param id the id of the rolColaborador to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/roles-colaboradores/{id}")
    public ResponseEntity<Void> deleteRolesColaboradores(@PathVariable Long id) {
        log.debug("REST request to delete RolesColaboradores : {}", id);
        rolesColaboradoresService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
