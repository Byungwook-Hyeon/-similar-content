package com.lguplus.fleta.domain.repository.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.data.dto.NewHotVodInfoDto;
import com.lguplus.fleta.data.entity.PtUxHvCategoryEntityDto;
import com.lguplus.fleta.provider.jpa.HotVodCategoryJpaRepository;
import com.lguplus.fleta.provider.jpa.HotVodJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(SpringExtension.class)
class HotVodJpaRepositoryImplTest {

    @Mock
    HotVodJpaRepository hotVodJpaRepository;
    @Mock
    HotVodCategoryJpaRepository hotVodCategoryJpaRepository;

    @InjectMocks
    private HotVodJpaRepositoryImpl hotVodRepository;

    private List<NewHotVodInfoDto> hotVods;
    private List<PtUxHvCategoryEntityDto> hvCategorys;

    @BeforeEach
    void init() {

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
    }
    @Test
    void testGetHvCategoryByCategoryIds() {

        when(hotVodCategoryJpaRepository.findHvCategoryByCategoryIds(anyList())).thenReturn(hvCategorys);

        List<PtUxHvCategoryEntityDto> resultList = hotVodRepository.getHvCategoryByCategoryIds(anyList());
        assertThat(resultList.size()).isEqualTo(1);
    }

    @Test
    void testGetNewHotVods() {

        when(hotVodJpaRepository.getNewHotVods(any())).thenReturn(hotVods);

        List<NewHotVodInfoDto> resultList = hotVodRepository.getNewHotVods(any());
        assertThat(resultList.size()).isEqualTo(10);
    }
}