package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.vo.SimilarContentVo;
import org.mapstruct.Mapper;

@Mapper(config = ObjectMapperConfig.class)
public interface SimilarContentRequestMapper {
    SimilarRequestDto toDto(SimilarContentVo similarContentVo);
}
