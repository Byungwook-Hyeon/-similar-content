package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.entity.converter.BooleanToYnConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_HDTV_ADS_INFO", schema = "SMARTUX")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class HdtvAdvertisementInfo {

    @Id
    @Column(name = "ADS_NO")
    private int advertisementNumber;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ROL_TIME")
    private int rollingTime;

    @Column(name = "ADS_TYPE")
    private int advertisementType;

    @Column(name = "FILE_NAME")
    private String filename;

    @Column(name = "SAVE_FILE_NAME")
    private String saveFilename;

    @Column(name = "LINK_URL")
    private String linkUrl;

    @Column(name = "S_TIME")
    private LocalDateTime startTime;

    @Column(name = "E_TIME")
    private LocalDateTime endTime;

    @Column(name = "R_TIME")
    private LocalDateTime registerTime;

    @Column(name = "R_ID")
    private String registerer;

    @Column(name = "LIVE_YN")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean live;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "EV_STAT_ID")
    private Integer eventStatisticsId;

    @Column(name = "ADS_ID")
    private String advertisementId;

    @Column(name = "BG_COLOR")
    private String backgroundColor;

    @Column(name = "BG_FILE_NAME_V")
    private String portraitBackgroundFilename;

    @Column(name = "BG_SAVE_FILE_NAME_V")
    private String portraitBackgroundSaveFilename;

    @Column(name = "BG_FILE_NAME_H")
    private String landscapeBackgroundFilename;

    @Column(name = "BG_SAVE_FILE_NAME_H")
    private String landscapeBackgroundSaveFilename;

    @Column(name = "ADS_ORDER")
    private Integer advertisementOrder;

    @Column(name = "ADS_DATE_TYPE")
    private String advertisementDateType;

    @Column(name = "FILE_NAME2")
    private String filename2;

    @Column(name = "SAVE_FILE_NAME2")
    private String saveFilename2;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "CHARGE_TYPE")
    private String chargeType;

    @Column(name = "DISPLAY_SERVICE")
    private String displayService;

    @Column(name = "ETC")
    private String etc;

    @Column(name = "ETC2")
    private String etc2;

    @Column(name = "OS_GB")
    private String osGubun;

    @Column(name = "LINK_SERVICE")
    private String linkService;

    @Column(name = "ETC3")
    private String etc3;

    @Column(name = "ETC4")
    private String etc4;

    @Column(name = "AWS_IMG_URL")
    private String awsImageUrl;

    @Column(name = "AWS_IMG_FILENAME")
    private String awsImageFilename;
}
