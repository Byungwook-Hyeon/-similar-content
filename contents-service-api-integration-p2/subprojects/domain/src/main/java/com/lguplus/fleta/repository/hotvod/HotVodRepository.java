package com.lguplus.fleta.repository.hotvod;

import com.lguplus.fleta.data.dto.NewHotVodInfoDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.entity.PtUxHvCategoryEntityDto;

import java.util.List;

public interface HotVodRepository {

    List<PtUxHvCategoryEntityDto> getHvCategoryByCategoryIds(List<String> categoryIds);

    List<NewHotVodInfoDto> getNewHotVods(HotVodListRequestDto param);
}
