package com.lguplus.fleta.domain.repository.jpa;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.DBTestEntity;
import com.lguplus.fleta.provider.jpa.DBTestJpaRepository;
import com.lguplus.fleta.repository.dbTest.DBTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DBTestRepositoryImpl implements DBTestRepository {

    private final DBTestJpaRepository dbTestJpaRepository;

    @Override
    public DBTestEntity getDBTest(String albumId) {
        return dbTestJpaRepository.findByIds(albumId);
    }
}
