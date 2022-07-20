package com.lguplus.fleta.api.outer.similar;


import com.lguplus.fleta.RestDocsConfig;
import com.lguplus.fleta.advice.exhandler.OuterControllerAdvice;
import com.lguplus.fleta.config.ArgumentResolverConfig;
import com.lguplus.fleta.config.MessageConverterConfig;
import com.lguplus.fleta.data.dto.AdvertisementBannerDto;
import com.lguplus.fleta.data.dto.HotVodRecordDto;
import com.lguplus.fleta.data.dto.response.GenericHotVodResponseDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.mapper.SimilarContentRequestMapper;
import com.lguplus.fleta.data.vo.SimilarContentVo;
import com.lguplus.fleta.exhandler.ErrorResponseResolver;
import com.lguplus.fleta.service.HotVodService;
import com.lguplus.fleta.service.SimilarContentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
@WebMvcTest
@ContextConfiguration(classes = {SimilarContentController.class})
@Import(RestDocsConfig.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class SimilarContentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SimilarContentController similarContentController;
    @MockBean
    private SimilarContentService similarContentService;

    @MockBean
    private SimilarContentRequestMapper similarContentRequestMapper;
    @InjectMocks
    private OuterControllerAdvice outerControllerAdvice;
    @Mock
    private ErrorResponseResolver errorResponseResolver;

    private static final String ALBUM_ID = "M01121IA88PPV00";


    @Test
    void getData() throws Exception {
        MultiValueMap<String , String> params = new LinkedMultiValueMap<>();
        params.add("albumId", ALBUM_ID);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/similar/cont")
                .accept(MediaType.APPLICATION_JSON)
                .queryParams(params)
        ).andExpect(status().isOk())
                .andDo(document("{methodName}"))
                .andReturn();
    }
}
