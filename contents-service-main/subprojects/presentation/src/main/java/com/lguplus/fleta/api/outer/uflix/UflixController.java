package com.lguplus.fleta.api.outer.uflix;


import com.lguplus.fleta.data.dto.BannerMstListDto;
import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.dto.response.GenericBannerMstListResponseDto;
import com.lguplus.fleta.data.mapper.BannerGroupRequestMapper;
import com.lguplus.fleta.data.vo.BannerMstListVo;
import com.lguplus.fleta.service.BannerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UflixController {

    private final BannerGroupRequestMapper bannerGroupRequestMapper;
    private final BannerGroupService bannerGroupService;

    @GetMapping("/uflix/banner/group/lists")
    public GenericBannerMstListResponseDto<BannerMstListDto> getBannerGroupLlist(BannerMstListVo bannerMstListVo) {
        BannerMstRequestDto bannerMstRequestDto = bannerGroupRequestMapper.toDto(bannerMstListVo);

        return bannerGroupService.getBannerGroupList(bannerMstRequestDto);
    }
}
