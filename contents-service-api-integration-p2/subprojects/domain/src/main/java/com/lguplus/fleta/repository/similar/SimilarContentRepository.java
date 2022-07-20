package com.lguplus.fleta.repository.similar;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.dto.response.GenericSimilarResponseDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import java.util.List;

public interface SimilarContentRepository {
    SimilarContentEntity getSimilarContent(SimilarRequestDto similarRequestDto);
//    SimilarMetaEntity getVodMeta(SimilarRequestDto similarRequestDto);
    List<SimilarMetaEntity> getMetaList(SimilarRequestDto similarRequestDto);
}