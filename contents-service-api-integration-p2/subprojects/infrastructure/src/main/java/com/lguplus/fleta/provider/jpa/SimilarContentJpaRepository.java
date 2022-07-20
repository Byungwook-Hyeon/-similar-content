package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimilarContentJpaRepository extends JpaRepository<SimilarContentEntity, String> {
//    SimilarRequestDto findAllByAlbumId(String val);
    SimilarContentEntity findByAlbumId(String albumId);
}
