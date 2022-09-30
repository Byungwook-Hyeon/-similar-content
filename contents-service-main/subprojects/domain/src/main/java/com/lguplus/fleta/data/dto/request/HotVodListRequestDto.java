package com.lguplus.fleta.data.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class HotVodListRequestDto extends CommonPagingRequestDto {

    private String version;
    @Setter
    private String parentCate;
    private String type;
    private String order;
    private String siteId;
    private String filteringId;
    private String callByScheduler;
    private String masterContentInclude;
    @Setter
    private String imageServerUrl;
    @Setter
    private String categoryId;
    @Setter
    private List<String> filteringSite;
    @Setter
    private boolean hitmapChk;
    @Setter
    private boolean isBadge;

}
