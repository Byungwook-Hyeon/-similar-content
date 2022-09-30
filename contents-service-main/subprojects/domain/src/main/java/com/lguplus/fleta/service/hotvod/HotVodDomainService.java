package com.lguplus.fleta.service.hotvod;

import com.lguplus.fleta.client.SubscriberDomainClient;
import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.*;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.entity.PtUxHvCategoryEntityDto;
import com.lguplus.fleta.data.type.CacheName;
import com.lguplus.fleta.repository.hotvod.HotVodRepository;
import com.lguplus.fleta.util.DateUtils;
import com.lguplus.fleta.util.FileDataUtils;
import com.lguplus.fleta.util.StringBinaryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class HotVodDomainService {

    private final HotVodRepository hotVodRepository;
    private final VodLookupDomainClient vodLookupDomainClient;
    private final SubscriberDomainClient subscriberDomainClient;

    private static final String DEFAULT_HOTVOD_BADGE_LIST = "신규|인기|연령1|연령2|연령3|추천1|추천2|추천3|기타1|기타2|기타3";
    private static final Integer DEFAULT_BADGE_LEN = 11;

    private static final Integer TOTAL_NUMBER = 0;
    private static final Integer TOTAL_RECORD = -1;

    @Value("${hotvod.filteringsite.dir}")
    private String filteringsiteDir;

    @Cacheable(cacheNames=CacheName.HOTVOD_HIT_COUNT, key="#contentId")
    public Integer getHotVodHitCount(String contentId) {
        log.debug("getHotVodHitCount() - {}:contentId={}", "화제 동영상 조회 수(Cacheable) Key", contentId);
         
        return 0;
    }

    @CachePut(cacheNames=CacheName.HOTVOD_HIT_COUNT, key="#contentId")
    public Integer setHotVodHitCount(String contentId, int count) {
        log.debug("addHotVodHitCount() - {}:contentId={}", "화제 동영상 조회 수 Cache에 저장 Key", contentId);

        return count;
    }


    /**
     * chaild에 해당하는 Data를 가져와서 parent data를 조회하여 계층 결과를 만족하는 Data만 조회한다.
     *      - NewHotVods 조회한다.
     *      - key 값만 가져와서 categoryList를 조회한다. ( conet by categoryId = parentcate )
     *      - Type M인 경우 IMCS와 연계하여 inner Join을 만족하는 것만 Filter한다.
     *      - categoryList별 newHotVods를 Mapping하여 AS_IS와 같은 결과가 나오도로 한다.
     *
     * key : HOTVOD_CACHE("HotvodDao.newGetHotvod.cacheKey"),
     * Cacheable(cacheNames="HOTVOD_CACHE", key="'newGetHotvod.' + #categoryId")
     *  - ParentCate의 원형을 유지하기 위하여 categoryId를 추가함
     */
    @Caching(cacheable = {
            @Cacheable(cacheNames=CacheName.HOTVOD_NEW_GET_LIST, key="'DEFAULT'", condition = "#request.parentCate == ''", unless="#result.size <= 0"),
            @Cacheable(cacheNames=CacheName.HOTVOD_NEW_GET, key="#request.parentCate", condition = "#request.parentCate != null and #request.parentCate != ''", unless="#result.size <= 0")
    })
    public List<HotVodRecordDto> getHotVodLoadData(HotVodListRequestDto request) {

        List<NewHotVodInfoDto> hotVods =  hotVodRepository.getNewHotVods(request);

        Map<String, List<NewHotVodInfoDto>> newHotVodParentCateMap = hotVods.stream()
                .collect(Collectors.groupingBy(NewHotVodInfoDto::getParentCate));

        //key 값만 가져와서 categoryList를 조회한다. ( conet by categoryId = parentcate에 대한 계층쿼리이기 떄문에)
        List<String> keyList = new ArrayList<>(newHotVodParentCateMap.keySet());
        List<PtUxHvCategoryEntityDto> hvCategorys =  hotVodRepository.getHvCategoryByCategoryIds(keyList);

        //categoryId에 대한 Name을 가져와야 한다.
        Map<String, String> hvCategorysMap = hvCategorys.stream()
                .collect(Collectors.toMap(PtUxHvCategoryEntityDto::getHvCategoryId
                        , PtUxHvCategoryEntityDto::getCategoryName));

        //M Type 에 대하여 Imcs로부터 Contents 정보를 가져와야 한다.
        List<CategoryAlbumsDto> imcsAllList = this.getHotVodContentsFromImcs(hotVods);

        List<HotVodRecordDto> hotVodRecords = new ArrayList<>();
        AtomicInteger rowNum = new AtomicInteger();
        hvCategorys
                .forEach(
                        dto -> {
                            HotVodRecordDto hotVodCategory = dto.convert();
                            hotVodCategory.setParentCateName(
                                    hvCategorysMap.get(dto.getHvCategoryId())
                            );
                            hotVodCategory.setRowNum(rowNum.getAndIncrement());
                            hotVodRecords.add(hotVodCategory);

                            List<NewHotVodInfoDto> newHotVods = newHotVodParentCateMap.get(dto.getHvCategoryId());
                            newHotVods
                                    .forEach( hotVod -> {
                                        HotVodRecordDto hvRecord = this.getHotVodJoinImcs(request, hotVod, imcsAllList);  //Mtype만 대상이다
                                        if (hvRecord != null) {
                                            hvRecord.setRowNum(rowNum.getAndIncrement());
                                            hotVodRecords.add(hvRecord);
                                        }
                                    });
                        }
                );
        return hotVodRecords;

    }

    
    /**
     * M Type 에 대해 IMCS 와 연계 후 Inner join 한 결과
     *      - inner join 으로 연계 후 결과에 해당하는 건만 filter 되어야 한다.
     *      - imcs 결과가 있는 경우 일부 항목 대체
     */
    private HotVodRecordDto getHotVodJoinImcs(HotVodListRequestDto request, NewHotVodInfoDto newHotVod, List<CategoryAlbumsDto> imcsAllList) {

        HotVodRecordDto hvRecord = newHotVod.convert();
        // 뱃지데이터 파싱
        String badgeData = StringBinaryUtils.toBinaryString(newHotVod.getBadgeData(), DEFAULT_HOTVOD_BADGE_LIST, DEFAULT_BADGE_LEN);
        hvRecord.setBadgeData(badgeData);

        if (StringUtils.isNotEmpty(newHotVod.getCategoryImg())) {
            hvRecord.setCategoryImg(request.getImageServerUrl().concat(newHotVod.getCategoryImg()));
        }
        if (StringUtils.isNotEmpty(newHotVod.getCategoryImgTv())) {
            hvRecord.setCategoryImgTv(request.getImageServerUrl().concat(newHotVod.getCategoryImgTv()));
        }
        if (StringUtils.isNotEmpty(newHotVod.getSiteLogoUrl())) {
            hvRecord.setSiteLogoUrl(request.getImageServerUrl().concat(newHotVod.getSiteLogoUrl()));
        }
        if (StringUtils.isNotEmpty(newHotVod.getSiteLogoUrlTv())) {
            hvRecord.setSiteLogoUrlTv(request.getImageServerUrl().concat(newHotVod.getSiteLogoUrlTv()));
        }
        if (StringUtils.isNotEmpty(newHotVod.getImgUrl())) {
            hvRecord.setImgUrl(request.getImageServerUrl().concat(newHotVod.getImgUrl()));
        }
        if (StringUtils.isNotEmpty(newHotVod.getImgUrlTv())) {
            hvRecord.setImgUrlTv(request.getImageServerUrl().concat(newHotVod.getImgUrlTv()));
        }

        if(StringUtils.equals(newHotVod.getType(), "M")) {
            return imcsAllList.stream()
                    .filter(x-> StringUtils.equals(hvRecord.getVodCategoryId(), x.getCategoryId())
                            && StringUtils.equals(hvRecord.getVodAlbumId(), x.getContentsId())
                    ).findFirst()
                    .map(
                            ca -> {
                                hvRecord.setContentsName(ca.getContentsName());
                                hvRecord.setSeriesNo(ca.getSeriesNo());
                                hvRecord.setSeriesYn(ca.getSeriesYn());
                                hvRecord.setSeriesDesc(ca.getSeriesDesc());

                                return hvRecord;
                            }
                    ).orElse(null);
        }
        return hvRecord;
    }


    /**
     * M Type에 대하여 IMCS에 연계하여 Inner Join 대상 추출
     */
    private List<CategoryAlbumsDto> getHotVodContentsFromImcs(List<NewHotVodInfoDto> hotVods) {

        List<NewHotVodInfoDto> hotVodCategoryAlbums = hotVods.stream()
                .filter(x -> StringUtils.equals(x.getType(), "M")
                        && StringUtils.isNotBlank(x.getVodCategoryId())
                        && StringUtils.isNotBlank(x.getVodAlbumId()))
                .collect(Collectors.toList());

        return vodLookupDomainClient.getCategoryAlbums(hotVodCategoryAlbums);
    }


    /**
     * cache data를 가져와서 paramer filter
     * hitmap get/set cachable에서 가져온다.
     */
    public List<HotVodRecordDto> getHotVodSearchList(HotVodListRequestDto request, List<HotVodRecordDto> hotVodRecords) {
        List<HotVodRecordDto> newHotVodRecords;

        Set<String> types = Arrays.stream(request.getType().split("\\|"))
                .collect(Collectors.toSet());

        newHotVodRecords = hotVodRecords.stream()
                .filter(e -> this.isHotVodTypeCheck(e, types)
                        && this.isHotVodMasterContentInclude(e, request)
                        && this.isHotVodFilterCheck(e, request)
                        && this.isHotVodSiteCheck(e, request)
                )
                .collect(Collectors.toList());

        return newHotVodRecords;
    }


    /**
     * filter 대상
     *  - types == "" 인 경우 전체
     *  - types != "" 인 경우 해당 Set<String>
     */
    private boolean isHotVodTypeCheck(HotVodRecordDto recordVo, Set<String> types) {
        if(types.contains("")) {
            return true;
        } else {
            return types.contains(recordVo.getType());
        }
    }


    /**
     * filter 대상
     *  "Y".equals(master_content_include) && recordVo.getType().equals("C")
     *  인 경우 recordVo.getCategory_id() = parent_id 대상에 대해  "C|V|M|N|L" 를 추가한다
     *      - 이미 전체("C|V|M|N|L")를 가져오게 만들었다.
     *      - master_content_include == Y
     *          - ParentCate == null 이라면, 전체 리스트를 가져옴 (AS_IS)
     *          - ParentCate != null 이라면, Ctype만 제외하고 가져온다. (AS_IS)
     *      - master_content_include != Y
     *          - parentCate == null 이라면, 인 경우 경우 C Type만 가져온다. (AS_IS)
     *          - ParentCate != null 이라면, C type만 제외하고 가져온다. (AS_IS)
     */
    private boolean isHotVodMasterContentInclude(HotVodRecordDto recordVo, HotVodListRequestDto request) {
        boolean isHotVod;
        log.debug("isHotvodValidCheck() {} - {}",request.getParentCate(), recordVo.getType());

        if(StringUtils.equals(request.getMasterContentInclude(),"Y")) {
            isHotVod = isMasterInclude(recordVo, request);
        } else {
            isHotVod = isMasteNonInclude(recordVo, request);
        }
        return isHotVod;
    }

    private boolean isMasteNonInclude(HotVodRecordDto recordVo, HotVodListRequestDto request) {
        boolean isHotVod;
        if(StringUtils.isBlank(request.getParentCate())) {  // Ctype만 가져온다.
            isHotVod = StringUtils.equals(recordVo.getType(), "C");
        } else {    // Ctype만 제외하고 가져온다.
            isHotVod = !StringUtils.equals(recordVo.getType(), "C");
        }
        return isHotVod;
    }

    private boolean isMasterInclude(HotVodRecordDto recordVo, HotVodListRequestDto request) {
        boolean isHotVod;
        if(StringUtils.isBlank(request.getParentCate())) {  // 전체 리스트를 가져옴
            isHotVod = true;
        } else {    //parant 정보중 Ctype은 안 가져온다 (AS_IS)
            isHotVod = !StringUtils.equals(recordVo.getType(), "C");
        }
        return isHotVod;
    }


    /**
     * site fiter
     * - site Id 가 있는 경우 같은 대상만 나와야 됨
     * - site Id 가 null 인 경우 전체를 가져옴
     */
    private boolean isHotVodSiteCheck(HotVodRecordDto recordVo, HotVodListRequestDto request) {
        boolean isHotVod;
        log.debug("isHotVodSiteCheck() {} - {}",request.getSiteId(), recordVo.getSiteId());
        if(StringUtils.isNotEmpty(request.getSiteId())) {
            isHotVod = StringUtils.equals(recordVo.getSiteId(), request.getSiteId());
        } else {
            isHotVod = true;
        }

        return isHotVod;
    }

    /**
     * filter 대상
     *  - 영상은 filtering site 만 나와야 됨
     */
    private boolean isHotVodFilterCheck(HotVodRecordDto recordVo, HotVodListRequestDto request) {
        boolean isHotVod;
        List<String> filteringSite;

        //영상은 filtering site 만 나와야 됨
        if(StringUtils.equalsAny(recordVo.getType(),"V", "L")) {    //영상
            filteringSite = request.getFilteringSite();
            if(!CollectionUtils.isEmpty(filteringSite)) {
                isHotVod = filteringSite.stream()
                        .anyMatch(x -> recordVo.getContentUrl().startsWith(x));
            } else {
                isHotVod = false;
            }
        } else {
            isHotVod = true;
        }

        return isHotVod;
    }


    /**
     * 노출 기간 체크
     *  - 노출기간이  없는 컨텐츠의 경우는 skip
     *  - 노출기간이 있는 컨텐츠의 경우에는 기간체크(기간에 포함이 안될 경우 목록에서 제거)
     */
    public List<HotVodRecordDto> getHotVodOrderList(List<HotVodRecordDto> newHotVodRecords) {

        return newHotVodRecords.stream()
                .filter(x-> DateUtils.dateCompare(x.getStartDt(), x.getEndDt()))
                .collect(Collectors.toList());
    }


    /**
     * Ordering
     *  - "H" hitCompare : 조회수 정렬 (HitCnt.reversed)
     *  - "N" dateCompare : 등록일 정렬 (regDate.reversed)
     *  - "S" siteCompare : 사이트 정렬 (siteName)
     *  - "O" orderCompare : order기준 정렬(rowNum)
     *  - else orderCompare : order기준 정렬(rowNum)
     */
    public List<HotVodRecordDto> getHotVodRecordComparator(HotVodListRequestDto request, List<HotVodRecordDto> newHotVodRecords) {

        Comparator<HotVodRecordDto> compare;
        if(StringUtils.equals("H", request.getOrder())) {
            compare = Comparator.comparing(
                    HotVodRecordDto::getHitCnt).reversed(); //조회수 정렬 (HitCnt.reversed)

        } else if(StringUtils.equals("N", request.getOrder())) {
            compare = Comparator.comparing(
                    HotVodRecordDto::getRegDate).reversed();    //등록일 정렬 (regDate.reversed)

        } else if(StringUtils.equals("S", request.getOrder())) {
            compare = Comparator.comparing(
                    HotVodRecordDto::getSiteName);    //사이트 정렬 (siteName)

        } else if(StringUtils.equals("O", request.getOrder())) {
            compare = Comparator.comparing(
                    HotVodRecordDto::getRowNum);    //order기준 정렬(rowNum)

        } else {
            compare = Comparator.comparing(
                    HotVodRecordDto::getRowNum);
        }

        return newHotVodRecords.stream()
                .sorted(compare)
                .collect(Collectors.toList());
    }


    /**
     * 레코스 수를 제한한다
     */
    public List<HotVodRecordDto> getHotVodsLimt(HotVodListRequestDto request, List<HotVodRecordDto> newHotVodRecords) {
        List<HotVodRecordDto> hotVodsLimtRecord = new ArrayList<>();

        if (!Objects.equals(TOTAL_NUMBER, request.getStartNumber())) {
            int convertRequestCount;
            int startSkipNumber;
            if (Objects.equals(TOTAL_RECORD, request.getStartNumber())) {  //게시판의 전체 레코드를 구한다.
                convertRequestCount = Integer.MAX_VALUE;
                startSkipNumber = 0;
            } else {
                convertRequestCount = request.getRequestCount();
                startSkipNumber = request.getStartNumber()-1;
            }

            hotVodsLimtRecord = newHotVodRecords.stream()
                    .skip(startSkipNumber)
                    .limit(convertRequestCount)
                    .collect(Collectors.toList());
        }

        return hotVodsLimtRecord;
    }


    /**
     * VodLookup 도메인에 albumId 리스트로 trailer 정보 조회(Inner API 호출)
     * /vodlookup/album/albumInfo 사용하면 해당하는 컬럼 다 나옴
     * key : HOTVOD_META_CACHE("HotvodDao.metaTitleAsset.cacheKey"),
     * Cacheable(cacheNames="HOTVOD_CACHE", key="'metaTitleAsset' + #albumId")
     */
    @Cacheable(cacheNames=CacheName.HOTVOD_META_TITLE_ASSET, key="#albumId", condition = "#albumId != null and #albumId != ''", unless="#result.size <= 0")
    public List<AlbumTrailerDto> getMetadataList(String albumId) {
        //album id들만 추출해서
        List<String> albumIds = new ArrayList<>();
        albumIds.add(albumId);
        return new ArrayList<>(vodLookupDomainClient.getTrailerInfo(albumIds));
    }

    @CachePut(cacheNames=CacheName.HOTVOD_META_TITLE_ASSET, key="#albumId", condition = "#albumId != null and #albumId != ''", unless="#result.size <= 0")
    public List<AlbumTrailerDto> putMetadataList(String albumId) {
        return this.getMetadataList(albumId);
    }


    /**
     */
    public List<HotVodRecordDto> setMetadataList(List<HotVodRecordDto> newHotVodRecords, List<AlbumTrailerDto> albumTrailers) {
        List<HotVodRecordDto> rtnHotVodRecords;
        Map<String, List<AlbumTrailerDto>> albumTrailerMap = albumTrailers.stream()
                .collect(Collectors.groupingBy(AlbumTrailerDto::getAlbumId,
                        Collectors.mapping((AlbumTrailerDto bn) -> bn, Collectors.toList())));

        rtnHotVodRecords = newHotVodRecords.stream()
                .map(
                        newHotVod -> {
                            AlbumTrailerDto albumTitle = null;
                            List<AlbumTrailerDto> albumTitles = albumTrailerMap.get(newHotVod.getVodAlbumId());
                            if(!CollectionUtils.isEmpty(albumTitles)) {
                                albumTitle = albumTitles.stream()
                                        .findFirst().orElse(null);
                            }
                            if(albumTitle != null) {
                                newHotVod.setStillImg(albumTitle.getStillImageFile());
                                newHotVod.setOnairDate(albumTitle.getOnairDate());
                                newHotVod.setGenreLarge(albumTitle.getGenreLarge());
                                newHotVod.setGenreMid(albumTitle.getGenreMid());
                                newHotVod.setRating(albumTitle.getRating());
                                newHotVod.setSponsorName(albumTitle.getGenreSmall());
                                if(StringUtils.equalsAny(newHotVod.getType(), "M", "B", "A")) {
                                    newHotVod.setDuration(albumTitle.getRunTime());
                                }
                            }
                            return newHotVod;
                        }
                )
                .collect(Collectors.toList());

        return rtnHotVodRecords;
    }


    @Caching(put = {
            @CachePut(cacheNames=CacheName.HOTVOD_NEW_GET_LIST, key="'DEFAULT'", condition = "#request.parentCate == ''", unless="#result.size <= 0"),
            @CachePut(cacheNames=CacheName.HOTVOD_NEW_GET, key="#request.parentCate", condition = "#request.parentCate != null and #request.parentCate != ''", unless="#result.size <= 0")
    })
    public List<HotVodRecordDto> putHotVodLoadData(HotVodListRequestDto request) {
        return this.getHotVodLoadData(request);
    }


    /**
     * 화제동영상 목록조회 검수단말기 필터링
     *  - pSbcCode : StbMac
     */
    public List<HotVodRecordDto> getFilteredHotvodList(List<HotVodRecordDto> hotVodRecordList, boolean testSubscriber) {
        String testCondition;
        if(testSubscriber) {
            testCondition = "Y";
        }
        else {
            testCondition = "S";
        }

        String filterCondition = "N" + testCondition;

        return hotVodRecordList.stream().filter(hotVodRecord -> filterCondition.contains(StringUtils.defaultIfEmpty(hotVodRecord.getTestYn(),"N"))).collect(Collectors.toList());

    }


    @Cacheable(cacheNames=CacheName.FILTERING_SITE, key="'DEFAULT'")
    public Map<String, List<String>> getFilteringSiteList() {

        Map<String, List<String>> filterSiteList = new HashMap<>();
        List<File> rtnFile = FileDataUtils.findFile(".fts", "endsWith", filteringsiteDir);
        rtnFile.forEach(
                file -> {
                    String filteringId = file.getName().substring(0,file.getName().lastIndexOf("."));
                    List<String> brLines = FileDataUtils.getFileLineData(file);
                    List<String> filterSites = brLines.stream()
                            .filter(line -> line.split("\\|").length >= 3)
                            .map(line->line.split("\\|")[1])
                            .collect(Collectors.toList());
                    filterSiteList.put(filteringId, filterSites);
                    log.debug("getSiteList() - {}: {}", filteringId, filterSites);
                });

        return filterSiteList;

    }

    @CachePut(cacheNames=CacheName.FILTERING_SITE, key="'DEFAULT'")
    public Map<String, List<String>> loadFilteringSiteList() {
        return getFilteringSiteList();
    }

}
