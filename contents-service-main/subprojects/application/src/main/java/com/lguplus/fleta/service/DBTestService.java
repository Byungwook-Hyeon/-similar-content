package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.service.dbTest.DBTestDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DBTestService {

    private final DBTestDomainService dbTestDomainService;

    public SimilarContentDto getDBTest(String albumId) {

        return dbTestDomainService.getDBTest(albumId);
    }
}
