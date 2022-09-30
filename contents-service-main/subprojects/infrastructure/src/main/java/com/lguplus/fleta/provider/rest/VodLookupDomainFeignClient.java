package com.lguplus.fleta.provider.rest;

import com.lguplus.fleta.client.VodLookupDomainClient;
import com.lguplus.fleta.data.dto.*;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import com.lguplus.fleta.data.mapper.WatchaCommentMapper;
import com.lguplus.fleta.data.vo.WatchaCommentResultVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VodLookupDomainFeignClient extends CommonDomainFeignClient implements VodLookupDomainClient {

    private final VodLookupFeignClient vodLookupFeignClient;

    private final WatchaCommentMapper watchaCommentMapper;

    @Override
    public List<ImageServerDto> getImageServers() {
        return getResult(vodLookupFeignClient.getImageServers());
    }

    @Override
    public List<AlbumTrailerDto> getTrailerInfo(List<String> albumIds) {
        return getResult(vodLookupFeignClient.getTrailerInfo(albumIds));
    }

    @Override
    public List<WatchaCommentDto> getWatchaCommentList(String albumId) {

        InnerResponseDto<List<WatchaCommentResultVo>> response
                = vodLookupFeignClient.getWatchaCommentList(albumId);

        List<WatchaCommentResultVo> watchaCommentList = response.getResult().getData();

        return watchaCommentMapper.toDtoList(watchaCommentList);
    }

    @Override
    public List<CategoryAlbumsDto> getCategoryAlbums(List<NewHotVodInfoDto> hotVodCategoryAlbums) {

//        List<CategoryAlbumsDto> categoryAlbums = hotVodCategoryAlbums.stream()
//                .map(
//                        dto -> CategoryAlbumsDto.builder()
//                                .categoryId(dto.getVodCategoryId())
//                                .contentsId(dto.getVodAlbumId())
//                                .build()
//                ).collect(Collectors.toList());
//
//        return getResult(vodLookupFeignClient.getCategoryAlbums(categoryAlbums));
        return List.of();
    }
}
