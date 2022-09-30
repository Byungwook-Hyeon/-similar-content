package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.BannerMstListDto;
import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.entity.BannerMstEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = ObjectMapperConfig.class)
public interface BannerGroupMapper {

    List<BannerMstListDto> toDto(List<BannerMstEntity> bannerMstEntity);
}
