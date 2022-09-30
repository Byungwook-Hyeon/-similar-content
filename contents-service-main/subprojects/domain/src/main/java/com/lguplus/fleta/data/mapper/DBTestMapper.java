package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.DBTestEntity;
import org.mapstruct.Mapper;

@Mapper(config = ObjectMapperConfig.class)
public interface DBTestMapper {
    SimilarContentDto toDto(DBTestEntity dbTestEntity);
}
