package com.lguplus.fleta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.data.dto.HotVodRecordDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.dto.response.GenericHotVodResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.hotvod.HotVodDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import com.lguplus.fleta.service.subscriber.SubscriberDomainService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class HotVodServiceTest {
    
    @Mock
    private HotVodDomainService hotVodDomainService;

    @Mock
    private ImageServerDomainService imageServerDomainService;

    @Mock
    private SubscriberDomainService subscriberDomainService;

    @InjectMocks
    private HotVodService hotVodService;

    private List<HotVodRecordDto> hotVodRecords;
    private Integer hitCount;

    @BeforeEach
    void init() {

        JSONParser jsonParser=new JSONParser();
        String jsonHotVodRecords = "[" +
                "{\"badge_data\":\"0\",\"category_id\":\"C000000004\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"content_desc\":\"\",\"content_id\":null,\"content_url\":null,\"contents_name\":null,\"duration\":null,\"endDt\":null,\"end_time\":null,\"genre_large\":null,\"genre_mid\":null,\"hit_cnt\":\"0\",\"img_url\":null,\"img_url_tv\":null,\"onair_date\":null,\"parent_cate\":\"\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2019-03-08 16:06:07\",\"rowNum\":0,\"series_desc\":null,\"series_no\":null,\"series_yn\":null,\"siteId\":null,\"site_logo_url\":null,\"site_logo_url_tv\":null,\"site_name\":null,\"sponsor_name\":null,\"startDt\":null,\"start_time\":null,\"still_img\":null,\"testYn\":\"N\",\"title\":null,\"type\":\"C\",\"ui_type\":null,\"vod_album_id\":null,\"vod_category_id\":null}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uCD94\\uC11D \\uB3D9\\uC694\\uB97C \\uBC30\\uC6CC\\uBD10\\uC694\",\"content_id\":\"C000000050\",\"content_url\":\"https:\\/\\/kids.youtube.com\\/tv\\/kids?v=OgOdqpCxmgc\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2021\\/09\\/1631852256819.jpg\",\"onair_date\":\"\",\"parent_cate\":\"C000000004\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2020-01-22 15:53:10.0\",\"rowNum\":1,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uC74C\\uC545\\/\\uB304\\uC2A4\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uCD94\\uC11D \\uBCF4\\uB984\\uB2EC\\uC744 \\uBCF4\\uBA74\\uC11C \\uC18C\\uC6D0\\uC744 \\uBE4C\\uC5B4\\uBD10\\uC694\",\"content_id\":\"C000000053\",\"content_url\":\"https:\\/\\/kids.youtube.com\\/tv\\/kids?v=15YZmYMg4Q4\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2021\\/09\\/1631852289136.jpg\",\"onair_date\":\"\",\"parent_cate\":\"C000000004\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2020-01-22 15:55:03.0\",\"rowNum\":2,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uCCB4\\uD5D8\\/\\uD0D0\\uBC29\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uCD94\\uC11D \\uC804\\uD1B5\\uC74C\\uC2DD\\uC744 \\uB9CC\\uB4E4\\uC5B4 \\uBCF4\\uC544\\uC694!\",\"content_id\":\"C000000333\",\"content_url\":\"https:\\/\\/kids.youtube.com\\/tv\\/kids?v=is6eH55sml0\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2021\\/09\\/1631852321138.jpg\",\"onair_date\":\"\",\"parent_cate\":\"C000000004\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2020-11-16 09:46:36.0\",\"rowNum\":3,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uC694\\uB9AC\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uCD94\\uC11D \\uC804\\uD1B5\\uB180\\uC774\\uC5D0 \\uB300\\uD574 \\uC54C\\uC544\\uBCF4\\uC544\\uC694!\",\"content_id\":\"C000000332\",\"content_url\":\"https:\\/\\/kids.youtube.com\\/tv\\/kids?v=ZpEz_IR-TsY\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2021\\/09\\/1631852353638.jpg\",\"onair_date\":\"\",\"parent_cate\":\"C000000004\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2020-11-16 09:43:37.0\",\"rowNum\":4,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uC7A5\\uB09C\\uAC10\\/\\uB180\\uC774\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uB2EC\\uC758 \\uBAA8\\uC591\\uC774 \\uB2EC\\uB77C\\uC9C0\\uB294 \\uC774\\uC720\",\"content_id\":\"C000000054\",\"content_url\":\"https:\\/\\/kids.youtube.com\\/tv\\/kids?v=herOYI87v8s\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2021\\/09\\/1631852432710.jpg\",\"onair_date\":\"\",\"parent_cate\":\"C000000004\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2020-01-22 15:57:42.0\",\"rowNum\":5,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uACFC\\uD559\\uC2E4\\uD5D8\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uC1A1\\uD3B8 \\uB9CC\\uB4E4\\uAE30\",\"content_id\":\"C000000049\",\"content_url\":\"https:\\/\\/kids.youtube.com\\/tv\\/kids?v=yCNLRL4jz0c\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2021\\/09\\/1631852461091.jpg\",\"onair_date\":\"\",\"parent_cate\":\"C000000004\",\"parent_cate_name\":\"\\uC544\\uC774\\uB4E4\\uC720\\uD29C\\uBE0C \\uC378\\uB124\\uC77C\",\"rating\":\"01\",\"reg_date\":\"2020-01-22 15:52:35.0\",\"rowNum\":6,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uBBF8\\uC220\\/\\uB9CC\\uB4E4\\uAE30\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"0\",\"category_id\":\"C000000001\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"content_desc\":\"\\uD14C\\uC2A4\\uD2B8\",\"content_id\":null,\"content_url\":null,\"contents_name\":null,\"duration\":null,\"endDt\":null,\"end_time\":null,\"genre_large\":null,\"genre_mid\":null,\"hit_cnt\":\"0\",\"img_url\":null,\"img_url_tv\":null,\"onair_date\":null,\"parent_cate\":\"\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2019-01-24 15:51:31\",\"rowNum\":7,\"series_desc\":null,\"series_no\":null,\"series_yn\":null,\"siteId\":null,\"site_logo_url\":null,\"site_logo_url_tv\":null,\"site_name\":null,\"sponsor_name\":null,\"startDt\":null,\"start_time\":null,\"still_img\":null,\"testYn\":\"N\",\"title\":null,\"type\":\"C\",\"ui_type\":null,\"vod_album_id\":null,\"vod_category_id\":null}"+
                "{\"badge_data\":\"01000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"testtest\",\"content_id\":\"C000000072\",\"content_url\":\"\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"1\",\"img_url\":\"\",\"img_url_tv\":\"\",\"onair_date\":\"\",\"parent_cate\":\"C000000001\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2020-02-25 14:28:08.0\",\"rowNum\":8,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"20200225\",\"type\":\"N\",\"ui_type\":\"PU004\",\"vod_album_id\":\"\",\"vod_category_id\":\"M00QP\"}"+
                "{\"badge_data\":\"01000110000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uBE0C\\uB9BF\\uC9C0\\uD648 \\uD14C\\uC2A4\\uD2B820200225\\r\\n\\uBE0C\\uB9BF\\uC9C0\\uD648 \\uD14C\\uC2A4\\uD2B820200225\\r\\n\\uBE0C\\uB9BF\\uC9C0\\uD648 \\uD14C\\uC2A4\\uD2B820200225\",\"content_id\":\"C000000070\",\"content_url\":\"\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"1\",\"img_url\":\"\",\"img_url_tv\":\"\",\"onair_date\":\"\",\"parent_cate\":\"C000000001\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2020-02-25 14:16:14.0\",\"rowNum\":9,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uBE0C\\uB9BF\\uC9C0\\uD648 \\uD14C\\uC2A4\\uD2B820200225\",\"type\":\"N\",\"ui_type\":\"PU003\",\"vod_album_id\":\"\",\"vod_category_id\":\"U002K\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"\\uD551\\uD06C\\uD401\",\"content_id\":\"C000000002\",\"content_url\":\"https:\\/\\/www.youtube.com\\/watch?v=jKOi5zVh76I\",\"contents_name\":\"\",\"duration\":\"15:52:06\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"2\",\"img_url\":\"\",\"img_url_tv\":\"\",\"onair_date\":\"\",\"parent_cate\":\"C000000001\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2019-01-24 15:52:14.0\",\"rowNum\":10,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uD551\\uD06C\\uD401\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"10000100000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"test\",\"content_id\":\"C000000056\",\"content_url\":\"\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"1\",\"img_url\":\"\",\"img_url_tv\":\"http:\\/\\/mimstb-c.uplus.co.kr:88\\/smartux\\/iptv\\/hotvod\\/2020\\/02\\/1581906914816.png\",\"onair_date\":\"\",\"parent_cate\":\"C000000001\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2020-02-17 11:25:10.0\",\"rowNum\":11,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"test\",\"type\":\"M\",\"ui_type\":\"PU003\",\"vod_album_id\":\"\",\"vod_category_id\":\"U0035\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"test\",\"content_id\":\"C000000047\",\"content_url\":\"https:\\/\\/www.youtube.com\\/watch?v=wOx_zgwPYTo\",\"contents_name\":\"\",\"duration\":\"11:40:22\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"1\",\"img_url\":\"\",\"img_url_tv\":\"\",\"onair_date\":\"\",\"parent_cate\":\"C000000001\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2019-09-11 11:40:33.0\",\"rowNum\":12,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"test\",\"type\":\"V\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "{\"badge_data\":\"00000000000\",\"category_id\":\"\",\"category_img\":\"\",\"category_img_tv\":\"\",\"category_name\":\"\",\"content_desc\":\"111\",\"content_id\":\"C000000296\",\"content_url\":\"www.google.com\",\"contents_name\":\"\",\"duration\":\"\",\"endDt\":null,\"end_time\":\"\",\"genre_large\":\"\",\"genre_mid\":\"\",\"hit_cnt\":\"0\",\"img_url\":\"\",\"img_url_tv\":\"\",\"onair_date\":\"\",\"parent_cate\":\"C000000001\",\"parent_cate_name\":\"\\uD14C\\uC2A4\\uD2B8\",\"rating\":\"01\",\"reg_date\":\"2020-08-11 12:54:44.0\",\"rowNum\":13,\"series_desc\":\"\",\"series_no\":\"\",\"series_yn\":\"\",\"siteId\":\"\",\"site_logo_url\":\"\",\"site_logo_url_tv\":\"\",\"site_name\":\"\",\"sponsor_name\":\"\",\"startDt\":null,\"start_time\":\"\",\"still_img\":\"\",\"testYn\":\"N\",\"title\":\"\\uB9C1\\uD06C URL TEST\",\"type\":\"L\",\"ui_type\":\"\",\"vod_album_id\":\"\",\"vod_category_id\":\"\"}"+
                "]";
        hotVodRecords = new ArrayList<>();
        try {
            JSONArray arrayNotice=(JSONArray) jsonParser.parse(jsonHotVodRecords);
            arrayNotice.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        HotVodRecordDto value = mapper.convertValue(jsonData, HotVodRecordDto.class);
                        hotVodRecords.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        hitCount = 0;
    }

    @Test
    void testAddHotVodHitCount() {

        given(hotVodDomainService.getHotVodHitCount(anyString())).willReturn(0);
        given(hotVodDomainService.setHotVodHitCount(anyString(), anyInt())).willReturn(1);
        
        String contentId = "content_id";
        SuccessResponseDto result = hotVodService.addHotVodHitCount(contentId);
        assertThat(result.getFlag()).isEqualTo("0000");
    }


    @Test
    @DisplayName("HotVodService.getNewHotVodList() 전체 정상 케이스")
    void testGetNewHotVodList_all() {
        Map<String, List<String>> filteringSiteMap = new HashMap<>();
        String[] strArr = {"https://kids.youtube.com", "https://www.test2.co.kr", "https://www.test3.co.kr"};
        List<String> strList = new ArrayList<String>(Arrays.asList(strArr));
        filteringSiteMap.put("iptv", strList);

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .order("N")
                .filteringId("iptv")
                .startNumber(-1)
                .requestCount(10)
                .build();

        when(imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE)).thenReturn("imageServerUrl");
        when(hotVodDomainService.getHotVodLoadData(request)).thenReturn(hotVodRecords);
        when(hotVodDomainService.getHotVodSearchList(request, hotVodRecords)).thenReturn(hotVodRecords);
        when(hotVodDomainService.getFilteringSiteList()).thenReturn(filteringSiteMap);
        when(hotVodDomainService.getHotVodOrderList(anyList())).thenReturn(hotVodRecords);
        when(hotVodDomainService.getHotVodRecordComparator(request, hotVodRecords)).thenReturn(hotVodRecords);
        when(hotVodDomainService.getHotVodsLimt(request, hotVodRecords)).thenReturn(hotVodRecords);
        when(hotVodDomainService.setMetadataList(anyList(), anyList())).thenReturn(hotVodRecords);
        when(subscriberDomainService.isTestSubscriber(any(), any())).thenReturn(false);

        GenericHotVodResponseDto<HotVodRecordDto> result = hotVodService.getNewHotVodList(request);
        assertThat(result.getFlag()).isEqualTo("0000");
    }


    @Test
    @DisplayName("HotVodService.getNewHotVodList() parentCate 정상 케이스")
    void testGetNewHotVodList_parentCate() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .version("00000000000001")
                .saId("M00020202292")
                .stbMac("com1googlecode")
                .parentCate("C000000001|C000000004|C000000255")
                .order("N")
                .startNumber(-1)
                .requestCount(10)
                .build();

        when(imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE)).thenReturn("imageServerUrl");
        when(hotVodDomainService.getHotVodLoadData(request)).thenReturn(hotVodRecords);
        when(subscriberDomainService.isTestSubscriber(any(), any())).thenReturn(false);

        GenericHotVodResponseDto<HotVodRecordDto> result = hotVodService.getNewHotVodList(request);
        assertThat(result.getFlag()).isEqualTo("0000");
    }


    @Test
    @DisplayName("HotVodService.refreshHotVodCache() 정상 케이스")
    void testRefreshHotVodCache() {

        when(imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE)).thenReturn("imageServerUrl");
        when(hotVodDomainService.putHotVodLoadData(any())).thenReturn(hotVodRecords);
        when(hotVodDomainService.getFilteredHotvodList(any(), anyBoolean())).thenReturn(hotVodRecords);
        when(subscriberDomainService.isTestSubscriber(any(), any())).thenReturn(false);

        GenericHotVodResponseDto<HotVodRecordDto> result = hotVodService.refreshHotVodCache();
        assertThat(result.getFlag()).isEqualTo("0000");
    }

}

