package com.lguplus.fleta.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_UX_HV_CONT_EXT", schema = "SMARTUX")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UxHotvodContentExtension {

    @Id
    @Column(name = "CONTENT_ID")
    private String contentId;

    @Column(name = "ALBUM_ID")
    private String albumId;

    @Column(name = "CATEGORY_ID")
    private String categoryId;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    @Column(name = "SITE_ID")
    private String siteId;

    @Column(name = "BADGE_DATA")
    private String badgeData;
}
