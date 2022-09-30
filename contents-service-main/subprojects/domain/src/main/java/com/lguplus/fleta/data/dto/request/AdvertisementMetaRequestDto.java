package com.lguplus.fleta.data.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AdvertisementMetaRequestDto {

    private boolean banner;
    private List<String> adsId;
    private List<Integer> adsNo;
    private String screenType;
}
