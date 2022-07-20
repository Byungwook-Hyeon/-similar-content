package com.lguplus.fleta.client;

import com.lguplus.fleta.data.dto.*;

import java.util.List;

public interface VodLookupDomainClient {
    List<ImageServerDto> getImageServers();

    List<AlbumTrailerDto> getTrailerInfo(List<String> albumIds);

    List<CategoryAlbumsDto> getCategoryAlbums(List<NewHotVodInfoDto> hotVodCategoryAlbums) ;

    List<WatchaCommentDto> getWatchaCommentList(String albumId);
}
