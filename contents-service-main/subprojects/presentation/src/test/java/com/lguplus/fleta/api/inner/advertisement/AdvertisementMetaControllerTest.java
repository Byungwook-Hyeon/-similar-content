package com.lguplus.fleta.api.inner.advertisement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.fleta.advice.exhandler.InnerControllerAdvice;
import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import com.lguplus.fleta.service.AdvertisementMetaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AdvertisementMetaController.class, InnerControllerAdvice.class})
class AdvertisementMetaControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AdvertisementMetaService advertisementMetaService;

    @Test
    void testGetAdvertisementLiveMeta() throws Exception {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsNo(9373);
        advertisementMeta.setTitle("아이들나라");
        advertisementMeta.setContent("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");
        advertisementMeta.setSaveFileName("1627551500607.jpg");
        doReturn(List.of(advertisementMeta)).when(advertisementMetaService).getAdvertisementLiveMeta(any());

        MvcResult mvcResult = mvc.perform(get("/contents/advertisement/live/meta")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsNo", "9373"))
                .andExpect(status().isOk())
                .andReturn();

        List<AdvertisementMetaDto> response = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<InnerResponseDto<List<AdvertisementMetaDto>>>() {}).getResult().getData();
        assertThat(response).hasSize(1);

        AdvertisementMetaDto result = response.get(0);
        assertThat(result.getTitle()).isEqualTo("아이들나라");
        assertThat(result.getContent()).isEqualTo("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");
        assertThat(result.getSaveFileName()).isEqualTo("1627551500607.jpg");
        assertThat(result.getSaveFileName2()).isNull();
    }

    @Test
    void testGetAdvertisementLiveMetaWithMultipleAdsNo() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsNo", "9373")
                        .queryParam("adsNo", "7348"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaWithoutAdsId() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaWithEmptyAdsNo() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsNo", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaWithNotNumericAdsNo() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsNo", "A103"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaWithMultipleAdsNoButOneIsEmpty() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsNo", "9373")
                        .queryParam("adsNo", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaWithMultipleAdsNoButOneIsNotNumeric() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsNo", "9373")
                        .queryParam("adsNo", "A103"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaBanner() throws Exception {

        final AdvertisementMetaDto advertisementMeta = new AdvertisementMetaDto();
        advertisementMeta.setAdsId("SEAM1");
        advertisementMeta.setTitle("아이들나라");
        advertisementMeta.setContent("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");
        advertisementMeta.setSaveFileName("1627551500607.jpg");
        doReturn(List.of(advertisementMeta)).when(advertisementMetaService).getAdvertisementLiveMeta(any());

        MvcResult mvcResult = mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsId", "SEAM1"))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, List<AdvertisementMetaDto>> response = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<InnerResponseDto<Map<String, List<AdvertisementMetaDto>>>>() {}).getResult().getData();
        assertThat(response).hasSize(1);
        assertThat(response).containsKey("SEAM1");

        List<AdvertisementMetaDto> resultList = response.get("SEAM1");
        assertThat(resultList).hasSize(1);

        AdvertisementMetaDto result = resultList.get(0);
        assertThat(result.getTitle()).isEqualTo("아이들나라");
        assertThat(result.getContent()).isEqualTo("KIDS||com.lguplus.iptv3.base.launcher||com.lguplus.iptv3.base.launcher.kids.KidsHomeMainActivity");
        assertThat(result.getSaveFileName()).isEqualTo("1627551500607.jpg");
        assertThat(result.getSaveFileName2()).isNull();
    }

    @Test
    void testGetAdvertisementLiveMetaBannerWithMultipleAdsId() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsId", "SEAM1")
                        .queryParam("adsId", "SEAM2"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaBannerWithoutAdsId() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaBannerWithEmptyAdsId() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsId", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void testGetAdvertisementLiveMetaBannerWithMultipleAdsIdButOneIsEmpty() throws Exception {

        mvc.perform(get("/contents/advertisement/live/meta/banner")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam("adsId", "SEAM1")
                        .queryParam("adsId", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
