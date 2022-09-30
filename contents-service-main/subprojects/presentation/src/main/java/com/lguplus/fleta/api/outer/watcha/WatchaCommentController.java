package com.lguplus.fleta.api.outer.watcha;

import com.lguplus.fleta.data.dto.WatchaCommentDto;
import com.lguplus.fleta.data.dto.request.WatchaCommentRequestDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.data.vo.WatchaCommentOpenApiVo;
import com.lguplus.fleta.data.vo.WatchaCommentVo;
import com.lguplus.fleta.service.WatchaCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Watcha 댓글 정보")
@Slf4j
@RequiredArgsConstructor
@RestController
public class WatchaCommentController {

    private final WatchaCommentService watchaCommentService;

    @Operation(summary = "Watcha 댓글 리스트 조회", description = "단말에서 UFLIX용 컨텐츠와 연계된 왓챠 댓글 목록을 조회하기 위한 인터페이스이다.")
    @Parameters(value = {
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "sa_id", description = "순번: 1<br>자리수: 12<br>설명: 가입 번호", example = "500175001734"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "stb_mac", description = "순번: 2<br>자리수: 20<br>설명: 맥 주소", example = "8c6d.5044.7e64"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = true, name = "album_id", description = "순번: 3<br>자리수: 20<br>설명: 앨범 ID", example = "M01093HA81PPV000"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = true, name = "start_num", description = "순번: 4<br>자리수: 3<br>설명: 검색 시작 인덱스<br>레코드 시작점 (req_count 값을 확인하여 페이징 된 레코드만큼 정보 전달)", example = "1"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "req_count", description = "순번: 5<br>자리수: 3<br>설명: 내려받을 레코드 수", example = "10"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "app_name", description = "순번: 6<br>자리수: <br>설명: 통합 통계용 서비스명", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "ui_version", description = "순번: 7<br>자리수: <br>설명: 통합 통계용 UI 버전", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "pre_page", description = "순번: 8<br>자리수: <br>설명: 통합 통계용 이전 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "cur_page", description = "순번: 9<br>자리수: <br>설명: 통합 통계용 현재 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "dev_info", description = "순번: 10<br>자리수: <br>설명: 통합 통계용 접속 단말 타입<br>ex) PHONE, PAD, PC, TV, STB", example = "STB"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "os_info", description = "순번: 11<br>자리수: <br>설명: 통합 통계용 OS 정보<br>ex) android_1.5, android_2.2, android_2.3.22, ios_5, ios_6, window_xp, window_7", example = "android_8.0.0"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "nw_info", description = "순번: 12<br>자리수: <br>설명: 통합 통계용 접속 네트워크 정보<br>ex) 3G, 4G, 5G, WIFI, WIRE, ETC", example = "WIRE"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "dev_model", description = "순번: 13<br>자리수: <br>설명: 통합 통계용 단말 모델명<br>ex) LE-E250", example = "S60UPI"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "carrier_type", description = "순번: 14<br>자리수: <br>설명: 통합 통계용 통신사 구분<br>ex) L:LGU+, K:KT, S:SKT, E:etc", example = "L"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "api_sid", description = "순번: 15<br>자리수: <br>설명: 통합 통계용 프로젝트ID", example = "")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "성공 시 응답 메시지 문자열 반환")
    })
    @GetMapping("/mims/watcha/comment/lists")
    public GenericRecordsetResponseDto<WatchaCommentDto> getWatchaCommentList(@Parameter(hidden = true) @Valid WatchaCommentVo paramsVo) {

        WatchaCommentRequestDto requestDto = paramsVo.convert();
        log.debug("WatchaCommentController.getWatchaCommentList - {}:{}", "요청 파라미터 : ", ToStringBuilder.reflectionToString(requestDto, ToStringStyle.JSON_STYLE));

        return watchaCommentService.getWatchaCommentList(requestDto);
    }


    @Operation(summary = "Watcha 댓글 리스트 조회(Open API)", description = "단말에서 UFLIX용 컨텐츠와 연계된 왓챠 댓글 목록을 조회하기 위한 인터페이스이다. (Open API)")
    @Parameters(value = {
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = true, name = "access_key", description = "순번: 1<br>자리수: <br>설명: OpenAPI 개발자 Access Key", example = "C9CBFC04C27F34ADC97A"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = true, name = "cp_id", description = "순번: 2<br>자리수: <br>설명: OpenAPI 개발자 CP ID", example = "4000218816"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "sa_id", description = "순번: 3<br>자리수: 12<br>설명:가입 번호", example = "500175001734"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "stb_mac", description = "순번: 4<br>자리수: 20<br>설명: 맥 주소", example = "8c6d.5044.7e64"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = true, name = "album_id", description = "순번: 5<br>자리수: 20<br>설명: 앨범 ID", example = "M01093HA81PPV000"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = true, name = "start_num", description = "순번: 6<br>자리수: 3<br>설명: 검색 시작 인덱스<br>레코드 시작점 (req_count 값을 확인하여 페이징 된 레코드만큼 정보 전달)", example = "1"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "req_count", description = "순번: 7<br>자리수: 3<br>설명: 내려받을 레코드 수", example = "10"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "app_name", description = "순번: 8<br>자리수: <br>설명: 통합 통계용 서비스명", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "ui_version", description = "순번: 9<br>자리수: <br>설명: 통합 통계용 UI 버전", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "pre_page", description = "순번: 10<br>자리수: <br>설명: 통합 통계용 이전 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "cur_page", description = "순번: 11<br>자리수: <br>설명: 통합 통계용 현재 페이지<br>메뉴 ID", example = ""),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "dev_info", description = "순번: 12<br>자리수: <br>설명: 통합 통계용 접속 단말 타입<br>ex) PHONE, PAD, PC, TV, STB", example = "STB"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "os_info", description = "순번: 13<br>자리수: <br>설명: 통합 통계용 OS 정보<br>ex) android_1.5, android_2.2, android_2.3.22, ios_5, ios_6, window_xp, window_7", example = "android_8.0.0"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "nw_info", description = "순번: 14<br>자리수: <br>설명: 통합 통계용 접속 네트워크 정보<br>ex) 3G, 4G, 5G, WIFI, WIRE, ETC", example = "WIRE"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "dev_model", description = "순번: 15<br>자리수: <br>설명: 통합 통계용 단말 모델명<br>ex) LE-E250", example = "S60UPI"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "carrier_type", description = "순번: 16<br>자리수: <br>설명: 통합 통계용 통신사 구분<br>ex) L:LGU+, K:KT, S:SKT, E:etc", example = "L"),
        @Parameter(in = ParameterIn.QUERY, schema = @Schema(type = "string"), required = false, name = "api_sid", description = "순번: 17<br>자리수: <br>설명: 통합 통계용 프로젝트ID", example = "")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "성공 시 응답 메시지 문자열 반환")
    })
    @GetMapping("/mims/v1/watcha/comment/lists")
    public GenericRecordsetResponseDto<WatchaCommentDto> getWatchaCommentListOpenApi(@Parameter(hidden = true) @Valid WatchaCommentOpenApiVo paramsVo) {

        WatchaCommentRequestDto requestDto = paramsVo.convert();

        return watchaCommentService.getWatchaCommentList(requestDto);
    }
}
