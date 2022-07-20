package com.lguplus.fleta.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class AdvertisementBannerEntityDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ADS_ID")
    private String advertisementId;
    
    @Column(name="S_TIME")
    private String startTime;
    
    @Column(name="DATE_TYPE")
    private String dateType;
    
    @Id
    @Column(name="ADS_NO")
    private String advertisementNo;
    
    @Column(name="TITLE")
    private String title;

    @Setter
    @Column(name="IMAGE_URL")
    private String imageUrl;
    
    @Column(name="ADS_TYPE")
    private String advertisementType;
    
    @Column(name="DURATION")
    private String duration;
    
    @Column(name="ADS_URL")
    private String advertisementUrl;
    
    @Setter
    @Column(name="E_TIME")
    private String expiredTime;
    
    @Column(name="GRADE")
    private String grade;
    
    @Column(name="EVENT_ID")
    private String eventId;
    
    @Setter
    @Column(name="ADS_ORDER")
    private String order;
    
    @Column(name="BG_COLOR")
    private String backgroundColor;
    
    @Setter
    @Column(name="VERTICAL_BG_URL")
    private String verticalBackgroundUrl;
    
    @Setter
    @Column(name="HORIZONTAL_BG_URL")
    private String horizontalBackgroundUrl;
    
    @Setter
    @Column(name="IMAGE_URL2")
    private String imageUrl2;
    
    @Column(name="ETC")
    private String etc;
    
    @Column(name="ETC2")
    private String etc2;
    
    @Setter
    @Transient
    private String trailerSh;
    
    @Setter
    @Transient
    private String trailerHd;
    
    @Setter
    @Transient
    private String badge;
    
    @Setter
    @Transient
    private String albumId;
}

