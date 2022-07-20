package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.entity.converter.BooleanToYnConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_UX_HV_CATEGORY", schema = "SMARTUX")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UxHotvodCategory {

    @Id
    @Column(name = "HV_CATEGORY_ID")
    private String categoryId;

    @Column(name = "PARENT_ID")
    private String parentCategoryId;

    @Column(name = "CATEGORY_INFO")
    private String categoryInfo;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CATEGORY_ORDER")
    private Integer categoryOrder;

    @Column(name = "TEST_YN")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean test;

    @Column(name = "UNIFY_SEARCH_YN")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean unifySearch;

    @Column(name = "DEL_YN")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean delete;

    @Column(name = "REG_DT")
    private LocalDateTime registerDate;

    @Column(name = "REG_ID")
    private String registerer;

    @Column(name = "MOD_DT")
    private LocalDateTime modifyDate;

    @Column(name = "MOD_ID")
    private String modifier;

    @Column(name = "CATEGORY_IMG")
    private String categoryImage;

    @Column(name = "CATEGORY_IMG_TV")
    private String categoryTvImage;

    @Column(name = "BADGE_DATA")
    private String badgeData;

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "MULTI_SERVICE_TYPE")
    private String multiServiceType;
}
