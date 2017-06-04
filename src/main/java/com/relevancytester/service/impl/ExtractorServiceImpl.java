package com.relevancytester.service.impl;

import com.relevancytester.domain.Extractor;
import com.relevancytester.repository.ExtractorRepository;
import com.relevancytester.service.ExtractorService;
import com.relevancytester.service.dto.ExtractorDTO;
import com.relevancytester.service.dto.ProjectDTO;
import com.relevancytester.service.mapper.ExtractorMapper;
import com.relevancytester.service.mapper.ProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ExtractorServiceImpl implements ExtractorService {

    private final Logger log = LoggerFactory.getLogger(ExtractorServiceImpl.class);

    private final ExtractorRepository extractorRepository;

    private final ExtractorMapper extractorMapper;

    private final ProjectMapper projectMapper;

    public ExtractorServiceImpl(ExtractorRepository extractorRepository, ExtractorMapper extractorMapper, ProjectMapper projectMapper) {
        this.extractorRepository = extractorRepository;
        this.extractorMapper = extractorMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public ExtractorDTO save(ExtractorDTO extractorDTO) {
        log.debug("Request to save Extractor : {}", extractorDTO);
        Extractor extractor = extractorMapper.extractorDTOToExtractor(extractorDTO);
        extractor.setCreatedDate(ZonedDateTime.now());
        extractor = extractorRepository.save(extractor);
        ExtractorDTO result = extractorMapper.extractorToExtractorDTO(extractor);
        return result;
    }

    @Override
    public List<ExtractorDTO> findAll() {
        log.debug("Request to get all Extractors");
        List<ExtractorDTO> result = extractorRepository.findAll().stream()
            .map(extractorMapper::extractorToExtractorDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    @Override
    public ExtractorDTO findOne(Long id) {
        log.debug("Request to get Extractor : {}", id);
        Extractor extractor = extractorRepository.findOne(id);
        ExtractorDTO result = extractorMapper.extractorToExtractorDTO(extractor);
        return result;
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Extractor : {}", id);
        extractorRepository.delete(id);
    }

    @Override
    public List<ExtractorDTO> findAllByProject(ProjectDTO project) {
        List<Extractor> cases = extractorRepository.findAllByProject(projectMapper.projectDTOToProject(project));
        return cases.stream().map(extractorMapper::extractorToExtractorDTO).collect(toList());
    }
}
