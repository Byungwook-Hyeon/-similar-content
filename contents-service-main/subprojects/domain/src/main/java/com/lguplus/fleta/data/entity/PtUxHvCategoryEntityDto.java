package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.dto.HotVodRecordDto;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@Table(name = "pt_ux_hv_category", schema="smartux")
public class PtUxHvCategoryEntityDto implements Serializable {

	@Id
	@Column(name= "hv_category_id", nullable = false)
	private String hvCategoryId;

	@Column(name= "parent_id")
	private String parentId;

	@Column(name= "category_info")
	private String categoryInfo;

	@Column(name= "category_name", nullable = false)
	private String categoryName;

	@Column(name= "category_order")
	private String categoryOrder;

	@Column(name= "test_yn", columnDefinition = "'N'::character varying")
	private String testYn;

	@Column(name= "unify_search_yn", columnDefinition = "'N'::character varying")
	private String unifySearchYn;

	@Column(name= "del_yn", columnDefinition = "'N'::character varying")
	private String delYn;

	@Column(name= "reg_dt", nullable = false, columnDefinition = "numeric(1) default timezone(COALESCE(current_setting('aws_oracle_ext.tz'::text, true), 'GMT'::text), clock_timestamp())::timestamp(0) without time zone")
	private String regDt;

	@Column(name= "reg_id", nullable = false)
	private String regId;

	@Column(name= "mod_dt")
	private String modDt;

	@Column(name= "mod_id")
	private String modId;

	@Column(name= "category_img")
	private String categoryImg;

	@Column(name= "category_img_tv")
	private String categoryImgTv;

	@Column(name= "badge_data")
	private String badgeData;

	@Column(name= "service_type")
	private String serviceType;

	@Column(name= "multi_service_type")
	private String multiServiceType;

	public HotVodRecordDto convert() {
		return HotVodRecordDto.builder()
				.rating("01")
				.type("C")
				.regDate(StringUtils.defaultIfEmpty(this.regDt, ""))
				.categoryId(StringUtils.defaultIfEmpty(this.hvCategoryId, ""))
				.categoryName(StringUtils.defaultIfEmpty(this.categoryName, ""))
				.categoryImg(StringUtils.defaultIfEmpty(this.categoryImg, ""))
				.categoryImgTv(StringUtils.defaultIfEmpty(this.categoryImgTv, ""))
//				.contentId("")
//				.contentUrl("")
//				.title("")
				.contentDesc(StringUtils.defaultIfEmpty(this.categoryInfo, ""))
//				.siteLogoUrl("")
//				.siteLogoUrlTv("")
//				.siteName("")
//				.imgUrl("")
//				.imgUrlTv("")
				.hitCnt("0")
//				.duration("")
				.parentCate(StringUtils.defaultIfEmpty(this.parentId, ""))
//				.parentCateName("")
//				.siteId("")
//				.vodAlbumId("")
//				.vodCategoryId("")
//				.startTime("")
//				.endTime("")
//				.contentsName("")
//				.seriesNo("")
//				.seriesYn("")
//				.seriesDesc("")
//				.rowNum(0)
				.badgeData(StringUtils.defaultIfEmpty(this.badgeData, "0"))
				.testYn(this.testYn)
//				.contentOrder(this.categoryOrder)
//				.startDt("")
//				.endDt("")
//				.uiType("")
				.build();


	}
}
