package com.lguplus.fleta.service.videolte;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarContentMetaEntity;
import com.lguplus.fleta.data.mapper.SimilarContentListMapper;
import com.lguplus.fleta.data.mapper.SimilarContentMapper;
import com.lguplus.fleta.data.type.ImageServerType;
import com.lguplus.fleta.repository.videolte.SimilarContentRepository;
import com.lguplus.fleta.service.imageserver.ImageServerDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SimilarContentDomainService {

    private final SimilarContentRepository similarContentRepository;
    private final SimilarContentMapper similarContentMapper;
    private final SimilarContentListMapper similarContentListMapper;
    private final ImageServerDomainService imageServerDomainService;

    public SimilarRequestDto getSimilarContentList(SimilarRequestDto similarRequestDto) {
        SimilarContentEntity similarCont = similarContentRepository.getSimilarContentList(similarRequestDto);
        SimilarRequestDto result = similarContentMapper.toDto(similarCont);

        return result;
    }

    public List<SimilarContentDto> getContentMetaList(SimilarRequestDto similarRequestDto) {
        String[] albums = similarRequestDto.getSimilarContent().split("\\^");

        List<SimilarContentDto> resultList = new ArrayList<>();

        for(String album : albums) {
            SimilarContentMetaEntity contentMeta = similarContentRepository.getContentMetaList(album);
            if(contentMeta != null) {
                SimilarContentDto result = new SimilarContentDto();
                result = similarContentListMapper.toDto(contentMeta);
                result.setImgUrl(imageServerDomainService.getImageServerUrl(ImageServerType.DEFAULT));
                result.setStillImgUrl(imageServerDomainService.getImageServerUrl(ImageServerType.STILLCUT));
                result.setResultType("ALB");

                resultList.add(result);
            }
        }
        return resultList;
    }
}
