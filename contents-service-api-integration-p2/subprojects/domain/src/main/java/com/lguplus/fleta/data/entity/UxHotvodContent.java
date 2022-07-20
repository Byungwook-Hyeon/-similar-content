package com.lguplus.fleta.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_UX_HV_CONT", schema = "SMARTUX")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UxHotvodContent {

    @Id
    @Column(name = "CONTENT_ID")
    private String contentId;

    @Column(name = "CONTENT_NAME")
    private String contentName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "CONTENT_INFO")
    private String contentInfo;

    @Column(name = "CONTENT_IMG")
    private String contentImage;

    @Column(name = "CONTENT_URL")
    private String contentUrl;

    @Column(name = "CONTENT_IMG_TV")
    private String contentTvImage;

    @Column(name = "DURATION")
    private String duration;

    @Column(name = "HIT_CNT")
    private Integer hitCount;

    @Column(name = "REG_DT")
    private LocalDateTime registerDate;

    @Column(name = "REG_ID")
    private String registerer;

    @Column(name = "MOD_DT")
    private LocalDateTime modifyDate;

    @Column(name = "MOD_ID")
    private String modifier;

    @Column(name = "START_DT")
    private LocalDateTime startDate;

    @Column(name = "END_DT")
    private LocalDateTime endDate;

    @Column(name = "HV_UI_TYPE")
    private String uiType;
}
