package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SimilarMetaJpaRepository extends JpaRepository<SimilarMetaEntity, SimilarContentDto> {
//    SimilarMetaEntity findById(String id);

    List<SimilarMetaEntity> findById(String id);
}
