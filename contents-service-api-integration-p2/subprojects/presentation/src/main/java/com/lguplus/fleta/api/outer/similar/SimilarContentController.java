package com.lguplus.fleta.api.outer.similar;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.dto.response.GenericSimilarResponseDto;
import com.lguplus.fleta.data.mapper.SimilarContentRequestMapper;
import com.lguplus.fleta.data.vo.SimilarContentVo;
import com.lguplus.fleta.service.SimilarContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유사 컨텐츠 조회")
@Slf4j
@RestController
@RequiredArgsConstructor
public class SimilarContentController {

    private final SimilarContentRequestMapper similarContentRequestMapper;
    private final SimilarContentService similarContentService;

    @ApiOperation(value = "유사컨텐츠 조회", notes = "albumId에 해당하는 유사컨텐츠의 정보를 조회한다.")
    @ApiImplicitParam(paramType="query", dataType="string", required=true, name="albumId", value="<br>자리수: 글쎄<br>설명: 앨범 아이디", example = "M01109BA34PPV00")
    @GetMapping("/similar/cont")
    public GenericSimilarResponseDto<SimilarContentDto> getData(SimilarContentVo  similarContentVo){
        SimilarRequestDto similarRequestDto = similarContentRequestMapper.toDto(similarContentVo);
        return similarContentService.getMetaList(similarRequestDto);
    }
}
