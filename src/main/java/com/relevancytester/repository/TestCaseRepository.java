package com.relevancytester.repository;

import com.relevancytester.domain.Project;
import com.relevancytester.domain.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the TestCase entity.
 */
@SuppressWarnings("unused")
public interface TestCaseRepository extends JpaRepository<TestCase,Long> {

    List<TestCase> findAllByProject(Project project);

}
