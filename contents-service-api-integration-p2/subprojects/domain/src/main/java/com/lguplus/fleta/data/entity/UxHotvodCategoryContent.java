package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.entity.converter.BooleanToYnConverter;
import com.lguplus.fleta.data.entity.id.UxHotvodCategoryContentId;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PT_UX_HV_CATE_CONTENT", schema = "SMARTUX")
@IdClass(UxHotvodCategoryContentId.class)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UxHotvodCategoryContent {

    @Id
    @Column(name = "HV_CATEGORY_ID")
    private String categoryId;

    @Id
    @Column(name = "CONTENT_ID")
    private String contentId;

    @Column(name = "CONTENT_ORDER")
    private Integer contentOrder;

    @Column(name = "DEL_YN")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean delete;
}
