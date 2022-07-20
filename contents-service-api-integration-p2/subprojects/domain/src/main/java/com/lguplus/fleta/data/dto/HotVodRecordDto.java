package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lguplus.fleta.data.dto.response.CommonResponseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@JsonPropertyOrder({
		"type", "reg_date", "category_id", "category_name", "category_img", "category_img_tv",
		"content_id", "content_url", "title", "content_desc", "site_logo_url", "site_logo_url_tv",
		"site_name", "img_url", "img_url_tv", "hit_cnt", "duration", "vod_album_id", "vod_category_id",
		"start_time", "end_time", "contents_name", "series_no", "sponsor_name", "still_img", "series_desc",
		"series_yn", "genre_large", "genre_mid", "parent_cate", "onair_date", "rating","badge_data", "ui_type", "parent_cate_name"
})
public class HotVodRecordDto implements PlainTextibleDto, Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(position=106, dataType="string", value="순번: 6<br>자리수: 1<br>설명: 타입 - C:카테고리, V:영상, H:하이라이트, B:콘서트, A:방송", example="C")
	@JsonProperty("type")
	private String type;

	@ApiModelProperty(position=107, dataType="string", value="순번: 7<br>자리수: <br>설명: 등록일", example="")
	@JsonProperty("reg_date")
	private String regDate;

	@ApiModelProperty(position=108, dataType="string", value="순번: 8<br>자리수: 10<br>설명: 해당 카테고리 ID", example="")
	@JsonProperty("category_id")
	private String categoryId;

	@ApiModelProperty(position=109, dataType="string", value="순번: 9<br>자리수: 50<br>설명: 카테고리 이름", example="")
	@JsonProperty("category_name")
	private String categoryName;

	@ApiModelProperty(position=110, dataType="string", value="순번: 10<br>자리수: 255<br>설명: 카테고리 이미지(HDTV), type이 V인 경우 해당없음", example="")
	@JsonProperty("category_img")
	@Setter
	private String categoryImg;

	@ApiModelProperty(position=111, dataType="string", value="순번: 11<br>자리수: 255<br>설명: 카테고리이미지(IPTV)", example="")
	@JsonProperty("category_img_tv")
	@Setter
	private String categoryImgTv;

	@ApiModelProperty(position=112, dataType="string", value="순번: 12<br>자리수: 10<br>설명: 컨텐츠 ID, type이 V일경우해당", example="")
	@JsonProperty("content_id")
	private String contentId;

	@ApiModelProperty(position=113, dataType="string", value="순번: 13<br>자리수: 255<br>설명: 컨텐츠 URL, type이 V, L일경우해당", example="")
	@JsonProperty("content_url")
	private String contentUrl;

	@ApiModelProperty(position=114, dataType="string", value="순번: 14<br>자리수: 100<br>설명: 제목, type이 V일경우해당", example="")
	@JsonProperty("title")
	private String title;

	@ApiModelProperty(position=115, dataType="string", value="순번: 15<br>자리수: 255<br>설명: 출처사이트로고(HDTV), type이 V일경우해당", example="")
	@JsonProperty("site_logo_url")
	@Setter
	private String siteLogoUrl;

	@ApiModelProperty(position=116, dataType="string", value="순번: 16<br>자리수: 255<br>설명: 출처사이트로고(IPTV), type이 V일경우해당", example="")
	@JsonProperty("site_logo_url_tv")
	@Setter
	private String siteLogoUrlTv;

	@ApiModelProperty(position=117, dataType="string", value="순번: 17<br>자리수: 50<br>설명: 출처사이트명, type이 V일경우해당", example="")
	@JsonProperty("site_name")
	private String siteName;

	@ApiModelProperty(position=118, dataType="string", value="순번: 18<br>자리수: 255<br>설명: 포스터이미지(HDTV), type이 V일경우해당", example="")
	@JsonProperty("img_url")
	@Setter
	private String imgUrl;

	@ApiModelProperty(position=119, dataType="string", value="순번: 19<br>자리수: 255<br>설명: 포스터이미지(IPTV), type이 V일경우해당", example="")
	@JsonProperty("img_url_tv")
	@Setter
	private String imgUrlTv;

	@ApiModelProperty(position=120, dataType="string", value="순번: 20<br>자리수: 10<br>설명: 조회수, type이 V일경우해당", example="")
	@JsonProperty("hit_cnt")
	@Setter
	private String hitCnt;

	@ApiModelProperty(position=121, dataType="string", value="순번: 21<br>자리수: 8<br>설명: 재생시간, type이 V일경우해당 00:00:30", example="")
	@JsonProperty("duration")
	@Setter
	private String duration;

	@JsonIgnore
//	@JsonProperty("site_id")
	private String siteId;

	@ApiModelProperty(position=122, dataType="string", value="순번: 22<br>자리수: 50<br>설명: 영상 앨범 ID, type이 V인 경우 해당없음, type이 M인 경우 앨범ID", example="")
	@JsonProperty("vod_album_id")
	private String vodAlbumId;

	@ApiModelProperty(position=123, dataType="string", value="순번: 23<br>자리수: 50<br>설명: 영상 카테고리 ID, type이 V인 경우 해당없음, type이 N,M인 경우 카테고리 ID", example="")
	@JsonProperty("vod_category_id")
	private String vodCategoryId;

	@ApiModelProperty(position=124, dataType="string", value="순번: 24<br>자리수: 8<br>설명: 시작시간, type이 V인 경우 해당없음", example="")
	@JsonProperty("start_time")
	private String startTime;

	@ApiModelProperty(position=125, dataType="string", value="순번: 25<br>자리수: 8<br>설명: 종료시간, type이 V인 경우 해당없음", example="")
	@JsonProperty("end_time")
	private String endTime;

	@ApiModelProperty(position=126, dataType="string", value="순번: 26<br>자리수: 100<br>설명: 영상 이름, type이 V인 경우 해당없음", example="")
	@JsonProperty("contents_name")
	@Setter
	private String contentsName;

	@ApiModelProperty(position=127, dataType="string", value="순번: 27<br>자리수: 50<br>설명: 시리즈 정보, type이 V인 경우 해당없음, type 이 M인경우 사용", example="")
	@JsonProperty("series_no")
	@Setter
	private String seriesNo;

	@ApiModelProperty(position=128, dataType="string", value="순번: 28<br>자리수: 30<br>설명: 방송사, type이 V인 경우 해당없음", example="")
	@JsonProperty("sponsor_name")
	@Setter
	private String sponsorName;

	@ApiModelProperty(position=129, dataType="string", value="순번: 29<br>자리수: 255<br>설명: 스틸컷, type이 V인 경우 해당없음", example="")
	@JsonProperty("still_img")
	@Setter
	private String stillImg;

	@ApiModelProperty(position=130, dataType="string", value="순번: 30<br>자리수: 20<br>설명: 시리즈 번호, type이 V인 경우 해당없음", example="")
	@JsonProperty("series_desc")
	@Setter
	private String seriesDesc;

	// videolte 고도화 추가
	@ApiModelProperty(position=131, dataType="string", value="순번: 31<br>자리수: <br>설명: 시리즈여부, type이 V인 경우 해당없음, type이 M인경우 사용", example="")
	@JsonProperty("series_yn")
	@Setter
	private String seriesYn;

	@ApiModelProperty(position=132, dataType="string", value="순번: 32<br>자리수: <br>설명: 장르 대분류, type이 V인 경우 해당없음", example="")
	@JsonProperty("genre_large")
	@Setter
	private String genreLarge;

	@ApiModelProperty(position=133, dataType="string", value="순번: 33<br>자리수: <br>설명: 장르 중분류, type이 V인 경우 해당없음", example="")
	@JsonProperty("genre_mid")
	@Setter
	private String genreMid;

	@ApiModelProperty(position=134, dataType="string", value="순번: 34<br>자리수: <br>설명: 상위 카테고리 ID", example="")
	@JsonProperty("parent_cate")
	private String parentCate;

	@ApiModelProperty(position=135, dataType="string", value="순번: 35<br>자리수: 8<br>설명: 방영일, type이 V인 경우 해당없음", example="")
	@JsonProperty("onair_date")
	@Setter
	private String onairDate;

	@ApiModelProperty(position=136, dataType="string", value="순번: 36<br>자리수: 2<br>설명: 연령정보, 01~06", example="")
	@JsonProperty("rating")
	@Setter
	private String rating;

	@JsonIgnore
	@Setter
//	@JsonProperty("row_num")
	private Integer rowNum;

	@ApiModelProperty(position=137, dataType="string", value="순번: 37<br>자리수: 5<br>설명: 뱃지 정보, 순서(신규,인기,연령1,연령2,연령3,추천1,추천2,추천3,기타1,기타2,기타3) 0:FALSE ,1 TRUE EX)00000000000, 00101111000", example="")
	@JsonProperty("badge_data")
	@Setter
	private String badgeData;

	@ApiModelProperty(position=138, dataType="string", value="순번: 38<br>자리수: 5<br>설명: 화제동영상 UI TYPE, Type이 M 또는 N인 경우에만 UI TYPE 존재 Ex) PU004", example="")
	@JsonProperty("ui_type")
	private String uiType;

	@ApiModelProperty(position=139, dataType="string", value="순번: 39<br>자리수: 50<br>설명: 부모 카테고리 명", example="")
	@JsonProperty("parent_cate_name")
	@Setter
	private String parentCateName;

	@ApiModelProperty(position=140, dataType="string", value="순번: 40<br>자리수: <br>설명: 내용", example="")
	@JsonProperty("content_desc")
	private String contentDesc;

	@JsonIgnore
	private String startDt;		//노출시작일

	@JsonIgnore
	private String endDt;			//노출종료일

	@JsonIgnore
	private String testYn;	//상용단말기인 경우 검수여부가 N: 전체 S:상용


	@Override
	public String toPlainText() {
		return String.join(CommonResponseDto.Separator.COLUMN
				, getType()
				, getRegDate()
				, getCategoryId()
				, getCategoryName()
				, getCategoryImg()
				, getCategoryImgTv()
				, getContentId()
				, getContentUrl()
				, getTitle()
				, getSiteLogoUrl()
				, getSiteLogoUrlTv()
				, getSiteName()
				, getImgUrl()
				, getImgUrlTv()
				, getHitCnt()
				, getDuration()
				, getVodAlbumId()
				, getVodCategoryId()
				, getStartTime()
				, getEndTime()
				, getContentsName()
				, getSeriesNo()
				, getSponsorName()
				, getStillImg()
				, getSeriesDesc()
				, getSeriesYn()
				, getGenreLarge()
				, getGenreMid()
				, getParentCate()
				, getOnairDate()
				, getRating()
				, getBadgeData()
				, getUiType()
				, getParentCateName()
				, getContentDesc()
		);
	}
}
