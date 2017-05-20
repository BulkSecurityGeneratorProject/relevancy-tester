package com.relevancytester.service.mapper;

import com.relevancytester.domain.TestCase;
import com.relevancytester.service.dto.TestCaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for the entity TestCase and its DTO TestCaseDTO.
 */
@Mapper(componentModel = "spring", uses = {ProjectMapper.class, })
public interface TestCaseMapper {

    @Mapping(source = "project.id", target = "projectId")
    TestCaseDTO testCaseToTestCaseDTO(TestCase testCase);

    List<TestCaseDTO> testCasesToTestCaseDTOs(List<TestCase> testCases);

    @Mapping(source = "projectId", target = "project")
    TestCase testCaseDTOToTestCase(TestCaseDTO testCaseDTO);

    List<TestCase> testCaseDTOsToTestCases(List<TestCaseDTO> testCaseDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */

    default TestCase testCaseFromId(Long id) {
        if (id == null) {
            return null;
        }
        TestCase testCase = new TestCase();
        testCase.setId(id);
        return testCase;
    }

}
