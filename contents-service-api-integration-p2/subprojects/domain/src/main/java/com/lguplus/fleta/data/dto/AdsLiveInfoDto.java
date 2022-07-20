package com.lguplus.fleta.data.dto;

import lombok.*;

import java.io.Serializable;

/**
 * PT_HDTV_ADS_MASTER, PT_HDTV_ADS_INFO 테이블과 맵핑 되는 VO
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdsLiveInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String advertisementId;
    private String advertisementNo;
    private String title;
    private String saveFileName;
    private String advertisementType;
    private String duration;
    private String advertisementUrl;
    private String grade;
    private String eventId;
    private String startTime;
    private String expiredTime;
    private String order;
    private String backgroundColor;
    private String backgroundSaveFileNameVertical;
    private String backgroundSaveFileNameHorizontal;
    private String dateType;
    private String saveFileName2;
    private String etc;
    private String etc2;
    private String albumId;
    private String trailerSh;
    private String trailerHd;
    private String badge;

}

