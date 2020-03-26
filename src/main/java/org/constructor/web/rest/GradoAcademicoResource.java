package org.constructor.web.rest;

import org.constructor.domain.GradoAcademico;
import org.constructor.service.GradoAcademicoService;
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
 * REST controller for managing {@link org.constructor.domain.GradoAcademico}.
 */
@RestController
@RequestMapping("/api")
public class GradoAcademicoResource {

    private final Logger log = LoggerFactory.getLogger(GradoAcademicoResource.class);

    private static final String ENTITY_NAME = "gradoAcademico";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GradoAcademicoService gradoAcademicoService;

    public GradoAcademicoResource(GradoAcademicoService gradoAcademicoService) {
        this.gradoAcademicoService = gradoAcademicoService;
    }

    /**
     * {@code POST  /grado-academicos} : Create a new gradoAcademico.
     *
     * @param gradoAcademico the gradoAcademico to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gradoAcademico, or with status {@code 400 (Bad Request)} if the gradoAcademico has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grado-academicos")
    public ResponseEntity<GradoAcademico> createGradoAcademico(@RequestBody GradoAcademico gradoAcademico) throws URISyntaxException {
        log.debug("REST request to save GradoAcademico : {}", gradoAcademico);
        if (gradoAcademico.getId() != null) {
            throw new BadRequestAlertException("A new gradoAcademico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GradoAcademico result = gradoAcademicoService.save(gradoAcademico);
        return ResponseEntity.created(new URI("/api/grado-academicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grado-academicos} : Updates an existing gradoAcademico.
     *
     * @param gradoAcademico the gradoAcademico to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gradoAcademico,
     * or with status {@code 400 (Bad Request)} if the gradoAcademico is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gradoAcademico couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grado-academicos")
    public ResponseEntity<GradoAcademico> updateGradoAcademico(@RequestBody GradoAcademico gradoAcademico) throws URISyntaxException {
        log.debug("REST request to update GradoAcademico : {}", gradoAcademico);
        if (gradoAcademico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GradoAcademico result = gradoAcademicoService.save(gradoAcademico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gradoAcademico.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /grado-academicos} : get all the gradoAcademicos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gradoAcademicos in body.
     */
    @GetMapping("/grado-academicos")
    public ResponseEntity<List<GradoAcademico>> getAllGradoAcademicos(Pageable pageable) {
        log.debug("REST request to get a page of GradoAcademicos");
        Page<GradoAcademico> page = gradoAcademicoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /grado-academicos/:id} : get the "id" gradoAcademico.
     *
     * @param id the id of the gradoAcademico to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gradoAcademico, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grado-academicos/{id}")
    public ResponseEntity<GradoAcademico> getGradoAcademico(@PathVariable Long id) {
        log.debug("REST request to get GradoAcademico : {}", id);
        Optional<GradoAcademico> gradoAcademico = gradoAcademicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gradoAcademico);
    }

    /**
     * {@code DELETE  /grado-academicos/:id} : delete the "id" gradoAcademico.
     *
     * @param id the id of the gradoAcademico to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grado-academicos/{id}")
    public ResponseEntity<Void> deleteGradoAcademico(@PathVariable Long id) {
        log.debug("REST request to delete GradoAcademico : {}", id);
        gradoAcademicoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
