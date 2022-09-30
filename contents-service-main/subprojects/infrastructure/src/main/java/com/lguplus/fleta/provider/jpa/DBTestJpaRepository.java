package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.DBTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBTestJpaRepository extends JpaRepository<DBTestEntity, String> {
    DBTestEntity findByIds(String albumId);

}
