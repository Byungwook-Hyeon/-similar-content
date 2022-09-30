package com.lguplus.fleta.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NewHotVodInfoDto implements Serializable {

	/**
	 * 시청등급
	 */
	private String rating;
	/**
	 * 타입 - C:카테고리, V:영상, H:하이라이트, B:콘서트, A:방송
	 */
	private String type;
	/**
	 * 등록일
	 */
	private String regDate;
	/**
	 * 해당 카테고리 ID
	 */
	private String categoryId;
	/**
	 * 카테고리 이름
	 */
	private String categoryName;
	/**
	 * 카테고리 이미지(HDTV)
	 */
	@Setter
	private String categoryImg;
	/**
	 * 카테고리 이미지(IPTV)
	 */
	@Setter
	private String categoryImgTv;
	/**
	 * 컨텐츠 ID
	 */
	private String contentId;
	/**
	 * 컨텐츠 URL
	 */
	private String contentUrl;
	/**
	 * 제목
	 */
	private String title;
	/**
	 * 내용
	 */
	private String contentDesc;
	/**
	 * 출처 사이트 로고
	 */
	@Setter
	private String siteLogoUrl;
	/**
	 * 출처 사이트 로고
	 */
	@Setter
	private String siteLogoUrlTv;
	/**
	 * 출처 사이트 명
	 */
	private String siteName;
	/**
	 * 포스터 이미지(HDTV)
	 */
	@Setter
	private String imgUrl;
	/**
	 * 포스터 이미지(IPTV)
	 */
	@Setter
	private String imgUrlTv;
	/**
	 * 조회수
	 */
	private String hitCnt;
	/**
	 * 재생시간
	 */
	private String duration;
	/**
	 * 상위 카테고리
	 */
	private String parentCate;
	/**
	 * 상위 카테고리명
	 */
	private String parentCateName;
	/**
	 * 사이트 아이디
	 */
	private String siteId;
	/**
	 * 영상 앨범 ID
	 */
	private String vodAlbumId;
	/**
	 * 영상 카테고리 ID
	 */
	private String vodCategoryId;
	/**
	 * 영상 시작 시간
	 */
	private String startTime;
	/**
	 * 영상 종료 시간
	 */
	private String endTime;
	/**
	 * 영상 이름
	 */
	private String contentsName;
	/**
	 * 영상 번호
	 */
	private String seriesNo;
	/**
	 * 시리즈 여부
	 */
	private String seriesYn;
	/**
	 * 시리즈 정보
	 */
	private String seriesDesc;
	/**
	 * 뱃지데이터 (신규,인기,연령1,연령2,연령3,추천1,추천2,추천3,기타1,기타2,기타3) 0:FALSE ,1 TRUE
	 */
	@Setter
	private String badgeData;

	private String testYn;
	private String contentOrder;

	/**
	 * 노출시작일
	 */
	private String startDt;
	/**
	 * 노출종료일
	 */
	private String endDt;
	/**
	 * 2019.11.19 브릿지홈 개편 :화제동영상 UI Type추가
	 */
	private String uiType;


	public HotVodRecordDto convert() {
		return HotVodRecordDto.builder()
				.rating(StringUtils.defaultIfEmpty(this.rating, ""))
				.type(StringUtils.defaultIfEmpty(this.type, ""))
				.regDate(StringUtils.defaultIfEmpty(this.regDate, ""))
				.categoryId(StringUtils.defaultIfEmpty(this.categoryId, ""))
				.categoryName(StringUtils.defaultIfEmpty(this.categoryName, ""))
				.categoryImg(StringUtils.defaultIfEmpty(this.categoryImg, ""))
				.categoryImgTv(StringUtils.defaultIfEmpty(this.categoryImgTv, ""))
				.contentId(StringUtils.defaultIfEmpty(this.contentId, ""))
				.contentUrl(StringUtils.defaultIfEmpty(this.contentUrl, ""))
				.title(StringUtils.defaultIfEmpty(this.title, ""))
				.siteLogoUrl(StringUtils.defaultIfEmpty(this.siteLogoUrl, ""))
				.siteLogoUrlTv(StringUtils.defaultIfEmpty(this.siteLogoUrlTv, ""))
				.siteName(StringUtils.defaultIfEmpty(this.siteName, ""))
				.imgUrl(StringUtils.defaultIfEmpty(this.imgUrl, ""))
				.imgUrlTv(StringUtils.defaultIfEmpty(this.imgUrlTv, ""))
				.hitCnt(StringUtils.defaultIfEmpty(this.hitCnt, "0"))
				.duration(StringUtils.defaultIfEmpty(this.duration, ""))
				.siteId(StringUtils.defaultIfEmpty(this.siteId, ""))
				.vodAlbumId(StringUtils.defaultIfEmpty(this.vodAlbumId, ""))
				.vodCategoryId(StringUtils.defaultIfEmpty(this.vodCategoryId, ""))
				.startTime(StringUtils.defaultIfEmpty(this.startTime, ""))
				.endTime(StringUtils.defaultIfEmpty(this.endTime, ""))
				.contentsName(StringUtils.defaultIfEmpty(this.contentsName, ""))
				.seriesNo(StringUtils.defaultIfEmpty(this.seriesNo, ""))
				.sponsorName("")
				.stillImg("")
				.seriesDesc(StringUtils.defaultIfEmpty(this.seriesDesc, ""))
				.seriesYn(StringUtils.defaultIfEmpty(this.seriesYn, ""))
				.genreLarge("")
				.genreMid("")
				.parentCate(StringUtils.defaultIfEmpty(this.parentCate, ""))
				.onairDate("")
//				.rowNum(0)
				.badgeData(this.badgeData)
				.testYn(this.testYn)
//				.contentOrder(this.contentOrder)
				.startDt(this.startDt)
				.endDt(this.endDt)
				.uiType(StringUtils.defaultIfEmpty(this.uiType, ""))
				.parentCateName(StringUtils.defaultIfEmpty(this.parentCateName, ""))
				.contentDesc(StringUtils.defaultIfEmpty(this.contentDesc, ""))
				.build();
	}

}
