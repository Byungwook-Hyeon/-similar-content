package com.lguplus.fleta.service.advertisement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.AlbumTrailerDto;
import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import com.lguplus.fleta.exception.ResultNotFoundException;
import com.lguplus.fleta.repository.advertisement.AdvertisementBannerRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AdvertisementBannerDomainServiceTest {
    
    @Mock
    private AdvertisementBannerRepository advertisementBannerRepository;
    
    @Mock
    private VodLookupDomainClient vodLookupDomainClient;
    
    @InjectMocks
    private AdvertisementBannerDomainService advertisementBannerDomainService;

    List<AdsLiveInfoDto> adsLiveInfos;
    
    @BeforeEach
    void setUp() {
        adsLiveInfos = new ArrayList<>();
        //category list
        JSONParser jsonCategoryParser=new JSONParser();
        String jsonCategoryData = "[" +
                "{\"advertisementNo\":3228,\"title\":\"[3세 이하]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775634971.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLdkj6XH8GYPQqYVM8iwbiGPNAYT1hrZSg\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019141100\",\"expiredTime\":\"29991026145900\",\"advertisementId\":\"CP42\",\"order\":1,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"00\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_1\"}"+
                "{\"advertisementNo\":3229,\"title\":\"[4세 이상]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775651140.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLgnDMw6xI5pnNdD57sJWpIULZ6LUDs3SK\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019141300\",\"expiredTime\":\"29991026145900\",\"advertisementId\":\"CP42\",\"order\":2,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_2\"}"+
                "{\"advertisementNo\":3230,\"title\":\"[4세 이상]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775651140.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLgnDMw6xI5pnNdD57sJWpIULZ6LUDs3SK\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019000000\",\"expiredTime\":\"29991026235959\",\"advertisementId\":\"CP42\",\"order\":2,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"11\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_2\"}"+
                "{\"advertisementNo\":3232,\"title\":\"[4세 이상]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775651140.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLgnDMw6xI5pnNdD57sJWpIULZ6LUDs3SK\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019235959\",\"expiredTime\":\"29991026000000\",\"advertisementId\":\"CP42\",\"order\":2,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"11\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_2\"}"+
                "{\"advertisementNo\":3234,\"title\":\"[4세 이상]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775651140.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLgnDMw6xI5pnNdD57sJWpIULZ6LUDs3SK\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019000000\",\"expiredTime\":\"29991026000001\",\"advertisementId\":\"CP42\",\"order\":2,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"11\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_2\"}"+
                "{\"advertisementNo\":3233,\"title\":\"[4세 이상]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775651140.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLgnDMw6xI5pnNdD57sJWpIULZ6LUDs3SK\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019000000\",\"expiredTime\":\"20181026235959\",\"advertisementId\":\"CP42\",\"order\":2,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"11\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_2\"}"+
                "{\"advertisementNo\":3231,\"title\":\"[4세 이상]영어 애니\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775692280.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLFEgnf4tmQe8CuwieGKH2kfrXqWBB6A78\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"29991208111300\",\"expiredTime\":\"29991231235800\",\"advertisementId\":\"CP42\",\"order\":4,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"11\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":null}"+
                "]";
        try {
            JSONArray arrayNotice=(JSONArray) jsonCategoryParser.parse(jsonCategoryData);
            arrayNotice.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        AdsLiveInfoDto value = mapper.convertValue(jsonData, AdsLiveInfoDto.class);
                        adsLiveInfos.add(value);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAdvertisementBannerList() {
        given(advertisementBannerRepository.getIptvAdvertisementBannerList(anyString(), anyString())).willReturn(adsLiveInfos);

        ArrayList<AlbumTrailerDto> trailerInfos = new ArrayList<>();
        AlbumTrailerDto dto1 = AlbumTrailerDto.builder().albumId("ALBUM_ID_1")
                .trailerLowFile("TRAILER_SH_1").trailerMainFile("TRAILER_HD_1").badgeIcon("BADGE_1").build();
        trailerInfos.add(dto1);
        AlbumTrailerDto dto2 = AlbumTrailerDto.builder().albumId("ALBUM_ID_2")
                .trailerLowFile("TRAILER_SH_2").trailerMainFile("TRAILER_HD_2").badgeIcon("BADGE_2").build();
        trailerInfos.add(dto2);
        given(vodLookupDomainClient.getTrailerInfo(anyList())).willReturn(trailerInfos);

        String advertisementId = "seam05";
        String imageServerUrl = "http://xxxx.com/test";
        List<AdsLiveInfoDto> resultList = advertisementBannerDomainService.getAdvertisementBannerList(advertisementId, imageServerUrl);

        assertThat(resultList).hasSameSizeAs(adsLiveInfos);
    }

    @Test
    void testLoadAdvertisementBannerList() {
        String advertisementId = "CP42";
        List<AdsLiveInfoDto> resultList = advertisementBannerDomainService.loadAdvertisementBannerList(advertisementId, adsLiveInfos);

        assertThat(resultList).hasSameSizeAs(adsLiveInfos);
    }

    @Test
    void testGetLiveAdvertisementList() {
        List<AdsLiveInfoDto> resultList = advertisementBannerDomainService.getLiveAdvertisementList(adsLiveInfos);
        assertThat(resultList.size()).isEqualTo(3);

        Set<String> adsNos = resultList.stream().map(AdsLiveInfoDto::getAdvertisementNo).collect(Collectors.toSet());
        assertThat(adsNos).contains("3228")
                .contains("3229")
                .contains("3230");
    }
    
    @Test
    void testGetPaginatedAdvertisementBannerList_startNumber_equals_zero() {
        int totalCount = adsLiveInfos.size();
        int startNumber = 0;
        int requestCount = 3;
        List<AdsLiveInfoDto> resultList = advertisementBannerDomainService.getPaginatedAdvertisementBannerList(adsLiveInfos, totalCount, startNumber, requestCount);
        
        assertThat(resultList.size()).isZero();
    }
    
    @Test
    void testGetPaginatedAdvertisementBannerList_startNumber_greaterThen_totalCount() {
        int totalCount = adsLiveInfos.size();
        int startNumber = 100;
        int requestCount = 3;
        
        Exception e = assertThrows(ResultNotFoundException.class, () -> advertisementBannerDomainService.getPaginatedAdvertisementBannerList(adsLiveInfos, totalCount, startNumber, requestCount));
        assertTrue(e instanceof ResultNotFoundException);
    }
    
    @Test
    void testGetPaginatedAdvertisementBannerList_startNumber_greaterThen_zero() {
        int totalCount = adsLiveInfos.size();
        int startNumber = 1;
        int requestCount = 3;
        List<AdsLiveInfoDto> resultList = advertisementBannerDomainService.getPaginatedAdvertisementBannerList(adsLiveInfos, totalCount, startNumber, requestCount);
        
        assertThat(resultList.size()).isEqualTo(3);
    }
    
    @Test
    void testGetPaginatedAdvertisementBannerList_startNumber_lessThen_zero() {
        int totalCount = adsLiveInfos.size();
        int startNumber = -1;
        int requestCount = 3;
        List<AdsLiveInfoDto> resultList = advertisementBannerDomainService.getPaginatedAdvertisementBannerList(adsLiveInfos, totalCount, startNumber, requestCount);
        
        assertThat(resultList.size()).isEqualTo(3);
    }

    @Test
    void testGetPaginatedAdvertisementBannerList_startNumber_LessThen_MinusTotalCount() {
        int totalCount = adsLiveInfos.size();
        int startNumber = -100;
        int requestCount = 3;

        Exception e = assertThrows(ResultNotFoundException.class, () -> advertisementBannerDomainService.getPaginatedAdvertisementBannerList(adsLiveInfos, totalCount, startNumber, requestCount));
        assertTrue(e instanceof ResultNotFoundException);
    }
}
