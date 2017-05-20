package com.relevancytester.service;

import com.relevancytester.service.dto.ProjectDTO;
import com.relevancytester.service.dto.TestCaseDTO;

import java.util.List;

/**
 * Service Interface for managing TestCase.
 */
public interface TestCaseService {

    /**
     * Save a testCase.
     *
     * @param testCaseDTO the entity to save
     * @return the persisted entity
     */
    TestCaseDTO save(TestCaseDTO testCaseDTO);

    /**
     *  Get all the testCases.
     *
     *  @return the list of entities
     */
    List<TestCaseDTO> findAll();

    /**
     *  Get the "id" testCase.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TestCaseDTO findOne(Long id);

    /**
     *  Delete the "id" testCase.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    List<TestCaseDTO> findAllByProject(ProjectDTO project);

}
