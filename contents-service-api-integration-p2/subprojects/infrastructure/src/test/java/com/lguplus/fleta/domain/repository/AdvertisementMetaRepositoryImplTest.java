package com.lguplus.fleta.domain.repository;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import com.lguplus.fleta.provider.jpa.AdvertisementMetaJpaEmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AdvertisementMetaRepositoryImplTest {

    @Mock
    AdvertisementMetaJpaEmRepository advertisementMetaJpaEmRepository;

    @InjectMocks
    AdvertisementMetaRepositoryImpl advertisementMetaRepository;

    @Test
    void testGetAdvertisementLiveMeta() {

        doReturn(List.of()).when(advertisementMetaJpaEmRepository).getAdvertisementLiveMeta(any());

        final List<AdvertisementMetaDto> result = advertisementMetaRepository.getAdvertisementLiveMeta(
                AdvertisementMetaRequestDto.builder().build());
        assertThat(result).isEmpty();
    }
}
