package com.lguplus.fleta.repository.advertisement;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;

import java.util.List;

public interface AdvertisementMetaRepository {

    List<AdvertisementMetaDto> getAdvertisementLiveMeta(AdvertisementMetaRequestDto request);
}
