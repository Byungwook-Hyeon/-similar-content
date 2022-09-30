package com.lguplus.fleta.data.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class AlbumTrailerDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 앨범 ID
     */
    private String albumId;
    /**
     * 방송일
     */
    private String onairDate;
    /**
     * 대장르
     */
    private String genreLarge;
    /**
     * 중장르
     */
    private String genreMid;
    /**
     * 소장르
     */
    private String genreSmall;
    /**
     * 연령등급
     */
    private String rating;
    /**
     * 재생시간
     */
    private String runTime;
    /**
     * 세로포스터파일명
     */
    private String posterVFile;
    /**
     * 가로포스터파일명(구포스터)
     */
    private String posterHFile;
    /**
     * 가로포스터파일명(신규포스터)
     */
    private String posterRFile;
    /**
     * 스틸컷파일명
     */
    private String stillImageFile;
    /**
     * 대표이미지파일명
     */
    private String mainImageFile;
    /**
     * 타이틀로고이미지파일명
     */
    private String logoImageFile;
    /**
     * 뱃지아이콘 BADGE
     */
    private String badgeIcon;
    /**
     * VOD 영상 파일명 (고화질) TRAILER_HD
     */
    private String trailerMainFile;
    /**
     * VOD 영상 파일명 (저화질) TRAILER_SH
     */
    private String trailerLowFile;

}