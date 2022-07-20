package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import org.mapstruct.Mapper;

@Mapper(config = ObjectMapperConfig.class)
public interface SimilarMetaMapper {
    SimilarContentDto toDto(SimilarMetaEntity similarMetaEntity);
}
