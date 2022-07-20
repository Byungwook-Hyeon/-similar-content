package com.lguplus.fleta.domain.repository;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import com.lguplus.fleta.provider.jpa.AdvertisementMetaJpaEmRepository;
import com.lguplus.fleta.repository.advertisement.AdvertisementMetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AdvertisementMetaRepositoryImpl implements AdvertisementMetaRepository {

    private final AdvertisementMetaJpaEmRepository advertisementMetaJpaEmRepository;

    @Override
    public List<AdvertisementMetaDto> getAdvertisementLiveMeta(AdvertisementMetaRequestDto request) {

        return advertisementMetaJpaEmRepository.getAdvertisementLiveMeta(request);
    }
}
