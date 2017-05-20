package com.relevancytester.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.relevancytester.service.TestCaseService;
import com.relevancytester.service.dto.TestCaseDTO;
import com.relevancytester.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TestCase.
 */
@RestController
@RequestMapping("/api")
public class TestCaseResource {

    private final Logger log = LoggerFactory.getLogger(TestCaseResource.class);

    private static final String ENTITY_NAME = "testCase";

    private final TestCaseService testCaseService;

    public TestCaseResource(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    /**
     * POST  /test-cases : Create a new testCase.
     *
     * @param testCaseDTO the testCaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new testCaseDTO, or with status 400 (Bad Request) if the testCase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/test-cases")
    @Timed
    public ResponseEntity<TestCaseDTO> createTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO) throws URISyntaxException {
        log.debug("REST request to save TestCase : {}", testCaseDTO);
        if (testCaseDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new testCase cannot already have an ID")).body(null);
        }
        TestCaseDTO result = testCaseService.save(testCaseDTO);
        return ResponseEntity.created(new URI("/api/test-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /test-cases : Updates an existing testCase.
     *
     * @param testCaseDTO the testCaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated testCaseDTO,
     * or with status 400 (Bad Request) if the testCaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the testCaseDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/test-cases")
    @Timed
    public ResponseEntity<TestCaseDTO> updateTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO) throws URISyntaxException {
        log.debug("REST request to update TestCase : {}", testCaseDTO);
        if (testCaseDTO.getId() == null) {
            return createTestCase(testCaseDTO);
        }
        TestCaseDTO result = testCaseService.save(testCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, testCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /test-cases : get all the testCases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of testCases in body
     */
    @GetMapping("/test-cases")
    @Timed
    public List<TestCaseDTO> getAllTestCases() {
        log.debug("REST request to get all TestCases");
        return testCaseService.findAll();
    }

    /**
     * GET  /test-cases/:id : get the "id" testCase.
     *
     * @param id the id of the testCaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the testCaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/test-cases/{id}")
    @Timed
    public ResponseEntity<TestCaseDTO> getTestCase(@PathVariable Long id) {
        log.debug("REST request to get TestCase : {}", id);
        TestCaseDTO testCaseDTO = testCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(testCaseDTO));
    }

    /**
     * DELETE  /test-cases/:id : delete the "id" testCase.
     *
     * @param id the id of the testCaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/test-cases/{id}")
    @Timed
    public ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
        log.debug("REST request to delete TestCase : {}", id);
        testCaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
