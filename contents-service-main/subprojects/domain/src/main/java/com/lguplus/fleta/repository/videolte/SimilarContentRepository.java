package com.lguplus.fleta.repository.videolte;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarContentMetaEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SimilarContentRepository {

    SimilarContentEntity getSimilarContentList(SimilarRequestDto similarRequestDto);
    SimilarContentMetaEntity getContentMetaList(String albumId);

    List<SimilarContentMetaEntity> getContentMetaList(Iterable<String> albumId);
}