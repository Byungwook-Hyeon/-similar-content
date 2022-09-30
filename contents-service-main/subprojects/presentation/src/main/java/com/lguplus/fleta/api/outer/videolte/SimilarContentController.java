package com.lguplus.fleta.api.outer.videolte;


import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.dto.response.GenericSimilarResponseDto;
import com.lguplus.fleta.data.mapper.SimilarContentRequestMapper;
import com.lguplus.fleta.data.vo.SimilarContentVo;
import com.lguplus.fleta.service.SimilarContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 수정 사항
 * 1. SimilarContentEntity
 * SimilarContentEntity -> @Table(name = "TB_P_SIMILAR_CONT_A", schema = "SMARTUX")
 * AS-IS의 "FUN_IS_ACTIVE_TBL"의 response == 1 -> TB_P_SIMILAR_CONT_A
 * AS-IS의 "FUN_IS_ACTIVE_TBL"의 response == 0 -> TB_P_SIMILAR_CONT_B
 * AS-IS의 "FUN_IS_ACTIVE_TBL"의 response == 2 -> Error(NO_DATA_FOUND)
 *
 * 현재 : 테스트 목적으로 Local의 TB_P_SIMILAR_CONT_B로만 접근하도록 구현 됨.
 * 개선 : AS-IS의 "FUN_IS_ACTIVE_TBL"을 로직으로 구현하여 TB_P_SIMILAR_CONT_A/B 구분해야 함.
 *
 *
 * 2. SimilarContentDomainService.getContentMetaList(SimilarRequestDto similarRequestDto)
 * 현재 : AS-IS 코드와 동일하게 Split된 albumId(SMARTUX.TB_P_SIMILAR_CONT_A/B.val)를 for문을 돌려 각각의 albumId에 해당하는 Meta 정보를 받아오는 형태.
 * 개선 : SMARTUX.TB_P_SIMILAR_CONT_A/B.vla을 한 번에 던져서 response가 List로 올 수 있도록.
 *
 *
 * Info
 * 1. resultType
 * SimilarContentDomainService.getContentMetaList -> result.setResultType("ALB");
 * AS-IS VodVO에 "private String resultType;  // CAT/ALB"라고 적혀 있고,
 * PanelServiceImpl.getVodMetaVO()에서 vodMetaVO.setResultType("ALB")로
 * CATEGORY는 고려치 않고 "ALB"로 픽스되어 set되고 있음.
 * 1팀 이재승님, 이민우님 확인 결과 CATEGORY를 분류하려다가 진행하지 않은건지, CATEGORY가 있다가 사라진 것인지는
 * 모르겠으나, 일단 고려치 말고 동일하게 "ALB"로 픽스하면 된다는 답변을 받음.
 * ※ 이슈가 되더라도 시험 자동화에서 확인이 불가능할 수 있음.
 *
 * 2. DB
 * Personalization 도메인의 DB인 TB_P_SIMILAR_CONT_A/B, TB_U_ACTIVE_TBL, TB_M_VOD 모두 모바일 Curation의 DB 마이그레이션 시기에 따라 개발 시기에 영향을 받게됨.
 * 전환에 필요한 DB를 최우선적으로 마이그레이션 할 수 있도록 확인 필요.
 *
 * 3. 캐시
 * 1팀은 현재 캐시를 사용중에 있으나, 2팀은 캐시를 모두 걷어내는 쪽으로 결정하여 진행중.
 * 따라서 캐시는 고려치 말고 DB에 직접 access하는 쪽으로.
 *
 */

@RestController
@RequiredArgsConstructor
public class SimilarContentController {

    private final SimilarContentRequestMapper similarContentRequestMapper;
    private final SimilarContentService similarContentService;

    @GetMapping("similar/cont")
    public GenericSimilarResponseDto<SimilarContentDto> getSimilarContentList(SimilarContentVo similarContentVo) {
        SimilarRequestDto similarRequestDto = similarContentRequestMapper.toDto(similarContentVo);

        return similarContentService.getSimilarContentList(similarRequestDto);
    }
}
