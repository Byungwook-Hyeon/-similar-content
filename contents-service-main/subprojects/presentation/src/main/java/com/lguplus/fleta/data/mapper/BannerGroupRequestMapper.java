package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.vo.BannerMstListVo;
import org.mapstruct.Mapper;

@Mapper(config = ObjectMapperConfig.class)
public interface BannerGroupRequestMapper {
    BannerMstRequestDto toDto(BannerMstListVo bannerMstListVo);
}
