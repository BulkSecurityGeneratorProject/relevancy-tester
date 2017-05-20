package com.relevancytester.service.mapper;

import com.relevancytester.domain.Project;
import com.relevancytester.domain.TestCase;
import com.relevancytester.service.dto.ProjectDTO;
import com.relevancytester.service.dto.TestCaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Mapper for the entity Project and its DTO ProjectDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectMapper {

    ProjectDTO projectToProjectDTO(Project project);

    List<ProjectDTO> projectsToProjectDTOs(List<Project> projects);

    Project projectDTOToProject(ProjectDTO projectDTO);

    List<Project> projectDTOsToProjects(List<ProjectDTO> projectDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */

    default Project projectFromId(Long id) {
        if (id == null) {
            return null;
        }
        Project project = new Project();
        project.setId(id);
        return project;
    }

    default Set<TestCaseDTO> testCaseDTOfromTestCase(Set<TestCase> cases) {
        return cases.stream()
            .map(testCase -> Mappers.getMapper(TestCaseMapper.class).testCaseToTestCaseDTO(testCase))
            .collect(toSet());
    }

}
