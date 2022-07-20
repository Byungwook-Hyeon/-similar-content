package com.lguplus.fleta.domain.repository;

import com.lguplus.fleta.provider.jpa.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
class CtcLogRepositoryImplTest {

    @Mock
    private UxHotvodCategoryJpaRepository uxHotvodCategoryJpaRepository;

    @Mock
    private UxHotvodCategoryContentJpaRepository uxHotvodCategoryContentJpaRepository;

    @Mock
    private UxHotvodContentJpaRepository uxHotvodContentJpaRepository;

    @Mock
    private UxHotvodContentExtensionJpaRepository uxHotvodContentExtensionJpaRepository;

    @Mock
    private UxHotvodHitcountLogJpaEmRepository uxHotvodHitcountLogJpaEmRepository;

    @Mock
    private UxHotvodSiteJpaRepository uxHotvodSiteJpaRepository;

    @Mock
    private HdtvAdvertisementInfoJpaRepository hdtvAdvertisementInfoJpaRepository;

    @Mock
    private HdtvAdvertisementInfoLogJpaEmRepository hdtvAdvertisementInfoLogJpaEmRepository;

    @Mock
    private HdtvAdvertisementMasterJpaRepository hdtvAdvertisementMasterJpaRepository;

    @Mock
    private HdtvAdvertisementMasterLogJpaEmRepository hdtvAdvertisementMasterLogJpaEmRepository;

    @InjectMocks
    private CtcLogRepositoryImpl ctcLogRepository;

    @Test
    void test_All() {

        assertDoesNotThrow(ctcLogRepository::findAllUxHotvodCategory);
        assertDoesNotThrow(ctcLogRepository::findAllUxHotvodCategoryContent);
        assertDoesNotThrow(ctcLogRepository::findAllUxHotvodContent);
        assertDoesNotThrow(ctcLogRepository::findAllUxHotvodContentExtension);
        assertDoesNotThrow(ctcLogRepository::streamAllUxHotvodHitcountLog);
        assertDoesNotThrow(ctcLogRepository::findAllUxHotvodSite);
        assertDoesNotThrow(ctcLogRepository::findAllHdtvAdvertisementInfo);
        assertDoesNotThrow(ctcLogRepository::streamAllHdtvAdvertisementInfoLog);
        assertDoesNotThrow(ctcLogRepository::findAllHdtvAdvertisementMaster);
        assertDoesNotThrow(ctcLogRepository::streamAllHdtvAdvertisementMasterLog);
    }
}
