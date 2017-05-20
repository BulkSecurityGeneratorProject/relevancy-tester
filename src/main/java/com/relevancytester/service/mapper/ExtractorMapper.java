package com.relevancytester.service.mapper;

import com.relevancytester.domain.Extractor;
import com.relevancytester.service.dto.ExtractorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class, })
public interface ExtractorMapper {

    @Mapping(source = "project.id", target = "projectId")
    ExtractorDTO extractorToExtractorDTO(Extractor extractor);

    List<ExtractorDTO> extractorsToExtractorDTOs(List<Extractor> extractors);

    @Mapping(source = "projectId", target = "project")
    Extractor extractorDTOToExtractor(ExtractorDTO extractorDTO);

    List<Extractor> extractorDTOsToExtractorCases(List<ExtractorDTO> extractorDTOs);

    default Extractor extractorFromId(Long id) {
        if (id == null) {
            return null;
        }
        Extractor extractor = new Extractor();
        extractor.setId(id);
        return extractor;
    }

}
