package com.lguplus.fleta.service.advertisement;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import com.lguplus.fleta.data.type.AdvertisementMetaIptvBannerType;
import com.lguplus.fleta.data.type.AdvertisementMetaIdType;
import com.lguplus.fleta.data.type.AdvertisementMetaType;
import com.lguplus.fleta.repository.advertisement.AdvertisementMetaRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertisementMetaDomainService {

    private final AdvertisementMetaRepository advertisementMetaRepository;

    public List<AdvertisementMetaDto> getAdvertisementLiveMeta(final AdvertisementMetaRequestDto request) {

        return advertisementMetaRepository.getAdvertisementLiveMeta(request).stream()
                .filter(e -> request.isBanner() ? isValidBannerAdvertisementId(e.getAdsId(), e.getAdsType()) :
                        isValidAdvertisementId(e.getAdsId(), e.getAdsType()))
                .map(this::updateInformation)
                .collect(Collectors.toList());
    }

    private boolean isValidAdvertisementId(final String adsId, final String adsType) {

        return AdvertisementMetaIdType.IPTV_CATEGORY.contains(adsId) ||
                AdvertisementMetaIdType.IPTV_APP_LINK.contains(adsId) ||
                AdvertisementMetaIdType.MOBILE_CATEGORY.contains(adsId);
    }

    private boolean isValidBannerAdvertisementId(final String adsId, final String adsType) {

        return AdvertisementMetaIdType.IPTV_BANNER.contains(adsId) &&
                AdvertisementMetaIptvBannerType.ALL.contains(adsType);
    }

    private AdvertisementMetaDto updateInformation(final AdvertisementMetaDto advertisementMeta) {

        final String adsId = advertisementMeta.getAdsId();
        if (AdvertisementMetaIdType.IPTV_BANNER.contains(adsId)) {
            return updateIptvBannerInformation(advertisementMeta);
        } else if (AdvertisementMetaIdType.IPTV_CATEGORY.contains(adsId)) {
            return updateIptvCategoryInformation(advertisementMeta);
        } else if (AdvertisementMetaIdType.IPTV_APP_LINK.contains(adsId)){
            return updateIptvAppLinkInformation(advertisementMeta);
        } else if (AdvertisementMetaIdType.MOBILE_CATEGORY.contains(adsId)) {
            return updateMobileCategoryInformation(advertisementMeta);
        } else {
            return advertisementMeta;
        }
    }

    private AdvertisementMetaDto updateIptvBannerInformation(final AdvertisementMetaDto advertisementMeta) {

        final String adsType = advertisementMeta.getAdsType();
        if (AdvertisementMetaIptvBannerType.VOD.contains(adsType)) {
            return updateIptvBannerVodInformation(advertisementMeta);
        } else if (AdvertisementMetaIptvBannerType.CATEGORY.contains(adsType)) {
            return updateIptvBannerCategoryInformation(advertisementMeta);
        } else if (AdvertisementMetaIptvBannerType.APP_LINK.contains(adsType)){
            return updateIptvBannerAppLinkInformation(advertisementMeta);
        } else {
            return advertisementMeta;
        }
    }

    private AdvertisementMetaDto updateIptvBannerVodInformation(final AdvertisementMetaDto advertisementMeta) {

        final String[] values = advertisementMeta.getLinkUrlValues();
        advertisementMeta.setType(AdvertisementMetaType.VOD.toString());
        advertisementMeta.setContent(ArrayUtils.get(values, 1));
        advertisementMeta.setCategory(ArrayUtils.get(values, 0));
        advertisementMeta.setSeries(ArrayUtils.get(values, 3));
        return advertisementMeta;
    }

    private AdvertisementMetaDto updateIptvBannerCategoryInformation(final AdvertisementMetaDto advertisementMeta) {

        final String[] values = advertisementMeta.getLinkUrlValues();
        advertisementMeta.setType(AdvertisementMetaType.CATEGORY.toString());
        advertisementMeta.setContent(ArrayUtils.get(values, 0));
        advertisementMeta.setCategory(ArrayUtils.get(values, 0));
        return advertisementMeta;
    }

    private AdvertisementMetaDto updateIptvBannerAppLinkInformation(final AdvertisementMetaDto advertisementMeta) {

        final String[] values = advertisementMeta.getLinkUrlValues();
        advertisementMeta.setType(AdvertisementMetaType.APP_LINK.toString());
        advertisementMeta.setContent(ArrayUtils.get(values, 0));
        advertisementMeta.setCategory(ArrayUtils.get(values, 0));
        return advertisementMeta;
    }

    private AdvertisementMetaDto updateIptvCategoryInformation(final AdvertisementMetaDto advertisementMeta) {

        advertisementMeta.setType(AdvertisementMetaType.CATEGORY.toString());
        advertisementMeta.setContent(ArrayUtils.get(advertisementMeta.getLinkUrlValues(), 0));
        return advertisementMeta;
    }

    private AdvertisementMetaDto updateIptvAppLinkInformation(final AdvertisementMetaDto advertisementMeta) {

        advertisementMeta.setType(AdvertisementMetaType.APP_LINK.toString());
        advertisementMeta.setContent(StringUtils.defaultString(advertisementMeta.getLinkUrl())
                .replace("||I30", ""));
        return advertisementMeta;
    }

    private AdvertisementMetaDto updateMobileCategoryInformation(AdvertisementMetaDto advertisementMeta) {

        advertisementMeta.setType(AdvertisementMetaType.CATEGORY.toString());
        advertisementMeta.setContent(advertisementMeta.getLinkUrl());
        return advertisementMeta;
    }
}
