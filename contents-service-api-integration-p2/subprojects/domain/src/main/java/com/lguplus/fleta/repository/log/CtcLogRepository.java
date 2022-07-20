package com.lguplus.fleta.repository.log;

import com.lguplus.fleta.data.dto.HdtvAdvertisementInfoLogDto;
import com.lguplus.fleta.data.dto.HdtvAdvertisementMasterLogDto;
import com.lguplus.fleta.data.dto.UxHotvodHitcountLogDto;
import com.lguplus.fleta.data.entity.*;

import java.util.List;
import java.util.stream.Stream;

public interface CtcLogRepository {

    List<UxHotvodCategory> findAllUxHotvodCategory();
    List<UxHotvodCategoryContent> findAllUxHotvodCategoryContent();
    List<UxHotvodContent> findAllUxHotvodContent();
    List<UxHotvodContentExtension> findAllUxHotvodContentExtension();
    Stream<UxHotvodHitcountLogDto> streamAllUxHotvodHitcountLog();
    List<UxHotvodSite> findAllUxHotvodSite();
    List<HdtvAdvertisementInfo> findAllHdtvAdvertisementInfo();
    Stream<HdtvAdvertisementInfoLogDto> streamAllHdtvAdvertisementInfoLog();
    List<HdtvAdvertisementMaster> findAllHdtvAdvertisementMaster();
    Stream<HdtvAdvertisementMasterLogDto> streamAllHdtvAdvertisementMasterLog();
}
