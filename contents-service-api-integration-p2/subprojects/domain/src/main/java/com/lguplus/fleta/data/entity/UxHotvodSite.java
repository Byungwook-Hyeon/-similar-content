package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.entity.converter.BooleanToYnConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_UX_HV_SITE", schema = "SMARTUX")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UxHotvodSite {

    @Id
    @Column(name = "SITE_ID")
    private String siteId;

    @Column(name = "SITE_NAME")
    private String siteName;

    @Column(name = "SITE_URL")
    private String siteUrl;

    @Column(name = "SITE_IMG")
    private String siteImage;

    @Column(name = "REG_DT")
    private LocalDateTime registerDate;

    @Column(name = "REG_ID")
    private String registerer;

    @Column(name = "MOD_DT")
    private LocalDateTime modifyDate;

    @Column(name = "MOD_ID")
    private String modifier;

    @Column(name = "SITE_IMG_TV")
    private String siteTvImage;

    @Column(name = "DEL_YN")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean delete;
}
