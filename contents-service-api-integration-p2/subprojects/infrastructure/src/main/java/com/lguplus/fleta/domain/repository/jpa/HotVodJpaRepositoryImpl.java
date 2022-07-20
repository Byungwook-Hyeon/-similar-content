package com.lguplus.fleta.domain.repository.jpa;

import com.lguplus.fleta.data.dto.NewHotVodInfoDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.entity.PtUxHvCategoryEntityDto;
import com.lguplus.fleta.provider.jpa.HotVodCategoryJpaRepository;
import com.lguplus.fleta.provider.jpa.HotVodJpaRepository;
import com.lguplus.fleta.repository.hotvod.HotVodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HotVodJpaRepositoryImpl implements HotVodRepository {

    private final HotVodJpaRepository hotVodJpaRepository;
    private final HotVodCategoryJpaRepository hotVodCategoryJpaRepository;

    @Override
    public List<PtUxHvCategoryEntityDto> getHvCategoryByCategoryIds(List<String> categoryIds) {
        return hotVodCategoryJpaRepository.findHvCategoryByCategoryIds(categoryIds);
    }

    @Override
    public List<NewHotVodInfoDto> getNewHotVods(HotVodListRequestDto param) {
        return hotVodJpaRepository.getNewHotVods(param);
    }
}
