package com.lguplus.fleta.api.inner.advertisement;

import com.lguplus.fleta.data.dto.AdvertisementMetaDto;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import com.lguplus.fleta.data.vo.AdvertisementMetaBannerVo;
import com.lguplus.fleta.data.vo.AdvertisementMetaVo;
import com.lguplus.fleta.service.AdvertisementMetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "광고 메타 조회")
@RestController
@AllArgsConstructor
@RequestMapping("/contents")
public class AdvertisementMetaController {

    private final AdvertisementMetaService advertisementMetaService;

    @Operation(summary = "광고 라이브 메타 목록 조회", description = "라이브 상태의 광고 메타 목록을 조회하는 API")
    @Parameters({
        @Parameter(name = "adsNo", description = "광고 번호", required = true, array = @ArraySchema(schema = @Schema(type = "int", example = "9373"))
            , in = ParameterIn.QUERY),
        @Parameter(name = "screenType", description = "스크린 타입<br>" +
            "<span style=\"display: block; padding-left: 10px;\">" +
            "- I : IPTV<br>" +
            "- N : 모바일</span>", schema = @Schema(type = "string"), in = ParameterIn.QUERY, example = "I")
    })
    @GetMapping(value = "/advertisement/live/meta", produces = "application/json")
    public InnerResponseDto<List<AdvertisementMetaDto>> getAdvertisementLiveMeta(
        @Parameter(hidden = true) @Valid final AdvertisementMetaVo request) {

        final Map<Integer, AdvertisementMetaDto> advertisementMetas =
            advertisementMetaService.getAdvertisementLiveMeta(request.convert()).stream()
                .collect(Collectors.toMap(AdvertisementMetaDto::getAdsNo, Function.identity()));
        return InnerResponseDto.of(request.getAdsNo().stream()
            .filter(advertisementMetas::containsKey)
            .map(advertisementMetas::get)
            .collect(Collectors.toList()));
    }

    @Operation(summary = "배너 타입 광고 라이브 메타 목록 조회", description = "라이브 상태의 광고 메타 배너 타입 목록을 조회하는 API")
    @Parameters({
        @Parameter(name = "adsId", description = "광고 아이디", required = true, array = @ArraySchema(schema = @Schema(type = "string", example = "SEAM1"))
            , in = ParameterIn.QUERY),
        @Parameter(name = "screenType", description = "스크린 타입<br>" +
            "<span style=\"display: block; padding-left: 10px;\">" +
            "- I : IPTV<br>" +
            "- N : 모바일</span>", schema = @Schema(type = "string"), in = ParameterIn.QUERY, example = "I")
    })
    @GetMapping(value = "/advertisement/live/meta/banner", produces = "application/json")
    public InnerResponseDto<Map<String, List<AdvertisementMetaDto>>> getAdvertisementLiveMetaBanner(
        @Parameter(hidden = true) @Valid final AdvertisementMetaBannerVo request) {

        final Map<String, List<AdvertisementMetaDto>> advertisementMetas =
            advertisementMetaService.getAdvertisementLiveMeta(request.convert()).stream()
                .collect(Collectors.groupingBy(AdvertisementMetaDto::getAdsId));
        final Map<String, List<AdvertisementMetaDto>> response = new LinkedHashMap<>();
        request.getAdsId().stream()
            .forEach(e -> response.put(e, Optional.ofNullable(advertisementMetas.get(e)).orElse(List.of())));
        return InnerResponseDto.of(response);
    }
}

