package com.relevancytester.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.relevancytester.service.ExtractorService;
import com.relevancytester.service.dto.ExtractorDTO;
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

@RestController
@RequestMapping("/api")
public class ExtractorResource {

    private final Logger log = LoggerFactory.getLogger(ExtractorResource.class);

    private static final String ENTITY_NAME = "extractor";

    private final ExtractorService extractorService;

    public ExtractorResource(ExtractorService extractorService) {
        this.extractorService = extractorService;
    }

    /**
     * POST  /extractors : Create a new extractor.
     *
     * @param extractorDTO the extractorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new extractorDTO, or with status 400 (Bad Request) if the extractor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/extractors")
    @Timed
    public ResponseEntity<ExtractorDTO> createExtractor(@Valid @RequestBody ExtractorDTO extractorDTO) throws URISyntaxException {
        log.debug("REST request to save Extractor : {}", extractorDTO);
        if (extractorDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new testCase cannot already have an ID")).body(null);
        }
        ExtractorDTO result = extractorService.save(extractorDTO);
        return ResponseEntity.created(new URI("/api/extractors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /extractors : Updates an existing extractor.
     *
     * @param  extractorDTO the extractorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated extractorDTO,
     * or with status 400 (Bad Request) if the extractorDTO is not valid,
     * or with status 500 (Internal Server Error) if the extractorDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/extractors")
    @Timed
    public ResponseEntity<ExtractorDTO> updateExtractor(@Valid @RequestBody ExtractorDTO extractorDTO) throws URISyntaxException {
        log.debug("REST request to update TestCase : {}", extractorDTO);
        if (extractorDTO.getId() == null) {
            return createExtractor(extractorDTO);
        }
        ExtractorDTO result = extractorService.save(extractorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, extractorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /extractors : get all the extractors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of extractors in body
     */
    @GetMapping("/extractors")
    @Timed
    public List<ExtractorDTO> getAllExtractors() {
        log.debug("REST request to get all Extractors");
        return extractorService.findAll();
    }

    /**
     * GET  /extractors/:id : get the "id" extractor.
     *
     * @param id the id of the extractorDto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the extractorDto, or with status 404 (Not Found)
     */
    @GetMapping("/extractors/{id}")
    @Timed
    public ResponseEntity<ExtractorDTO> getExtractor(@PathVariable Long id) {
        log.debug("REST request to get Extractor : {}", id);
        ExtractorDTO extractorDTO = extractorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(extractorDTO));
    }

    /**
     * DELETE  /extractors/:id : delete the "id" extractor.
     *
     * @param id the id of the extractorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/extractors/{id}")
    @Timed
    public ResponseEntity<Void> deleteExtractor(@PathVariable Long id) {
        log.debug("REST request to delete Extractor : {}", id);
        extractorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
