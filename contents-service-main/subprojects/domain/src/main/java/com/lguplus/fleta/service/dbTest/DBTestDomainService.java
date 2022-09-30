package com.lguplus.fleta.service.dbTest;


import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.entity.DBTestEntity;
import com.lguplus.fleta.data.mapper.DBTestMapper;
import com.lguplus.fleta.data.mapper.SimilarContentMapper;
import com.lguplus.fleta.repository.dbTest.DBTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DBTestDomainService {

    private final DBTestRepository dbTestRepository;
    private final DBTestMapper dbTestMapper;

    public SimilarContentDto getDBTest(String albumId) {
        DBTestEntity dbTestEntity = dbTestRepository.getDBTest(albumId);
        System.out.println(dbTestEntity);
        SimilarContentDto result = dbTestMapper.toDto(dbTestEntity);
        System.out.println(result);

        return result;
    }
}
