package com.lguplus.fleta.data.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WatchaCommentResultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String indexSeq;

    private String writerName;

    private String reviewContent;

    private String writerPoint;

    private String writeDt;

}