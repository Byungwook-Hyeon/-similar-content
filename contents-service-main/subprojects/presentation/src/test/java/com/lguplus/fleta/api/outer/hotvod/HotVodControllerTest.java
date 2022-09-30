package com.lguplus.fleta.api.outer.hotvod;

import com.lguplus.fleta.config.ArgumentResolverConfig;
import com.lguplus.fleta.config.MessageConverterConfig;
import com.lguplus.fleta.data.dto.AdvertisementBannerDto;
import com.lguplus.fleta.data.dto.HotVodRecordDto;
import com.lguplus.fleta.data.dto.response.GenericHotVodResponseDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.service.HotVodService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest
@ContextConfiguration(classes = {HotVodController.class, ArgumentResolverConfig.class, MessageConverterConfig.class})
class HotVodControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private HotVodService hotVodService;

    private SuccessResponseDto refreshProc;

    @BeforeEach
    void setUp() throws Exception {
        SuccessResponseDto response = SuccessResponseDto.builder().build();
        given(hotVodService.addHotVodHitCount(anyString())).willReturn(response);

        List<HotVodRecordDto> recordSet = Collections.emptyList();
        GenericHotVodResponseDto<HotVodRecordDto> hotVodResponse = GenericHotVodResponseDto.<HotVodRecordDto>genericHotVodResponseBuilder()
                .totalCnt(String.valueOf(recordSet.size()))
                .recordset(recordSet)
                .build();
        given(hotVodService.getNewHotVodList(any())).willReturn(hotVodResponse);
        given(hotVodService.refreshHotVodCache()).willReturn(hotVodResponse);
    }
    
    @Test
    void testSetHotVodHitCount() throws Exception {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("sa_id", "500175001734");
        queryParams.add("stb_mac", "8c6d.5044.7e64");
        queryParams.add("content_id", "content_id");
        
        MvcResult mvcResult = mvc.perform(post("/smartux/hotvod")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)
                ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertThat(status).isEqualTo(200);
    }


    @Test
    @DisplayName("HotVodController.getHotvodList() 정상 케이스")
    void testGetHotvodList() throws Exception {

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("version", "00000000000001");
        queryParams.add("sa_id", "M00020202292");
        queryParams.add("stb_mac", "com1googlecode");
        queryParams.add("order", "N");
        queryParams.add("start_num", "1");
        queryParams.add("req_count", "10");

        MvcResult mvcResult = mvc.perform(get("/smartux/hotvod")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertThat(status).isEqualTo(200);
    }

    @Test
    @DisplayName("HotVodController.refreshBbsIdCacheProc() 정상 케이스")
    void testRefreshHotVodCacheProc() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        MvcResult mvcResult = mvc.perform(get("/smartux/refreshHotVodCacheProc")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(queryParams)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertThat(status).isEqualTo(200);
    }

}
