package com.lguplus.fleta.domain.repository.jpa;


import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.entity.BannerMstEntity;
import com.lguplus.fleta.provider.jpa.BannerGroupJpaRepository;
import com.lguplus.fleta.repository.uflix.BannerGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BannerGroupRepositoryImpl implements BannerGroupRepository {

    private final BannerGroupJpaRepository bannerGroupJpaRepository;

    @Override
    public List<BannerMstEntity> getBannerGroupList(BannerMstRequestDto bannerMstRequestDto) {
        return bannerGroupJpaRepository.findAll();
    }
}
