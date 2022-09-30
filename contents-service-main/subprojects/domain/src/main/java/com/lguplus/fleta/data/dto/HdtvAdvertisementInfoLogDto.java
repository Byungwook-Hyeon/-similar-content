package com.lguplus.fleta.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HdtvAdvertisementInfoLogDto {

    private int advertisementNumber;
    private String title;
    private Integer rollingTime;
    private Integer advertisementType;
    private String filename;
    private String saveFilename;
    private String linkUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String registerer;
    private LocalDateTime registerTime;
    private boolean live;
    private String grade;
    private LocalDateTime actionDate;
    private String actor;
    private String actorIp;
    private String actionGubun;
    private Integer eventStatisticsId;
    private String advertisementId;
    private String backgroundColor;
    private String portraitBackgroundFilename;
    private String portraitBackgroundSaveFilename;
    private String landscapeBackgroundFilename;
    private String landscapeBackgroundSaveFilename;
    private Integer advertisementOrder;
    private String advertisementDateType;
    private String filename2;
    private String saveFilename2;
    private String productCode;
    private String chargeType;
    private String displayService;
    private String etc;
    private String etc2;
    private String osGubun;
    private String linkService;
    private String etc3;
    private String etc4;
    private String awsImageUrl;
    private String awsImageFilename;
}
