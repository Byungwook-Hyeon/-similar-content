package com.lguplus.fleta.service;


import com.lguplus.fleta.data.dto.BannerMstListDto;
import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.dto.response.GenericBannerMstListResponseDto;
import com.lguplus.fleta.service.uflix.BannerGroupDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BannerGroupService {

    private final BannerGroupDomainService bannerGroupDomainService;

    public GenericBannerMstListResponseDto<BannerMstListDto> getBannerGroupList(BannerMstRequestDto bannerMstRequestDto) {
        List<BannerMstListDto> bannerMstListResultDto = bannerGroupDomainService.getBannerGroupList(bannerMstRequestDto);

        return GenericBannerMstListResponseDto.<BannerMstListDto>genericBannerMstListResponseBuilder()
                .recordset(bannerMstListResultDto)
                .build();
    }
}
