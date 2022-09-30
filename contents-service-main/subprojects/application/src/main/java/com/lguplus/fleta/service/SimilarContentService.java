package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.dto.response.GenericSimilarResponseDto;
import com.lguplus.fleta.service.videolte.SimilarContentDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SimilarContentService {

    private final SimilarContentDomainService similarContentDomainService;

    public GenericSimilarResponseDto<SimilarContentDto> getSimilarContentList(SimilarRequestDto similarRequestDto) {

        // SMARTUX.TB_P_SIMILAR_CONT_A/B.val, similarCont.
        SimilarRequestDto similarCont = similarContentDomainService.getSimilarContentList(similarRequestDto);

        // SMARTUX.TB_M_VOD에서 VodMeta.
        List<SimilarContentDto> similarContentDto = similarContentDomainService.getContentMetaList(similarCont);

        return GenericSimilarResponseDto.<SimilarContentDto>genericSimilarResponseBuilder()
                .recordset(similarContentDto)
                .build();
    }
}