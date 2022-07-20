package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.data.dto.request.CategoryAlbumsRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class HotVodContentsVo {

    @ParamAlias("category_id")
    private List<String> categoryId;

    @ParamAlias("contents_id")
    private List<String> contentsId;

    public CategoryAlbumsRequestDto convert() {

        return CategoryAlbumsRequestDto.builder()
                .categoryId(categoryId)
                .contentsId(contentsId)
                .build();
    }
}
