package com.lguplus.fleta.data.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class WatchaCommentRequestDto  extends CommonPagingRequestDto {

    private String albumId;

}