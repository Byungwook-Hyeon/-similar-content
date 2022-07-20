package com.lguplus.fleta.provider.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.data.dto.NewHotVodInfoDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.testutil.DtoConverterTestUtil;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotVodJpaRepositoryTest {

    @Mock
    private EntityManager em;

    @Mock
    Query query;

    @Mock
    NativeQuery<?> nativeQuery;


    @InjectMocks
    private HotVodJpaRepository hotVodJpaRepository;

    List<NewHotVodInfoDto> newHotVodInfos;
    @BeforeEach
    void setUp() {

        when(em.createNativeQuery(anyString())).thenReturn(query);
        when(query.unwrap(NativeQuery.class)).thenReturn(nativeQuery);
        org.hibernate.query.Query hibernateQuery = mock(org.hibernate.query.Query.class);
        when(nativeQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)).thenReturn(hibernateQuery);

        newHotVodInfos = new ArrayList<>();
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
        try {
            JSONArray arrayContents=(JSONArray) jsonParser.parse(jsonHotVodRecords);
            arrayContents.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        NewHotVodInfoDto value = mapper.convertValue(jsonData, NewHotVodInfoDto.class);
                        newHotVodInfos.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void getNewHotVods() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .categoryId("M00020202291")
                .build();

        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(hotVodJpaRepository.convertList(query, NewHotVodInfoDto.class)).thenReturn(newHotVodInfos);

        DtoConverterTestUtil.testMockedDtoConverterForList(() -> {
            List<NewHotVodInfoDto> Dto = hotVodJpaRepository.getNewHotVods(request);
            assertThat(Dto.size()).isEqualTo(10);
        }, newHotVodInfos);

    }

    @Test
    void getNewHotVodsAll() {

        HotVodListRequestDto request = HotVodListRequestDto.builder()
                .build();

        when(hotVodJpaRepository.convertList(query, NewHotVodInfoDto.class)).thenReturn(newHotVodInfos);

        DtoConverterTestUtil.testMockedDtoConverterForList(() -> {
            List<NewHotVodInfoDto> Dto = hotVodJpaRepository.getNewHotVods(request);
            assertThat(Dto.size()).isEqualTo(10);
        }, newHotVodInfos);

    }
}