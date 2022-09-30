package com.lguplus.fleta.repository.dbTest;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.DBTestEntity;

public interface DBTestRepository {

    DBTestEntity getDBTest(String albumId);
}
