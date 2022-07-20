package com.lguplus.fleta.api.outer.advertisement;

import com.lguplus.fleta.data.dto.AdvertisementBannerDto;
import com.lguplus.fleta.data.dto.response.GenericRecordsetResponseDto;
import com.lguplus.fleta.data.dto.response.SuccessResponseDto;
import com.lguplus.fleta.data.vo.AdvertisementBannerVo;
import com.lguplus.fleta.service.AdvertisementBannerService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@Api(tags="배너 광고")
@Slf4j
@RestController
@AllArgsConstructor
public class AdvertisementBannerController {

    private final AdvertisementBannerService advertisementBannerService;
    
    @ApiOperation(value="광고 배너 리스트 조회", notes="활성화(Live) 되어 있는 광고 배너 리스트를 보여주기 위한 인터페이스이다.")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="sa_id",        value="순번: 1<br>자리수: 12<br>설명: 가입 번호", example="500175001734"),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="stb_mac",      value="순번: 2<br>자리수: 20<br>설명: 맥주소", example="8c6d.5044.7e64"),
            @ApiImplicitParam(paramType="query", dataType="string", required=true,  name="id",           value="순번: 3<br>자리수: 20<br>설명: 마스터 아이디", example="seam05"),
            @ApiImplicitParam(paramType="query", dataType="string", required=true,  name="app_type",     value="순번: 4<br>자리수: 4<br>설명: 어플타입(RABC)<br>ex) R:Reserved, A-U:자사고객, O:타사고객, B-S:스마트폰, P:PAD, C:Computer, C-I:IOS용, A:안드로이드용, W:Windows", example="A"),
            @ApiImplicitParam(paramType="query", dataType="int",    required=false, name="start_num",    value="순번: 5<br>자리수: 3<br>설명: 검색 시작 인덱스<br>레코드 시작점(req_count 값을 확인하여 페이징된 레코드만큼 정보 전달)", example="1"),
            @ApiImplicitParam(paramType="query", dataType="int",    required=false, name="req_count",    value="순번: 6<br>자리수: 3<br>설명: 요청 카운트(start_num이 있을 경우 필수)", example="10"),            
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="app_name",     value="순번: 7<br>자리수: <br>설명: 통합 통계용 서비스명(U+HDTV/VIDEOLTE)", example=""),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="ui_version",   value="순번: 8<br>자리수: <br>설명: 통합 통계용 UI 버전", example=""),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="pre_page",     value="순번: 9<br>자리수: <br>설명: 통합 통계용 이전 페이지<br>메뉴 ID", example=""),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="cur_page",     value="순번: 10<br>자리수: <br>설명: 통합 통계용 현재 페이지<br>메뉴 ID", example=""),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="dev_info",     value="순번: 11<br>자리수: <br>설명: 통합 통계용 접속 단말 타입<br>ex) PHONE, PAD, PC, TV, STB", example="TV"),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="os_info",      value="순번: 12<br>자리수: <br>설명: 통합 통계용 OS 정보<br>ex) android_1.5, android_2.2, android_2.3.22, ios_5, ios_6, window_xp, window_7", example="android_2.2"),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="nw_info",      value="순번: 13<br>자리수: <br>설명: 통합 통계용 접속 네트워크 정보<br>ex) 3G, 4G, 5G, WIFI, WIRE, ETC", example="4G"),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="dev_model",    value="순번: 14<br>자리수: <br>설명: 통합 통계용 단말 모델명<br>ex) LE-E250", example="LE-E250"),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="carrier_type", value="순번: 15<br>자리수: <br>설명: 통합 통계용 통신사 구분<br>ex) L:LGU+, K:KT, S:SKT, E:etc", example="L"),
            @ApiImplicitParam(paramType="query", dataType="string", required=false, name="cat_id",       value="순번: 16<br>자리수: <br>설명: id와 동일(통합 통계용)", example="")
    })
    @ApiResponses({
            @ApiResponse(code=200, message="OK")
    })
    @GetMapping("/smartux/comm/ad")
    public GenericRecordsetResponseDto<AdvertisementBannerDto> getAdvertisementBannerList(@ApiIgnore @Valid AdvertisementBannerVo request) {
        log.debug("getAdvertisementBannerList() - {}:{}", "광고 배너 리스트 조회", ToStringBuilder.reflectionToString(request, ToStringStyle.JSON_STYLE));
        
        String advertisementId = request.getId();
        Integer startNumber = request.getStartNumber();
        Integer requestCount = request.getRequestCount();
        return advertisementBannerService.getAdvertisementBannerList(advertisementId, startNumber, requestCount);
    }


    @ApiOperation(value="광고 배너 리스트 캐시 업데이트", notes="활성화(Live) 되어 있는 광고 배너 리스트를 보여주기 위한 인터페이스이다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
    @GetMapping("/smartux/comm/refreshAdCache")
    public SuccessResponseDto refreshAdvertisementBannerList() {
        log.debug("{} - {}", this.getClass().getSimpleName(), "광고 배너 리스트 캐시 업데이트");
        return advertisementBannerService.refreshAdvertisementBannerCache();
    }

}
