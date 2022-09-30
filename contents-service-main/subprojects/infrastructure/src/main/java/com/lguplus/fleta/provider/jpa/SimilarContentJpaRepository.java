package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarContentMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimilarContentJpaRepository extends JpaRepository<SimilarContentEntity, String> {
    SimilarContentEntity findByAlbumId(String albumId);
}
