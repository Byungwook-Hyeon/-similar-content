package com.lguplus.fleta.service.hotvod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.client.SubscriberDomainClient;
import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.*;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.entity.PtUxHvCategoryEntityDto;
import com.lguplus.fleta.repository.hotvod.HotVodRepository;
import com.lguplus.fleta.util.FileDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
class HotVodDomainServiceTest {

    @Mock
    private HotVodRepository hotVodRepository;
    @Mock
    private VodLookupDomainClient vodLookupDomainClient;
    @Mock
    private SubscriberDomainClient subscriberDomainClient;

    @TempDir
    Path directory;
    @Mock
    File file1;

    @Spy
    @InjectMocks
    private HotVodDomainService hotVodDomainService;

    private List<NewHotVodInfoDto> hotVods;
    private List<CategoryAlbumsDto> imcsList;
    private List<PtUxHvCategoryEntityDto> hvCategorys;
    private List<HotVodRecordDto> hotVodRecords;
    private List<AlbumTrailerDto> albumTrailers;
    private List<SubscriberDto> testSubscribers;

    private static MockedStatic<FileDataUtils> mFileDataUtils;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(hotVodDomainService, "filteringsiteDir", "/NAS_DATA/web/smartux/iptv/filteringsite/");

        mFileDataUtils = mockStatic(FileDataUtils.class);

        //private List<NewHotVodInfoDto> hotVods;
        JSONParser jsonParser=new JSONParser();
        String jsonHotVodRecords = "[" +
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000256\",\"contentOrder\":\"1\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2020-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501228982.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 14:36:18.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uB7A8\\uD398\\uC774\\uC9C0 3: \\uBC31\\uC545\\uAD00 \\uC2EC\\uD310\\uC758 \\uB0A0\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M0117AH665PPV00\",\"vodCategoryId\":\"A7001\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"2020\\/07\\/1595996982708.jpg\",\"categoryImgTv\":\"2020\\/07\\/1595996982708.jpg\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000257\",\"contentOrder\":\"2\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":null,\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":\"2020\\/07\\/1595996982708.jpg\",\"imgUrlTv\":\"2020\\/07\\/1595996982708.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 14:37:57.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":\"2020\\/07\\/1595996982708.jpg\",\"siteLogoUrlTv\":\"2020\\/07\\/1595996982708.jpg\",\"siteName\":null,\"startDt\":null,\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uC5B4\\uBA54\\uC774\\uC9D5 \\uC2A4\\uD30C\\uC774\\uB354\\uB9E8(The Amazing Spider-Man)\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M0112BJA70PPV00\",\"vodCategoryId\":\"A7001\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000258\",\"contentOrder\":\"3\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2020-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501261521.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 14:39:15.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uCD5C\\uC2E0\\uC601\\uD654\",\"type\":\"N\",\"uiType\":null,\"vodAlbumId\":null,\"vodCategoryId\":\"AQ00\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000267\",\"contentOrder\":\"4\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2099-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501332137.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:06:45.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uC601\\uD654\\uC6D4\\uC815\\uC561\",\"type\":\"N\",\"uiType\":null,\"vodAlbumId\":null,\"vodCategoryId\":\"AM000\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000265\",\"contentOrder\":\"4\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2099-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501349332.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:02:28.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uB3C5\\uC804: \\uC775\\uC2A4\\uD150\\uB514\\uB4DC \\uCEF7\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M01187H120PPV00\",\"vodCategoryId\":\"A7001\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000266\",\"contentOrder\":\"6\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2099-06-30\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501361799.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:03:09.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"BBC\\uD504\\uB86C\\uC2A4 2019: \\uBBF8\\uB974\\uAC00 \\uADF8\\uB77C\\uCE58\\uB2C8\\uD14C \\uD2F8\\uB77C\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M0119AU288PPV00\",\"vodCategoryId\":\"Q200T\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000268\",\"contentOrder\":\"7\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2020-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501373984.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:08:55.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uC218\\uD37C \\uC18C\\uB2C9(\\uC18C\\uC7A5)\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M01202S216PPV00\",\"vodCategoryId\":\"AL001\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000269\",\"contentOrder\":\"8\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2020-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501389676.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:09:54.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uAC78\\uCE85\\uC2A4(\\uC18C\\uC7A5)\",\"type\":\"L\",\"uiType\":null,\"vodAlbumId\":\"M01195U341PPV00\",\"vodCategoryId\":\"AL005\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000270\",\"contentOrder\":\"9\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":null,\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501405056.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:10:24.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":null,\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uAC10\\uCABD\\uAC19\\uC740 \\uADF8\\uB140(\\uC18C\\uC7A5)\",\"type\":\"V\",\"uiType\":null,\"vodAlbumId\":\"M0119CN016PPV00\",\"vodCategoryId\":\"AL006\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000271\",\"contentOrder\":\"10\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":null,\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501419279.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:11:07.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":null,\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uC560\\uB2C8_\\uC778\\uAE30\\uC2E0\\uC791\",\"type\":\"N\",\"uiType\":null,\"vodAlbumId\":null,\"vodCategoryId\":\"F4004\"}"+
                "]";
        hotVods = new ArrayList<>();
        try {
            JSONArray arrayContents=(JSONArray) jsonParser.parse(jsonHotVodRecords);
            arrayContents.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        NewHotVodInfoDto value = mapper.convertValue(jsonData, NewHotVodInfoDto.class);
                        hotVods.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        //private List<HotVodRecordDto> hotVodRecords;
        hotVodRecords = hotVods.stream()
                .map(hotVod -> {
                    HotVodRecordDto record = hotVod.convert();
                    record.setRowNum(new AtomicInteger().getAndIncrement());
                    return record;
                })
                .collect(Collectors.toList());


        //private List<PtUxHvCategoryEntityDto> hvCategorys;
        jsonParser=new JSONParser();
        String jsonHvCategorys = "[" +
                "{\"badgeData\":\"0\",\"categoryImg\":null,\"categoryImgTv\":null,\"categoryInfo\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"categoryName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"categoryOrder\":\"1\",\"delYn\":\"N\",\"hvCategoryId\":\"C000000255\",\"modDt\":\"2020-06-30 14:32:12\",\"modId\":\"kim82\",\"multiServiceType\":\"1\",\"parentId\":null,\"regDt\":\"2020-06-30 14:30:53\",\"regId\":\"kim82\",\"serviceType\":null,\"testYn\":\"N\",\"unifySearchYn\":\"Y\"}"+
                "]";
        hvCategorys = new ArrayList<>();
        try {
            JSONArray arrayContents=(JSONArray) jsonParser.parse(jsonHvCategorys);
            arrayContents.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        PtUxHvCategoryEntityDto value = mapper.convertValue(jsonData, PtUxHvCategoryEntityDto.class);
                        hvCategorys.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


        //private List<CategoryAlbumsDto> imcsList;
        jsonParser=new JSONParser();
        String jsonImcsLists = "[" +
                "{\"categoryId\":\"AL001\",\"contentsId\":\"M01202S216PPV00\",\"contentsName\":\"\\uC218\\uD37C \\uC18C\\uB2C9(\\uC18C\\uC7A5)\",\"seriesDesc\":null,\"seriesNo\":null,\"seriesYn\":\"N\",\"testSbc\":\"N\"}"+
                "{\"categoryId\":\"A7001\",\"contentsId\":\"M01187H120PPV00\",\"contentsName\":\"\\uB3C5\\uC804: \\uC775\\uC2A4\\uD150\\uB514\\uB4DC \\uCEF7\",\"seriesDesc\":null,\"seriesNo\":null,\"seriesYn\":\"N\",\"testSbc\":\"N\"}"+
                "{\"categoryId\":\"A7001\",\"contentsId\":\"M0117AH665PPV00\",\"contentsName\":\"\\uB3C5\\uC804: \\uC775\\uC2A4\\uD150\\uB514\\uB4DC \\uCEF7\",\"seriesDesc\":null,\"seriesNo\":null,\"seriesYn\":\"N\",\"testSbc\":\"N\"}"+
                "{\"categoryId\":\"AL006\",\"contentsId\":\"M0119CN016PPV00\",\"contentsName\":\"\\uAC10\\uCABD\\uAC19\\uC740 \\uADF8\\uB140(\\uC18C\\uC7A5)\",\"seriesDesc\":null,\"seriesNo\":null,\"seriesYn\":\"N\",\"testSbc\":\"N\"}"+
                "{\"categoryId\":\"AL005\",\"contentsId\":\"M01195U341PPV00\",\"contentsName\":\"\\uAC78\\uCE85\\uC2A4(\\uC18C\\uC7A5)\",\"seriesDesc\":null,\"seriesNo\":null,\"seriesYn\":\"N\",\"testSbc\":\"N\"}"+
                "]";
        imcsList = new ArrayList<>();
        try {
            JSONArray arrayContents=(JSONArray) jsonParser.parse(jsonImcsLists);
            arrayContents.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        CategoryAlbumsDto value = mapper.convertValue(jsonData, CategoryAlbumsDto.class);
                        imcsList.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // private List<AlbumTrailerDto> albumTrailers;
        jsonParser=new JSONParser();
        String jsonAlbumTrailers = "[" +
                "{\"albumId\":\"M0119CN016PPV00\",\"badgeIcon\":\"\",\"genreLarge\":\"\\uC601\\uD654\",\"genreMid\":\"\\uB4DC\\uB77C\\uB9C8\",\"genreSmall\":null,\"logoImageFile\":\"ST_M0119CN016PPV00_154102LI.png\",\"mainImageFile\":\"ST_M0119CN016PPV00_154102AI.jpg\",\"onairDate\":null,\"posterHFile\":\"M0119CN016PPV00SH160.jpg\",\"posterRFile\":\"M0119CN016PPV00_R_154102.jpg\",\"posterVFile\":\"M0119CN016PPV00SH170.jpg\",\"rating\":\"01\",\"runTime\":\"01:44:37\",\"stillImageFile\":\"ST_M0119CN016PPV00_092500.jpg\",\"trailerLowFile\":\"M0119CN016PPV00SH108.mpg\",\"trailerMainFile\":\"M0119CN016PPV00HD108.mpg\"}"+
                "{\"albumId\":\"M01195U341PPV00\",\"badgeIcon\":\"\",\"genreLarge\":\"\\uC601\\uD654\",\"genreMid\":\"\\uCF54\\uBBF8\\uB514\",\"genreSmall\":null,\"logoImageFile\":\"ST_M01195U341PPV00_131619LI.png\",\"mainImageFile\":\"ST_M01195U341PPV00_131619AI.jpg\",\"onairDate\":null,\"posterHFile\":\"M01195U341PPV00SH160.jpg\",\"posterRFile\":\"M01195U341PPV00_R_122735.jpg\",\"posterVFile\":\"M01195U341PPV00SH170.jpg\",\"rating\":\"04\",\"runTime\":\"01:47:37\",\"stillImageFile\":\"ST_M01195U341PPV00_093900.jpg\",\"trailerLowFile\":\"M01195U341PPV00SH108.mpg\",\"trailerMainFile\":\"M01195U341PPV00HD108.mpg\"}"+
                "{\"albumId\":\"M01202S216PPV00\",\"badgeIcon\":\"\",\"genreLarge\":\"\\uC601\\uD654\",\"genreMid\":\"\\uC561\\uC158\",\"genreSmall\":null,\"logoImageFile\":\"ST_M01202S216PPV00_131123LI.png\",\"mainImageFile\":\"ST_M01202S216PPV00_094621AI.jpg\",\"onairDate\":null,\"posterHFile\":\"M01202S216PPV00SH260.jpg\",\"posterRFile\":\"M01202S216PPV00_R_094621.jpg\",\"posterVFile\":\"M01202S216PPV00SH270.jpg\",\"rating\":\"01\",\"runTime\":\"02:05:41\",\"stillImageFile\":\"ST_M01202S216PPV00_093701.jpg\",\"trailerLowFile\":\"M01202S216PPV00SH208.mpg\",\"trailerMainFile\":\"M01202S216PPV00HD208.mpg\"}"+
                "{\"albumId\":\"M01187H120PPV00\",\"badgeIcon\":\"\",\"genreLarge\":\"\\uC601\\uD654\",\"genreMid\":\"\\uC561\\uC158\",\"genreSmall\":null,\"logoImageFile\":\"ST_M01187H120PPV00_000000LI.png\",\"mainImageFile\":\"ST_M01187H120PPV00_000000AI.jpg\",\"onairDate\":null,\"posterHFile\":\"M01187H120PPV00SH160.jpg\",\"posterRFile\":\"M01187H120PPV00_R_000000.jpg\",\"posterVFile\":\"M01187H120PPV00SH170.jpg\",\"rating\":\"04\",\"runTime\":\"02:11:44\",\"stillImageFile\":\"ST_M01187H120PPV00_161121.jpg\",\"trailerLowFile\":\"M01187H120PPV00SH108.mpg\",\"trailerMainFile\":\"M01187H120PPV00HD108.mpg\"}"+
                "]";
        albumTrailers = new ArrayList<>();
        try {
            JSONArray arrayContents=(JSONArray) jsonParser.parse(jsonAlbumTrailers);
            arrayContents.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        AlbumTrailerDto value = mapper.convertValue(jsonData, AlbumTrailerDto.class);
                        albumTrailers.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    void tearDown() {

        mFileDataUtils.close();
        log.info("@AfterEach - executed after each test method.");
    }


    @Test
    void testGetHotVodHitCount() {
        Integer response = hotVodDomainService.getHotVodHitCount("test");
        assertEquals(0, response);
    }

    @Test
    void testSetHotVodHitCount() {
        Integer response = hotVodDomainService.setHotVodHitCount("test", 0);
        assertEquals(0, response);
    }

    @Test
    void testGetHotVodLoadData() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .parentCate("C000000255")
                .imageServerUrl("iptv/hotvod/testUri")
                .order("N")
                .startNumber(-1)
                .requestCount(10)
                .build();

        when(hotVodRepository.getNewHotVods(request)).thenReturn(hotVods);
        when(hotVodRepository.getHvCategoryByCategoryIds(anyList())).thenReturn(hvCategorys);
        when(vodLookupDomainClient.getCategoryAlbums(anyList())).thenReturn(imcsList);


        for (PtUxHvCategoryEntityDto hvCategory : hvCategorys) {
            log.debug("{} ", ToStringBuilder.reflectionToString(hvCategory, ToStringStyle.JSON_STYLE));
        }

        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodLoadData(request);
        assertThat(resultList.size()).isEqualTo(9);
    }

    @Test
    void testGetHotVodSearchList() {
        List<String> filteringSite = new ArrayList<>();
        filteringSite.add("https://kids.youtube.com");
        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .parentCate("C000000255")
                .imageServerUrl("iptv/hotvod/testUri")
                .order("N")
                .type("C|V|M|N|L")
                .siteId("testsiteid")
                .filteringSite(filteringSite)
                .startNumber(-1)
                .requestCount(10)
                .build();

        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodSearchList(request, hotVodRecords);
        assertThat(resultList.size()).isZero();
    }

    @Test
    void testGetHotVodSearchList_isMasterContentInclude() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .imageServerUrl("iptv/hotvod/testUri")
                .order("N")
                .type("")
                .masterContentInclude("Y")
                .startNumber(-1)
                .requestCount(10)
                .build();

        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodSearchList(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(8);
    }

    @Test
    void testGetHotVodSearchList_isMasterInclude_parent() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .parentCate("C000000255")
                .imageServerUrl("iptv/hotvod/testUri")
                .order("N")
                .type("")
                .masterContentInclude("Y")
                .startNumber(-1)
                .requestCount(10)
                .build();

        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodSearchList(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(8);
    }

    @Test
    void testGetHotVodSearchList_isNonMasterContentInclude() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .imageServerUrl("iptv/hotvod/testUri")
                .order("N")
                .type("")
                .startNumber(-1)
                .requestCount(10)
                .build();

        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodSearchList(request, hotVodRecords);
        assertThat(resultList.size()).isZero();
    }


    @Test
    void testGetHotVodOrderList() {

        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodOrderList(hotVodRecords);
        assertThat(resultList.size()).isEqualTo(5);
    }

    @Test
    void testGetHotVodRecordComparator() {

        HotVodListRequestDto request = HotVodListRequestDto.builder().order("H").build();
        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodRecordComparator(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);

        request = HotVodListRequestDto.builder().order("N").build();
        resultList = hotVodDomainService.getHotVodRecordComparator(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);

        request = HotVodListRequestDto.builder().order("S").build();
        resultList = hotVodDomainService.getHotVodRecordComparator(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);

        request = HotVodListRequestDto.builder().order("O").build();
        resultList = hotVodDomainService.getHotVodRecordComparator(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);

        request = HotVodListRequestDto.builder().build();
        resultList = hotVodDomainService.getHotVodRecordComparator(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);
    }

    @Test
    void testGetHotVodsLimt() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .startNumber(-1)
                .requestCount(10)
                .build();
        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodsLimt(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);

    }

    @Test
    void testGetHotVodsLimt_skip() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .startNumber(1)
                .requestCount(10)
                .build();
        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodsLimt(request, hotVodRecords);
        assertThat(resultList.size()).isEqualTo(10);
    }


    @Test
    void testGetHotVodsLimt_totalCnt() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .startNumber(0)
                .requestCount(10)
                .build();
        List<HotVodRecordDto> resultList = hotVodDomainService.getHotVodsLimt(request, hotVodRecords);
        assertThat(resultList.size()).isZero();

    }

    @Test
    void testGetMetadataList() {

        when(vodLookupDomainClient.getTrailerInfo(anyList())).thenReturn(albumTrailers);

        List<AlbumTrailerDto> resultList = hotVodDomainService.getMetadataList("test");
        assertThat(resultList.size()).isEqualTo(4);
    }

    @Test
    void testPutMetadataList() {
        List<AlbumTrailerDto> resultList = hotVodDomainService.putMetadataList("test");
        assertThat(resultList.size()).isZero();
    }

    @Test
    void testSetMetadataList() {

        List<HotVodRecordDto> resultList = hotVodDomainService.setMetadataList(hotVodRecords, albumTrailers);
        assertThat(resultList.size()).isEqualTo(10);
    }

    @Test
    void testPutHotVodLoadData() {
        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .build();
        List<HotVodRecordDto> resultList = hotVodDomainService.putHotVodLoadData(request);
        assertThat(resultList.size()).isZero();
    }


    @Test
    void testGetFilteredHotvodList() {

        List<HotVodRecordDto> resultList = hotVodDomainService.getFilteredHotvodList(hotVodRecords, true);
        assertThat(resultList.size()).isEqualTo(10);

        resultList = hotVodDomainService.getFilteredHotvodList(hotVodRecords, false);
        assertThat(resultList.size()).isEqualTo(10);
    }


    @Test
    void testLoadFilteringSiteList() {
        Path fileOne = directory.resolve("file1.fts");
        file1 = fileOne.toFile();
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);

        List<String> brLines = new ArrayList<>(Collections.singleton("1|https://kids.youtube.com|2022-03-21 16:38"));

        when(FileDataUtils.findFile(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(fileList);
        when(FileDataUtils.getFileLineData(any())).thenReturn(brLines);

        Map<String, List<String>> resultList = hotVodDomainService.loadFilteringSiteList();
        assertThat(resultList).hasSize(1);
    }

}