package com.lguplus.fleta.data.mapper;

import com.lguplus.fleta.config.ObjectMapperConfig;
import com.lguplus.fleta.data.dto.WatchaCommentDto;
import com.lguplus.fleta.data.vo.WatchaCommentResultVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = ObjectMapperConfig.class)
public interface WatchaCommentMapper {

    @Mapping(target = "commentId", source = "resultVo.indexSeq")
    @Mapping(target = "commentWriter", source = "resultVo.writerName")
    @Mapping(target = "commentText", source = "resultVo.reviewContent")
    @Mapping(target = "commentRating", source = "resultVo.writerPoint")
    @Mapping(target = "commentDate", source = "resultVo.writeDt")
    WatchaCommentDto toDto(WatchaCommentResultVo resultVo);

    List<WatchaCommentDto> toDtoList(List<WatchaCommentResultVo> resultList);

}