package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class AdvertisementMetaBannerVo {

    @NotEmpty
    private List<@NotEmpty String> adsId;
    private String screenType;

    public AdvertisementMetaRequestDto convert() {

        return AdvertisementMetaRequestDto.builder()
                .banner(true)
                .adsId(adsId)
                .screenType(screenType)
                .build();
    }
}
