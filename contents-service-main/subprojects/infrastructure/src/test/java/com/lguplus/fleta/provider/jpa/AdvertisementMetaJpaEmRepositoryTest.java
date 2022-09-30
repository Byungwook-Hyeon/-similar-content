package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AdvertisementMetaJpaEmRepositoryTest {

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    @Mock
    NativeQuery nativeQuery;

    @Mock
    org.hibernate.query.Query hibernateQuery;

    @InjectMocks
    AdvertisementMetaJpaEmRepository advertisementMetaJpaEmRepository;

    @Test
    void testGetIptvAdvertisementLiveMetaWithAdsId() {

        doReturn(query).when(entityManager).createNativeQuery(any());
        doReturn(query).when(query).setParameter(anyString(), any());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        doReturn(List.of()).when(hibernateQuery).getResultList();

        final List<AdvertisementMetaDto> result = advertisementMetaJpaEmRepository.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder()
                        .adsId(List.of("SEAM1"))
                        .build());
        assertThat(result).isEmpty();
    }

    @Test
    void testGetIptvAdvertisementLiveMetaWithAdsNo() {

        doReturn(query).when(entityManager).createNativeQuery(any());
        doReturn(query).when(query).setParameter(anyString(), any());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        doReturn(List.of()).when(hibernateQuery).getResultList();

        final List<AdvertisementMetaDto> result = advertisementMetaJpaEmRepository.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder()
                        .adsNo(List.of(9373))
                        .build());
        assertThat(result).isEmpty();
    }

    @Test
    void testGetMobileAdvertisementLiveMetaWithAdsId() {

        doReturn(query).when(entityManager).createNativeQuery(any());
        doReturn(query).when(query).setParameter(anyString(), any());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        doReturn(List.of()).when(hibernateQuery).getResultList();

        final List<AdvertisementMetaDto> result = advertisementMetaJpaEmRepository.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder()
                        .adsId(List.of("SEAM1"))
                        .screenType("N")
                        .build());
        assertThat(result).isEmpty();
    }

    @Test
    void testGetMobileAdvertisementLiveMetaWithAdsNo() {

        doReturn(query).when(entityManager).createNativeQuery(any());
        doReturn(query).when(query).setParameter(anyString(), any());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        doReturn(List.of()).when(hibernateQuery).getResultList();

        final List<AdvertisementMetaDto> result = advertisementMetaJpaEmRepository.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder()
                        .adsNo(List.of(9373))
                        .screenType("N")
                        .build());
        assertThat(result).isEmpty();
    }
}
