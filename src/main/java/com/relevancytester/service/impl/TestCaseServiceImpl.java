package com.relevancytester.service.impl;

import com.relevancytester.domain.TestCase;
import com.relevancytester.repository.TestCaseRepository;
import com.relevancytester.service.TestCaseService;
import com.relevancytester.service.dto.ProjectDTO;
import com.relevancytester.service.dto.TestCaseDTO;
import com.relevancytester.service.mapper.ProjectMapper;
import com.relevancytester.service.mapper.TestCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Service Implementation for managing TestCase.
 */
@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService{

    private final Logger log = LoggerFactory.getLogger(TestCaseServiceImpl.class);

    private final TestCaseRepository testCaseRepository;

    private final TestCaseMapper testCaseMapper;

    private final ProjectMapper projectMapper;

    public TestCaseServiceImpl(TestCaseRepository testCaseRepository, TestCaseMapper testCaseMapper, ProjectMapper projectMapper) {
        this.testCaseRepository = testCaseRepository;
        this.testCaseMapper = testCaseMapper;
        this.projectMapper = projectMapper;
    }

    /**
     * Save a testCase.
     *
     * @param testCaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TestCaseDTO save(TestCaseDTO testCaseDTO) {
        log.debug("Request to save TestCase : {}", testCaseDTO);
        TestCase testCase = testCaseMapper.testCaseDTOToTestCase(testCaseDTO);
        testCase.setCreatedDate(ZonedDateTime.now());
        testCase = testCaseRepository.save(testCase);
        TestCaseDTO result = testCaseMapper.testCaseToTestCaseDTO(testCase);
        return result;
    }

    /**
     *  Get all the testCases.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TestCaseDTO> findAll() {
        log.debug("Request to get all TestCases");
        List<TestCaseDTO> result = testCaseRepository.findAll().stream()
            .map(testCaseMapper::testCaseToTestCaseDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one testCase by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TestCaseDTO findOne(Long id) {
        log.debug("Request to get TestCase : {}", id);
        TestCase testCase = testCaseRepository.findOne(id);
        TestCaseDTO testCaseDTO = testCaseMapper.testCaseToTestCaseDTO(testCase);
        return testCaseDTO;
    }

    /**
     *  Delete the  testCase by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TestCase : {}", id);
        testCaseRepository.delete(id);
    }

    @Override
    public List<TestCaseDTO> findAllByProject(ProjectDTO project) {
        List<TestCase> cases = testCaseRepository.findAllByProject(projectMapper.projectDTOToProject(project));
        return cases.stream().map(testCaseMapper::testCaseToTestCaseDTO).collect(toList());
    }
}
