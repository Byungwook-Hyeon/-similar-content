package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.dto.request.AdvertisementMetaRequestDto;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class AdvertisementMetaVo {

    @NotEmpty
    private List<@NotNull Integer> adsNo;
    private String screenType;

    public AdvertisementMetaRequestDto convert() {

        return AdvertisementMetaRequestDto.builder()
                .banner(false)
                .adsNo(adsNo)
                .screenType(screenType)
                .build();
    }
}
