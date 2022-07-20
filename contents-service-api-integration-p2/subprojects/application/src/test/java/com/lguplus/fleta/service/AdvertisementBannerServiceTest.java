package com.lguplus.fleta.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.dto.AdsLiveInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lguplus.fleta.data.dto.AdvertisementBannerDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.service.advertisement.AdvertisementBannerDomainService;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;

@ExtendWith(MockitoExtension.class)
class AdvertisementBannerServiceTest {

    @Mock
    private AdvertisementBannerDomainService advertisementBannerDomainService;

    @Mock
    private ImageServerDomainService imageServerDomainService;

    @InjectMocks
    private AdvertisementBannerService advertisementBannerService;

    List<AdsLiveInfoDto> advertisementBanners;
    
    @BeforeEach
    void setUp() throws Exception {
        given(imageServerDomainService.getImageServerUrl(ImageServerType.SMARTUX_RESIZE)).willReturn("imageServerUrl");
        
        advertisementBanners = new ArrayList<>();
        AdsLiveInfoDto entity = AdsLiveInfoDto.builder()
            .advertisementId("seam05")
            .startTime("20211208111300")
            .dateType("00")
            .advertisementNo("1")
            .title("TITLE")
            .saveFileName("/image.png")
            .advertisementType("ADVERTISEMENT_Type")
            .duration("10")
            .advertisementUrl("na||albumId||na")
            .expiredTime("20991208111300")
            .grade("na")
            .eventId("na")
            .order("1")
            .backgroundColor("ffffff")
            .backgroundSaveFileNameVertical("/vb")
            .backgroundSaveFileNameHorizontal("/hb")
            .saveFileName2("/image2.png")
            .etc("ETC")
            .etc2("ETC2")
            .trailerSh("TRAILER_SH")
            .trailerHd("TRAILER_HD")
            .badge("BADGE")
                .build()  ;
        //ReflectionTestUtils.setField(entity, "albumId", "");
        advertisementBanners.add(entity);
    }

    @Test
    @DisplayName("정상 case - 페이징")
    void testGetAdvertisementBannerList_paging() {
        given(advertisementBannerDomainService.getAdvertisementBannerList(anyString(), anyString())).willReturn(advertisementBanners);
        given(advertisementBannerDomainService.getLiveAdvertisementList(anyList())).willReturn(advertisementBanners);
        given(advertisementBannerDomainService.getPaginatedAdvertisementBannerList(anyList(), anyInt(), anyInt(), anyInt())).willReturn(advertisementBanners);

        String advertisementId = "seam05";
        Integer startNumber = 1;
        Integer requestCount = 1;
        GenericRecordsetResponseDto<AdvertisementBannerDto> result = advertisementBannerService.getAdvertisementBannerList(advertisementId, startNumber, requestCount);
        String flag = result.getFlag();

        assertThat(flag).isEqualTo("0000");
    }
    
    @Test
    @DisplayName("정상 case - 페이징 없음")
    void testGetAdvertisementBannerList_no_paging() {
        given(advertisementBannerDomainService.getAdvertisementBannerList(anyString(), anyString())).willReturn(advertisementBanners);
        given(advertisementBannerDomainService.getLiveAdvertisementList(anyList())).willReturn(advertisementBanners);
        
        String advertisementId = "seam05";
        Integer startNumber = null;
        Integer requestCount = null;
        GenericRecordsetResponseDto<AdvertisementBannerDto> result = advertisementBannerService.getAdvertisementBannerList(advertisementId, startNumber, requestCount);
        String flag = result.getFlag();
        
        assertThat(flag).isEqualTo("0000");
    }



    @Test
    @DisplayName("정상 case - refreshCache")
    void testRefreshAdvertisementBannerCache() {
        given(advertisementBannerDomainService.buildAdvertisementBannerList(null, "imageServerUrl")).willReturn(advertisementBanners);
        given(advertisementBannerDomainService.loadAdvertisementBannerList(anyString(), anyList())).willReturn(advertisementBanners);

        SuccessResponseDto result = advertisementBannerService.refreshAdvertisementBannerCache();
        String flag = result.getFlag();

        assertThat(flag).isEqualTo("0000");
    }
}
