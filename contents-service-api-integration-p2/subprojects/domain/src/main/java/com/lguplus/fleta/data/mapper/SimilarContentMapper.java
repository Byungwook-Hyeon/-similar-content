package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(config = ObjectMapperConfig.class)
public interface SimilarContentMapper {
    SimilarRequestDto toDto(SimilarContentEntity similarContentEntity);
}

