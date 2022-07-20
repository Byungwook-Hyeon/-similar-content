package com.lguplus.fleta.provider.rest;

import com.lguplus.fleta.data.dto.AlbumTrailerDto;
import com.lguplus.fleta.data.dto.CategoryAlbumsDto;
import com.lguplus.fleta.data.dto.ImageServerDto;
import com.lguplus.fleta.data.dto.response.inner.InnerResponseDto;
import com.lguplus.fleta.data.vo.WatchaCommentResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "vodlookup", url = "${service.vodlookup.url}")
public interface VodLookupFeignClient {

    @GetMapping(value = "/vodlookup/imageServers", consumes = "application/json")
    InnerResponseDto<List<ImageServerDto>> getImageServers();

    @GetMapping(value = "/vodlookup/album/albumInfo", consumes = "application/json")
    InnerResponseDto<List<AlbumTrailerDto>> getTrailerInfo(@RequestParam("albumId") List<String> albumIds);

    @GetMapping(value = "/vodlookup/album/watchaComment", produces = "application/json", consumes = "application/json")
    InnerResponseDto<List<WatchaCommentResultVo>> getWatchaCommentList(@RequestParam("albumId") String albumId);

    @PostMapping(value = "/vodlookup/category/albums", consumes = "application/json")
    InnerResponseDto<List<CategoryAlbumsDto>> getCategoryAlbums(@RequestBody List<CategoryAlbumsDto> categoryAlbums);

}
