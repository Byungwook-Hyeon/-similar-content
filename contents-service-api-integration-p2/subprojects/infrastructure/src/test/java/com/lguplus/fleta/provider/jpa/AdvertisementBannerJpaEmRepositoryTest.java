package com.lguplus.fleta.provider.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import org.hibernate.query.NativeQuery;
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
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertisementBannerJpaEmRepositoryTest {

    @Mock
    private EntityManager em;

    @Mock
    Query query;

    @Mock
    NativeQuery nativeQuery;

    @Mock
    org.hibernate.query.Query hibernateQuery;

    @InjectMocks
    private AdvertisementBannerJpaEmRepository advertisementBannerJpaEmRepository;

    List<AdsLiveInfoDto> advertisementBanners;

    List<Map<?, ?>> advertisementBannersMap;

    @BeforeEach
    void setUp() {
        advertisementBanners = new ArrayList<>();
        advertisementBannersMap = new ArrayList<>();

        JSONParser jsonCategoryParser=new JSONParser();
        String jsonCategoryData = "[" +
                "{\"advertisementNo\":3228,\"title\":\"[3세 이하]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775634971.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLdkj6XH8GYPQqYVM8iwbiGPNAYT1hrZSg\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019141100\",\"expiredTime\":\"20231026145900\",\"advertisementId\":\"CP42\",\"order\":1,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"00\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_1\"}"+
                "{\"advertisementNo\":3229,\"title\":\"[4세 이상]영어 동요\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775651140.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLgnDMw6xI5pnNdD57sJWpIULZ6LUDs3SK\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20181019141300\",\"expiredTime\":\"20231026145900\",\"advertisementId\":\"CP42\",\"order\":2,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":\"ALBUM_ID_2\"}"+
                "{\"advertisementNo\":3231,\"title\":\"[4세 이상]영어 애니\",\"saveFileName\":\"http://mimstb-c.uplus.co.kr:88/smartuxads/1540775692280.jpg\",\"advertisementType\":4,\"duration\":10,\"advertisementUrl\":\"https://www.youtube.com/playlist?list=PLFEgnf4tmQe8CuwieGKH2kfrXqWBB6A78\",\"grade\":\"01\",\"eventId\":null,\"startTime\":\"20211208111300\",\"expiredTime\":\"20991231235800\",\"advertisementId\":\"CP42\",\"order\":4,\"backgroundColor\":null,\"backgroundSaveFileNameVertical\":null,\"backgroundSaveFileNameHorizontal\":null,\"dateType\":\"11\",\"saveFileName2\":null,\"etc\":null,\"etc2\":null,\"albumId\":null}"+
                "]";
        try {
            JSONArray arrayNotice=(JSONArray) jsonCategoryParser.parse(jsonCategoryData);
            arrayNotice.forEach(
                    jsonData -> {
                        ObjectMapper mapper = new ObjectMapper();
                        AdsLiveInfoDto value = mapper.convertValue(jsonData, AdsLiveInfoDto.class);
                        advertisementBanners.add(value);
                        advertisementBannersMap.add(mapper.convertValue(jsonData, Map.class));
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testGetIptvAdvertisementBannerList_advertisementId_not_null() {
        doReturn(query).when(em).createNativeQuery(anyString());
        doReturn(query).when(query).setParameter(anyString(), anyString());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        when(hibernateQuery.getResultList()).thenReturn(advertisementBannersMap);

        String advertisementId = "seam05";
        List<AdsLiveInfoDto> resultList = advertisementBannerJpaEmRepository.getIptvAdvertisementBannerList(advertisementId, "imgResieServer");

        assertThat(resultList.size()).isEqualTo(3);
    }
    
    @Test
    void testGetIptvAdvertisementBannerList_advertisementId_null() {
        doReturn(query).when(em).createNativeQuery(anyString());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        when(hibernateQuery.getResultList()).thenReturn(advertisementBannersMap);
        
        String advertisementId = null;
        List<AdsLiveInfoDto> resultList = advertisementBannerJpaEmRepository.getIptvAdvertisementBannerList(advertisementId, "imgResizeServer");
        
        assertThat(resultList.size()).isEqualTo(3);
    }

    @Test
    void testGetMobileAdvertisementBannerList_advertisementId_not_null() {
        doReturn(query).when(em).createNativeQuery(anyString());
        doReturn(query).when(query).setParameter(anyString(), anyString());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        when(hibernateQuery.getResultList()).thenReturn(advertisementBannersMap);

        String advertisementId = "seam05";
        List<AdsLiveInfoDto> resultList = advertisementBannerJpaEmRepository.getMobileAdvertisementBannerList(advertisementId, "imgResieServer");

        assertThat(resultList.size()).isEqualTo(3);
    }

    @Test
    void testGetMobileAdvertisementBannerList_advertisementId_null() {
        doReturn(query).when(em).createNativeQuery(anyString());
        doReturn(nativeQuery).when(query).unwrap(any());
        doReturn(hibernateQuery).when(nativeQuery).setResultTransformer(any());
        when(hibernateQuery.getResultList()).thenReturn(advertisementBannersMap);

        String advertisementId = null;
        List<AdsLiveInfoDto> resultList = advertisementBannerJpaEmRepository.getMobileAdvertisementBannerList(advertisementId, "imgResizeServer");

        assertThat(resultList.size()).isEqualTo(3);
    }
}
