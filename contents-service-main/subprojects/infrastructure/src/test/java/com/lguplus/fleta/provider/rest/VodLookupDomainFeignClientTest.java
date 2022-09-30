package com.lguplus.fleta.provider.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.data.dto.*;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import com.lguplus.fleta.data.mapper.WatchaCommentMapper;
import com.lguplus.fleta.data.type.response.InnerResponseCodeType;
import com.lguplus.fleta.data.vo.WatchaCommentResultVo;
import com.lguplus.fleta.util.JunitTestUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VodLookupDomainFeignClientTest {

    @Mock
    private VodLookupFeignClient vodLookupFeignClient;

    @Mock
    private WatchaCommentMapper watchaCommentMapper;

    @InjectMocks
    private VodLookupDomainFeignClient vodLookupDomainFeignClient;

    @BeforeEach
    void setUp() {
        vodLookupDomainFeignClient = new VodLookupDomainFeignClient(vodLookupFeignClient, watchaCommentMapper);
    }

    @Test
    void testGetImageServers() {
        List<ImageServerDto> data = new ArrayList<>();
        InnerResponseDto<List<ImageServerDto>> response = new InnerResponseDto<>(InnerResponseCodeType.OK, data);
        given(vodLookupFeignClient.getImageServers()).willReturn(response);

        List<ImageServerDto> result = vodLookupDomainFeignClient.getImageServers();

        assertThat(CollectionUtils.isEmpty(result)).isTrue();
    }

    @Test
    void testGetTrailerInfo() {
        List<AlbumTrailerDto> data = new ArrayList<>();
        InnerResponseDto<List<AlbumTrailerDto>> response = new InnerResponseDto<>(InnerResponseCodeType.OK, data);

        given(vodLookupFeignClient.getTrailerInfo(anyList())).willReturn(response);

        //List<String> albumIds = new ArrayList<>();
        List<String> albumIds = Collections.emptyList();
        List<AlbumTrailerDto> result = vodLookupDomainFeignClient.getTrailerInfo(albumIds);

        assertThat(CollectionUtils.isEmpty(result)).isTrue();
    }

    @Test
    void testGetCategoryAlbums() {
        List<CategoryAlbumsDto> data = new ArrayList<>();
        InnerResponseDto<List<CategoryAlbumsDto>> response = new InnerResponseDto<>(InnerResponseCodeType.OK, data);
        given(vodLookupFeignClient.getCategoryAlbums(anyList())).willReturn(response);

        List<NewHotVodInfoDto> categoryAlbums = new ArrayList<>();
        JSONParser jsonParser=new JSONParser();
        String jsonHotVodRecords = "[" +
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000256\",\"contentOrder\":\"1\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":\"2020-06-30 14:37:57\",\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501228982.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 14:36:18.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":\"2020-06-30 14:37:57\",\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uB7A8\\uD398\\uC774\\uC9C0 3: \\uBC31\\uC545\\uAD00 \\uC2EC\\uD310\\uC758 \\uB0A0\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M0117AH665PPV00\",\"vodCategoryId\":\"A7001\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"2020\\/07\\/1595996982708.jpg\",\"categoryImgTv\":\"2020\\/07\\/1595996982708.jpg\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000257\",\"contentOrder\":\"2\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":null,\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":\"2020\\/07\\/1595996982708.jpg\",\"imgUrlTv\":\"2020\\/07\\/1595996982708.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 14:37:57.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":\"2020\\/07\\/1595996982708.jpg\",\"siteLogoUrlTv\":\"2020\\/07\\/1595996982708.jpg\",\"siteName\":null,\"startDt\":null,\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uC5B4\\uBA54\\uC774\\uC9D5 \\uC2A4\\uD30C\\uC774\\uB354\\uB9E8(The Amazing Spider-Man)\",\"type\":\"M\",\"uiType\":null,\"vodAlbumId\":\"M0112BJA70PPV00\",\"vodCategoryId\":\"A7001\"}"+
                "{\"badgeData\":\"0\",\"categoryId\":\"\",\"categoryImg\":\"\",\"categoryImgTv\":\"\",\"categoryName\":\"\",\"contentDesc\":null,\"contentId\":\"C000000271\",\"contentOrder\":\"10\",\"contentUrl\":null,\"contentsName\":\"\",\"duration\":null,\"endDt\":null,\"endTime\":null,\"hitCnt\":\"0\",\"imgUrl\":null,\"imgUrlTv\":\"2020\\/06\\/1593501419279.jpg\",\"parentCate\":\"C000000255\",\"parentCateName\":\"seamless_\\uAC80\\uC99D\\uC6A9\",\"rating\":\"01\",\"regDate\":\"2020-06-30 16:11:07.0\",\"seriesDesc\":\"\",\"seriesNo\":\"\",\"seriesYn\":\"\",\"siteId\":null,\"siteLogoUrl\":null,\"siteLogoUrlTv\":null,\"siteName\":null,\"startDt\":null,\"startTime\":null,\"testYn\":\"N\",\"title\":\"\\uC560\\uB2C8_\\uC778\\uAE30\\uC2E0\\uC791\",\"type\":\"N\",\"uiType\":null,\"vodAlbumId\":null,\"vodCategoryId\":\"F4004\"}"+
                "]";
        try {
            JSONArray arrayContents=(JSONArray) jsonParser.parse(jsonHotVodRecords);
            arrayContents.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        NewHotVodInfoDto value = mapper.convertValue(jsonData, NewHotVodInfoDto.class);
                        categoryAlbums.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CategoryAlbumsDto> result = vodLookupDomainFeignClient.getCategoryAlbums(categoryAlbums);

        assertThat(CollectionUtils.isEmpty(result)).isTrue();
    }


    @Test
    @DisplayName("IMCS 연동 및 결과 리턴 테스트")
    void testGetWatchaCommentList() {

        String albumId = "M01203N337PPV00";

        List<WatchaCommentResultVo> data = new ArrayList<>();
        WatchaCommentResultVo watchaCommentVo = new WatchaCommentResultVo();
        JunitTestUtils.setValue(watchaCommentVo, "indexSeq", "1");
        JunitTestUtils.setValue(watchaCommentVo, "writerName", "테스터");
        JunitTestUtils.setValue(watchaCommentVo, "reviewContent", "댓글 내용");
        JunitTestUtils.setValue(watchaCommentVo, "writerPoint", "3");
        JunitTestUtils.setValue(watchaCommentVo, "writeDt", "2021-12-28 14:20:16.000");
        data.add(watchaCommentVo);

        InnerResponseDto<List<WatchaCommentResultVo>> response = new InnerResponseDto<>(InnerResponseCodeType.OK, data);

        when(vodLookupFeignClient.getWatchaCommentList(anyString())).thenReturn(response);

        List<WatchaCommentDto> watchaCommentList = watchaCommentMapper.toDtoList(data);
        WatchaCommentDto watchaCommentDto = new WatchaCommentDto();
        JunitTestUtils.setValue(watchaCommentDto, "commentId", "1");
        JunitTestUtils.setValue(watchaCommentDto, "commentWriter", "테스터");
        JunitTestUtils.setValue(watchaCommentDto, "commentText", "댓글 내용");
        JunitTestUtils.setValue(watchaCommentDto, "commentRating", "3");
        JunitTestUtils.setValue(watchaCommentDto, "commentDate", "2021-12-28 14:20:16.000");
        watchaCommentList.add(watchaCommentDto);

        given(watchaCommentMapper.toDtoList(anyList())).willReturn(watchaCommentList);

        List<WatchaCommentDto> actualList = vodLookupDomainFeignClient.getWatchaCommentList(albumId);

        assertThat(actualList.get(0).getCommentId()).isEqualTo("1");
        assertThat(actualList.size()).isEqualTo(1);
    }
}