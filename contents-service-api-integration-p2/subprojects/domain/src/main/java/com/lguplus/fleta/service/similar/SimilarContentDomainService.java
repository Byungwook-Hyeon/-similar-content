package com.lguplus.fleta.service.similar;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import com.lguplus.fleta.data.dto.request.SimilarRequestDto;
import com.lguplus.fleta.data.dto.response.GenericSimilarResponseDto;
import com.lguplus.fleta.data.entity.SimilarContentEntity;
import com.lguplus.fleta.data.entity.SimilarMetaEntity;
import com.lguplus.fleta.data.mapper.SimilarContentMapper;
import com.lguplus.fleta.data.mapper.SimilarMetaMapper;
import com.lguplus.fleta.repository.similar.SimilarContentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Component
public class SimilarContentDomainService {
    private final SimilarContentRepository similarContentRepository;
    private final SimilarContentMapper similarContentMapper;
    private final SimilarMetaMapper similarMetaMapper;

    public SimilarRequestDto getSimilarList(SimilarRequestDto similarRequestDto){
        SimilarContentEntity result = similarContentRepository.getSimilarContent(similarRequestDto);

        SimilarRequestDto result2 = null;

        result2 = similarContentMapper.toDto(result);

        return result2;
    }

    public List<SimilarContentDto> getMetaList(SimilarRequestDto similarRequestDto){
        String[] albums = similarRequestDto.getSimilarCont().split("\\^");

        List<SimilarContentDto> resultList = new ArrayList<>();

        for(String album : albums) {
            similarRequestDto.setAlbumId(album);
            List<SimilarMetaEntity> records = similarContentRepository.getMetaList(similarRequestDto);

            if (records != null){
                records.forEach(e->{
                    SimilarContentDto item = similarMetaMapper.toDto(e);
                    resultList.add(item);
                });
            }
        }
        return resultList;
    }
}
