package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.entity.SimilarContentMetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimilarContentMetaJpaRepository extends JpaRepository<SimilarContentMetaEntity, String> {
    SimilarContentMetaEntity findByIds(String id);
}