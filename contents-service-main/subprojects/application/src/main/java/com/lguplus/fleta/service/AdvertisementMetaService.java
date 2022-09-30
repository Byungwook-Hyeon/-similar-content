package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.advertisement.AdvertisementMetaDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AdvertisementMetaService {

    private final AdvertisementMetaDomainService advertisementMetaDomainService;
    private final ImageServerDomainService imageServerDomainService;

    public List<AdvertisementMetaDto> getAdvertisementLiveMeta(final AdvertisementMetaRequestDto request) {

        final String imageServerUrl = imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE) +
                ("N".equals(request.getScreenType()) ? "hdtv/img/" : "ads/");
        return advertisementMetaDomainService.getAdvertisementLiveMeta(request).stream()
                .map(e -> updateImageServerUrl(e, imageServerUrl))
                .collect(Collectors.toList());
    }

    private AdvertisementMetaDto updateImageServerUrl(final AdvertisementMetaDto advertisementMeta,
                                                      final String imageServerUrl) {

        advertisementMeta.setImageServerUrl(imageServerUrl);
        return advertisementMeta;
    }
}
