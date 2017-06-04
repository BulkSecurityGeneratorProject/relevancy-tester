package com.relevancytester.service;

import com.relevancytester.service.dto.ExtractorDTO;
import com.relevancytester.service.dto.ProjectDTO;

import java.util.List;

public interface ExtractorService {

    ExtractorDTO save(ExtractorDTO extractorDTO);


    List<ExtractorDTO> findAll();


    ExtractorDTO findOne(Long id);

    void delete(Long id);

    List<ExtractorDTO> findAllByProject(ProjectDTO project);

}
