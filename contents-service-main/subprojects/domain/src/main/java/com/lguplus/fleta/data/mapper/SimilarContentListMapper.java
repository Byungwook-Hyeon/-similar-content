package com.lguplus.fleta.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.SimilarContentMetaEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(config = ObjectMapperConfig.class)
public interface SimilarContentListMapper {
    SimilarContentDto toDto(SimilarContentMetaEntity similarContentMetaEntity);
}
