package com.lguplus.fleta.domain.repository.jpa;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarContentMetaEntity;
import com.lguplus.fleta.provider.jpa.SimilarContentJpaRepository;
import com.lguplus.fleta.provider.jpa.SimilarContentMetaJpaRepository;
import com.lguplus.fleta.repository.videolte.SimilarContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SimilarContentRepositoryImpl implements SimilarContentRepository {

    private final SimilarContentJpaRepository similarContentJpaRepository;
    private final SimilarContentMetaJpaRepository similarContentMetaJpaRepository;

    @Override
    public SimilarContentEntity getSimilarContentList(SimilarRequestDto similarRequestDto) {
        return similarContentJpaRepository.findByAlbumId(similarRequestDto.getAlbumId());
    }

    @Override
//    public SimilarContentMetaEntity getContentMetaList(String albumId) {
    public SimilarContentMetaEntity getContentMetaList(String albumId) {
        return similarContentMetaJpaRepository.findByIds(albumId);
    }

    public List<SimilarContentMetaEntity> getContentMetaList(Iterable<String> albumIds) {
        return similarContentMetaJpaRepository.findAllById(albumIds);
    }
}
