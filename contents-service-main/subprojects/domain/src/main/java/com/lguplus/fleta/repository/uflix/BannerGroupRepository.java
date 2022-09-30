package com.lguplus.fleta.repository.uflix;

import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.entity.BannerMstEntity;

import java.util.List;

public interface BannerGroupRepository {

    List<BannerMstEntity> getBannerGroupList(BannerMstRequestDto bannerMstRequestDto);
}