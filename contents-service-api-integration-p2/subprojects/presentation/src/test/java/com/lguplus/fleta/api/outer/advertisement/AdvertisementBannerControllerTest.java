package com.lguplus.fleta.api.outer.advertisement;

import com.lguplus.fleta.config.ArgumentResolverConfig;
import com.lguplus.fleta.config.MessageConverterConfig;
import com.lguplus.fleta.data.dto.AdvertisementBannerDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.service.AdvertisementBannerService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
@ContextConfiguration(classes = {AdvertisementBannerController.class, ArgumentResolverConfig.class, MessageConverterConfig.class})
class AdvertisementBannerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdvertisementBannerService advertisementBannerService;

    @BeforeEach
    void setUp() throws Exception {
        //List<AdvertisementBannerDto> recordSet = new ArrayList<>();
        List<AdvertisementBannerDto> recordSet = Collections.emptyList();
        GenericRecordsetResponseDto<AdvertisementBannerDto> response = GenericRecordsetResponseDto.<AdvertisementBannerDto>genericRecordsetResponseBuilder()
                .totalCount(String.valueOf(recordSet.size()))
                .recordset(recordSet)
                .build();

        given(advertisementBannerService.getAdvertisementBannerList(anyString(), anyInt(), anyInt())).willReturn(response);
    }

    @Test
    @DisplayName("정상 Case")
    void testGetAdvertisementBannerList() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "seam05");
        queryParams.add("app_type", "A");
        
        MvcResult mvcResult = mvc.perform(get("/smartux/comm/ad")
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
    @DisplayName("정상 Case")
    void testRefreshAdvertisementBannerList() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/smartux/comm/refreshAdCache")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertThat(status).isEqualTo(200);
    }


//    @Test
    @DisplayName("정상 Case")
    void testBlank() {
        String test = "abc def";
        Pattern pBlank = Pattern.compile("\\s");
        Matcher mBlank = pBlank.matcher(test);
        System.out.println("===========");
        System.out.println(mBlank.find());
        System.out.println(mBlank);

        String test1 = "abcdef";
        Pattern pBlank1 = Pattern.compile("\\s");
        Matcher mBlank1 = pBlank.matcher(test1);
        System.out.println("===========");
        System.out.println(mBlank1.find());
        System.out.println(mBlank1);


    }


}
