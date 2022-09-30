package com.lguplus.fleta.api.outer.watcha;

import com.lguplus.fleta.config.ArgumentResolverConfig;
import com.lguplus.fleta.service.WatchaCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WatchaCommentController.class, ArgumentResolverConfig.class})
class WatchaCommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WatchaCommentService watchaCommentService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Controller 요청 테스트")
    void test_getWatchaCommentList() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("sa_id", "500175001734");
        queryParams.add("stb_mac", "8c6d.5044.7e64");
        queryParams.add("album_id", "M01093HA81PPV000");
        queryParams.add("start_num", "2");
        queryParams.add("req_count", "10");

        MvcResult mvcResult = mvc.perform(get("/mims/watcha/comment/lists")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParams(queryParams)
                ).andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();

        assertThat(status).isEqualTo(200);
    }

    @Test
    @DisplayName("Controller OpenAPI 요청 테스트")
    void test_getWatchaCommentList_OpenApi() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_key", "C9CBFC04C27F34ADC97A");
        queryParams.add("cp_id", "4000218816");
        queryParams.add("sa_id", "500175001734");
        queryParams.add("stb_mac", "8c6d.5044.7e64");
        queryParams.add("album_id", "M01093HA81PPV000");
        queryParams.add("start_num", "2");
        queryParams.add("req_count", "10");

        MvcResult mvcResult = mvc.perform(get("/mims/v1/watcha/comment/lists")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParams(queryParams)
                ).andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();

        assertThat(status).isEqualTo(200);
    }
}