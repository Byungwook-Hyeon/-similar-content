package com.lguplus.fleta.domain.repository.jpa;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import com.lguplus.fleta.provider.jpa.SimilarContentJpaRepository;
import com.lguplus.fleta.provider.jpa.SimilarMetaJpaRepository;
import com.lguplus.fleta.repository.similar.SimilarContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SimilarContentRepositoryImpl implements SimilarContentRepository {
    private final SimilarContentJpaRepository similarContentJpaRepository;
    private final SimilarMetaJpaRepository similarMetaJpaRepository;

    @Override
    public SimilarContentEntity getSimilarContent(SimilarRequestDto similarRequestDto) {
        return similarContentJpaRepository.findByAlbumId(similarRequestDto.getAlbumId());
    }

//    @Override
//    public SimilarMetaEntity getVodMeta(SimilarRequestDto similarRequestDto) {
//        System.out.println("################################");
//        System.out.println(similarRequestDto.getAlbumId());
//        System.out.println("################################");
//        return similarMetaJpaRepository.findById(similarRequestDto.getAlbumId());
//    }

    @Override
    public List<SimilarMetaEntity> getMetaList(SimilarRequestDto similarRequestDto) {
        return similarMetaJpaRepository.findById(similarRequestDto.getAlbumId());
    }
}