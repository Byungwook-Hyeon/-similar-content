package com.lguplus.fleta.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_HDTV_ADS_MASTER", schema = "SMARTUX")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class HdtvAdvertisementMaster {

    @Id
    @Column(name = "ADS_ID")
    private String advertisementId;

    @Column(name = "ADS_NM")
    private String advertisementName;

    @Column(name = "LIVE_CNT")
    private int liveCount;

    @Column(name = "REG_DT")
    private LocalDateTime registerDate;

    @Column(name = "MOD_DT")
    private LocalDateTime modifyDate;

    @Column(name = "REG_ID")
    private String registerer;

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "IMG_RATIO")
    private String imageRatio;
}
