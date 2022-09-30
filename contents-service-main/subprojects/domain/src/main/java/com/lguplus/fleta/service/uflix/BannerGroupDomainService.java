package com.lguplus.fleta.service.uflix;


import com.lguplus.fleta.data.dto.BannerMstListDto;
import com.lguplus.fleta.data.dto.request.BannerMstRequestDto;
import com.lguplus.fleta.data.entity.BannerMstEntity;
import com.lguplus.fleta.data.mapper.BannerGroupMapper;
import com.lguplus.fleta.repository.uflix.BannerGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BannerGroupDomainService {

    private final BannerGroupRepository bannerGroupRepository;
    private final BannerGroupMapper bannerGroupMapper;

    public List<BannerMstListDto> getBannerGroupList(BannerMstRequestDto bannerMstRequestDto) {
        List<BannerMstListDto> resultList = new ArrayList<>();
        List<BannerMstEntity> bannerMstEntity = bannerGroupRepository.getBannerGroupList(bannerMstRequestDto);
        List<BannerMstListDto> result = bannerGroupMapper.toDto(bannerMstEntity);

        for (BannerMstListDto dd : result) {
            BannerMstListDto tmp = new BannerMstListDto();
            tmp.setGroup_id(dd.getGroup_id());
            tmp.setGroup_nm(dd.getGroup_nm());
            tmp.setGroup_gbn(dd.getGroup_gbn());

            resultList.add(tmp);
        }

        return resultList;
    }
}
