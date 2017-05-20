package com.relevancytester.repository;

import com.relevancytester.domain.Extractor;
import com.relevancytester.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring jpa repository for exctractos.
 */
public interface ExtractorRepository extends JpaRepository<Extractor,Long> {

    List<Extractor> findAllByProject(Project project);

}
