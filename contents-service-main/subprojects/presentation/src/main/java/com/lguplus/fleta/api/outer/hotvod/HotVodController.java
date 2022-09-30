package com.lguplus.fleta.api.outer.hotvod;

import com.lguplus.fleta.data.dto.HotVodRecordDto;
import com.lguplus.fleta.data.dto.request.HotVodListRequestDto;
import com.lguplus.fleta.data.dto.response.GenericHotVodResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.vo.HotVodHitCountVo;
import com.lguplus.fleta.data.vo.HotVodListVo;
import com.lguplus.fleta.service.HotVodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "화제 동영상")
@Slf4j
@RestController
@AllArgsConstructor
public class HotVodController {

    private final HotVodService hotVodService;

    @Operation(summary = "화제 동영상 내역 조회", description = "단말에서 화제 동영상 카테고리 및 컨텐츠를 조회하기 위한 인터페이스이다.")
    @Parameters({
        @Parameter(in = ParameterIn.QUERY, required = true, name = "version", description = "순번: 1<br>자리수: 14<br>설명: 버전정보(검수용버전정보:00000000000000)", example = "20140124142507"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "sa_id", description = "순번: 2<br>자리수: 12<br>설명: 가입자 번호", example = "500175001734"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "stb_mac", description = "순번: 3<br>자리수: 20<br>설명: 맥어드레스", example = "8c6d50447e64"),
        @Parameter(in = ParameterIn.QUERY, name = "parent_cate", description = "순번: 4<br>자리수: 10<br>설명: 부모카테고리 ID (빈값 :최상위, ARC001 : 주간 랭킹, Top100), *조회수통계에의한자동편성카테고리ID는연동규격추가를통해대응한다", example = ""),
        @Parameter(in = ParameterIn.QUERY, name = "type", description = "순번: 5<br>자리수: 10<br>설명: 타입 (C:카테고리, V:영상, M:VOD상세, N:VOD 카테고리, L:컨텐츠 URL, 빈값:전체), *2개 이상의 타입 요청시 파이프 구분자로 구분(예) C|V|M|N|L)", example = "C|V|M|N|L"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "order", description = "순번: 6<br>자리수: 1<br>설명: 정렬순서(N:최신순, H:조회순, S:출처사이트순, O:인기순(관리자임의))", schema = @Schema(type = "string" , allowableValues = {"N", "H", "S", "O"}), example = "N"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "start_num", description = "순번: 7<br>자리수: 8<br>설명: 시작레코드(-1:전체 리스트, 0:총개수, 그외: 시작레코드)", example = "-1"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "req_count", description = "순번: 8<br>자리수: 4<br>설명: 더보기시추가로내려받을레코드수", example = "10"),
        @Parameter(in = ParameterIn.QUERY,  name = "site_id", description = "순번: 9<br>자리수: 10<br>설명: 사이트필터링 ID (S001 : 유튜브)<br> * 사이트필터링이필요한출처의경우, 연동규격추가를통해서대응한다", example = "S001"),
        @Parameter(in = ParameterIn.QUERY,  name = "filtering_id", description = "순번: 10<br>자리수: <br>설명: 컨텐츠URL 필터링 ID(빈값 : IPTV)", example = "IPTV"),
        @Parameter(in = ParameterIn.QUERY,  name = "master_content_include", description = "순번: 11<br>자리수: <br>설명: 마스터컨텐츠 포함여부(Y :포함, N: 미포함)",schema = @Schema(type = "string" , allowableValues = {"Y", "N"}), example = "N"),
        @Parameter(in = ParameterIn.QUERY,  name = "app_name", description = "순번: 12<br>자리수: <br>설명: 통합 통계용 서비스명(U+HDTV/VIDEOLTE)", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "ui_version", description = "순번: 13<br>자리수: <br>설명: 통합 통계용 UI 버전", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "pre_page", description = "순번: 14<br>자리수: <br>설명: 통합 통계용 이전 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cur_page", description = "순번: 15<br>자리수: <br>설명: 통합 통계용 현재 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "dev_info", description = "순번: 16<br>자리수: <br>설명: 통합 통계용 접속 단말 타입<br>ex) PHONE, PAD, PC, TV, STB", example = "TV"),
        @Parameter(in = ParameterIn.QUERY,  name = "os_info", description = "순번: 17<br>자리수: <br>설명: 통합 통계용 OS 정보<br>ex) android_1.5, android_2.2, android_2.3.22, ios_5, ios_6, window_xp, window_7", example = "android_2.2"),
        @Parameter(in = ParameterIn.QUERY,  name = "nw_info", description = "순번: 18<br>자리수: <br>설명: 통합 통계용 접속 네트워크 정보<br>ex) 3G, 4G, 5G, WIFI, WIRE, ETC", example = "4G"),
        @Parameter(in = ParameterIn.QUERY,  name = "dev_model", description = "순번: 19<br>자리수: <br>설명: 통합 통계용 단말 모델명<br>ex) LE-E250", example = "LE-E250"),
        @Parameter(in = ParameterIn.QUERY,  name = "carrier_type", description = "순번: 20<br>자리수: <br>설명: 통합 통계용 통신사 구분<br>ex) L:LGU+, K:KT, S:SKT, E:etc", example = "L"),
        @Parameter(in = ParameterIn.QUERY,  name = "cat_id", description = "순번: 21<br>자리수: <br>설명: 통합 통계용 카테고리 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cat_name", description = "순번: 22<br>자리수: <br>설명: 통합 통계용 카테고리명", example = "")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping(value = "/smartux/hotvod")
    public GenericHotVodResponseDto<HotVodRecordDto> getHotvodList(@Parameter(hidden = true) @Valid HotVodListVo request) {
        log.debug("getHotvodList() - {}:{}", "화제 동영상 목록 조회", ToStringBuilder.reflectionToString(request, ToStringStyle.JSON_STYLE));
        HotVodListRequestDto requestDto = request.convert();
        return hotVodService.getNewHotVodList(requestDto);
    }


    @GetMapping(value = "/smartux/refreshHotVodCacheProc")
    public GenericHotVodResponseDto<HotVodRecordDto> refreshHotVodCacheProc() {
        log.debug("{} - {}", this.getClass().getSimpleName(), "공지/이미지 Cache 업데이트");
        return hotVodService.refreshHotVodCache();
    }


    @Operation(summary = "화제 동영상 조회 수 저장", description = "단말에서 화제 동영상 시청 수를 기록하기 위한 인터페이스이다.")
    @Parameters({
        @Parameter(in = ParameterIn.QUERY, required = true, name = "sa_id", description = "순번: 1<br>자리수: 12<br>설명: 가입자 번호", example = "500175001734"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "stb_mac", description = "순번: 2<br>자리수: 20<br>설명: 맥주소", example = "8c6d.5044.7e64"),
        @Parameter(in = ParameterIn.QUERY, required = true, name = "content_id", description = "순번: 3<br>자리수: 20<br>설명: 컨텐츠 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "app_name", description = "순번: 4<br>자리수: <br>설명: 통합 통계용 서비스명(U+HDTV/VIDEOLTE)", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "ui_version", description = "순번: 5<br>자리수: <br>설명: 통합 통계용 UI 버전", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "pre_page", description = "순번: 6<br>자리수: <br>설명: 통합 통계용 이전 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cur_page", description = "순번: 7<br>자리수: <br>설명: 통합 통계용 현재 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cat_id", description = "순번: 8<br>자리수: <br>설명: 통합 통계용 카테고리 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cat_name", description = "순번: 9<br>자리수: <br>설명: 통합 통계용 카테고리명", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cont_id", description = "순번: 10<br>자리수: <br>설명: 통합 통계용 컨텐츠 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cont_name", description = "순번: 11<br>자리수: <br>설명: 통합 통계용 컨텐츠명", example = ""),
        @Parameter(in = ParameterIn.QUERY,  name = "cont_type", description = "순번: 12<br>자리수: <br>설명: 통합 통계용 화제 동영상 타입<br>ex) V:대박영상, H:하이라이트, O:쇼핑(아웃링크)", example = "V"),
        @Parameter(in = ParameterIn.QUERY,  name = "dev_info", description = "순번: 13<br>자리수: <br>설명: 통합 통계용 접속 단말 타입<br>ex) PHONE, PAD, PC, TV, STB", example = "TV"),
        @Parameter(in = ParameterIn.QUERY,  name = "os_info", description = "순번: 14<br>자리수: <br>설명: 통합 통계용 OS 정보<br>ex) android_1.5, android_2.2, android_2.3.22, ios_5, ios_6, window_xp, window_7", example = "android_2.2"),
        @Parameter(in = ParameterIn.QUERY,  name = "nw_info", description = "순번: 15<br>자리수: <br>설명: 통합 통계용 접속 네트워크 정보<br>ex) 3G, 4G, 5G, WIFI, WIRE, ETC", example = "4G"),
        @Parameter(in = ParameterIn.QUERY,  name = "dev_model", description = "순번: 16<br>자리수: <br>설명: 통합 통계용 단말 모델명<br>ex) LE-E250", example = "LE-E250"),
        @Parameter(in = ParameterIn.QUERY,  name = "carrier_type", description = "순번: 17<br>자리수: <br>설명: 통합 통계용 통신사 구분<br>ex) L:LGU+, K:KT, S:SKT, E:etc", example = "L")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping(value = "/smartux/hotvod")
    public SuccessResponseDto setHotVodHitCount(@Parameter(hidden = true) @Valid HotVodHitCountVo request) {
        log.debug("setHotVodHitCount() - {}:{}", "화제 동영상 조회 수 저장", ToStringBuilder.reflectionToString(request, ToStringStyle.JSON_STYLE));
        String contentId = request.getContentId();
        return hotVodService.addHotVodHitCount(contentId);
    }

}
