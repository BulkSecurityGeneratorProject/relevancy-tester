package com.relevancytester.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.relevancytester.service.ExtractorService;
import com.relevancytester.service.ProjectService;
import com.relevancytester.service.TestCaseService;
import com.relevancytester.service.dto.ExtractorDTO;
import com.relevancytester.service.dto.ProjectDTO;
import com.relevancytester.service.dto.TestCaseDTO;
import com.relevancytester.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Project.
 */
@RestController
@RequestMapping("/api")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private static final String ENTITY_NAME = "project";

    private final ProjectService projectService;
    private final TestCaseService testCaseService;
    private final ExtractorService extractorService;

    public ProjectResource(ProjectService projectService, TestCaseService testCaseService, ExtractorService extractorService) {
        this.projectService = projectService;
        this.testCaseService = testCaseService;
        this.extractorService = extractorService;
    }

    /**
     * POST  /projects : Create a new project.
     *
     * @param projectDTO the projectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectDTO, or with status 400 (Bad Request) if the project has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/projects")
    @Timed
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) throws URISyntaxException {
        log.debug("REST request to save Project : {}", projectDTO);
        if (projectDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new project cannot already have an ID")).body(null);
        }
        ProjectDTO result = projectService.save(projectDTO);
        return ResponseEntity.created(new URI("/api/projects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /projects : Updates an existing project.
     *
     * @param projectDTO the projectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectDTO,
     * or with status 400 (Bad Request) if the projectDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/projects")
    @Timed
    public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO) throws URISyntaxException {
        log.debug("REST request to update Project : {}", projectDTO);
        if (projectDTO.getId() == null) {
            return createProject(projectDTO);
        }
        ProjectDTO result = projectService.save(projectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /projects : get all the projects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of projects in body
     */
    @GetMapping("/projects")
    @Timed
    public List<ProjectDTO> getAllProjects() {
        log.debug("REST request to get all Projects");
        return projectService.findAll();
    }

    /**
     * GET  /projects/:id : get the "id" project.
     *
     * @param id the id of the projectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/projects/{id}")
    @Timed
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id) {
        log.debug("REST request to get Project : {}", id);
        ProjectDTO projectDTO = projectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectDTO));
    }

    /**
     * DELETE  /projects/:id : delete the "id" project.
     *
     * @param id the id of the projectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/projects/{id}")
    @Timed
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        log.debug("REST request to delete Project : {}", id);
        projectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /project/:projectId/test-cases : get all the testCases for project.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of testCases in body
     */
    @GetMapping("/projects/{projectId}/test-cases")
    @Timed
    public List<TestCaseDTO> getAllTestCases(@PathVariable Long projectId) {
        log.debug("REST request to get all TestCases for project");
        ProjectDTO projectDTO = projectService.findOne(projectId);
        return testCaseService.findAllByProject(projectDTO);
    }

    /**
     * GET  /project/:projectId/extractors : get all the extractors for project.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of testCases in body
     */
    @GetMapping("/projects/{projectId}/extractors")
    @Timed
    public List<ExtractorDTO> getAllExtractors(@PathVariable Long projectId) {
        log.debug("REST request to get all Extractors for project");
        ProjectDTO projectDTO = projectService.findOne(projectId);
        return extractorService.findAllByProject(projectDTO);
    }
}
