package com.lguplus.fleta.api.inner.advertisement;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import com.lguplus.fleta.data.vo.AdvertisementMetaBannerVo;
import com.lguplus.fleta.data.vo.AdvertisementMetaVo;
import com.lguplus.fleta.service.AdvertisementMetaService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Api(tags = "광고 메타 조회")
@RestController
@AllArgsConstructor
@RequestMapping("/contents")
public class AdvertisementMetaController {

    private final AdvertisementMetaService advertisementMetaService;

    @ApiOperation(value = "광고 라이브 메타 목록 조회", notes = "라이브 상태의 광고 메타 목록을 조회하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adsNo", value = "광고 번호", required = true, allowMultiple = true, dataType = "int", paramType = "query", example = "9373"),
            @ApiImplicitParam(name = "screenType", value = "스크린 타입<br>" +
                    "<span style=\"display: block; padding-left: 10px;\">" +
                    "- I : IPTV<br>" +
                    "- N : 모바일</span>", dataType = "string", paramType = "query", example = "I")
    })
    @GetMapping(value = "/advertisement/live/meta", produces = "application/json")
    public InnerResponseDto<List<AdvertisementMetaDto>> getAdvertisementLiveMeta(
            @Valid final AdvertisementMetaVo request) {

        final Map<Integer, AdvertisementMetaDto> advertisementMetas =
                advertisementMetaService.getAdvertisementLiveMeta(request.convert()).stream()
                        .collect(Collectors.toMap(AdvertisementMetaDto::getAdsNo, Function.identity()));
        return InnerResponseDto.of(request.getAdsNo().stream()
                .filter(advertisementMetas::containsKey)
                .map(advertisementMetas::get)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "배너 타입 광고 라이브 메타 목록 조회", notes = "라이브 상태의 광고 메타 배너 타입 목록을 조회하는 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adsId", value = "광고 아이디", required = true, allowMultiple = true, dataType = "string", paramType = "query", example = "SEAM1"),
            @ApiImplicitParam(name = "screenType", value = "스크린 타입<br>" +
                    "<span style=\"display: block; padding-left: 10px;\">" +
                    "- I : IPTV<br>" +
                    "- N : 모바일</span>", dataType = "string", paramType = "query", example = "I")
    })
    @GetMapping(value = "/advertisement/live/meta/banner", produces = "application/json")
    public InnerResponseDto<Map<String, List<AdvertisementMetaDto>>> getAdvertisementLiveMetaBanner(
            @Valid final AdvertisementMetaBannerVo request) {

        final Map<String, List<AdvertisementMetaDto>> advertisementMetas =
                advertisementMetaService.getAdvertisementLiveMeta(request.convert()).stream()
                        .collect(Collectors.groupingBy(AdvertisementMetaDto::getAdsId));
        final Map<String, List<AdvertisementMetaDto>> response = new LinkedHashMap<>();
        request.getAdsId().stream()
                .forEach(e -> response.put(e, Optional.ofNullable(advertisementMetas.get(e)).orElse(List.of())));
        return InnerResponseDto.of(response);
    }
}

