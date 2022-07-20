package com.lguplus.fleta.service;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.dto.response.GenericSimilarResponseDto;
import com.lguplus.fleta.service.similar.SimilarContentDomainService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SimilarContentService {

    private final SimilarContentDomainService similarContentDomainService;

    // realOne
    public GenericSimilarResponseDto<SimilarContentDto> getMetaList(SimilarRequestDto similarRequestDto){

        // Logic 1. albumId로 similarCont 값 받음.
        SimilarRequestDto simCont = similarContentDomainService.getSimilarList(similarRequestDto);

        // Logic 2. simCont VodMeta 값 받음.
        List<SimilarContentDto> result = similarContentDomainService.getMetaList(simCont);

        return GenericSimilarResponseDto.<SimilarContentDto>genericSimilarResponseBuilder()
                .recordset2(result)
                .build();
    }
}
