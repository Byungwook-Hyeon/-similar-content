package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lguplus.fleta.data.dto.response.CommonResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonPropertyOrder({"ads_no", "title", "img_url", "ads_type", "duration", "ads_url", "e_time", "grade", "ev_stat_id",
    "order", "bg_color", "bg_img_url_v", "bg_img_url_h", "img_url2", "etc", "etc2", "trailer_sh", "trailer_hd", "badge"})
public class AdvertisementBannerDto implements PlainTextibleDto, Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String advertisementId;

    @JsonIgnore
    private String startTime;

    @JsonIgnore
    private String dateType; //날짜 타입 (기존:00 / 매일:01)

    @Schema(type = "string", description = "순번: 4<br>자리수: 15<br>설명: 광고 번호", example = "1")
    @JsonProperty("ads_no")
    private String advertisementNo;

    @Schema(type = "string", description = "순번: 5<br>자리수: <br>설명: 제목", example = "")
    @JsonProperty("title")
    private String title;

    @Schema(type = "string", description = "순번: 6<br>자리수: 100<br>설명: 광고 이미지 URL", example = "")
    @JsonProperty("img_url")
    private String imageUrl;

    @Schema(type = "string", description = "순번: 7<br>자리수: 1<br>설명: 광고 타입<br>ex) 1:채널, 2:컨텐츠, 3:카테고리, 4:외부URL, 5:앱링크, 6:신청", example = "2")
    @JsonProperty("ads_type")
    private String advertisementType;

    @Schema(type = "string", description = "순번: 8<br>자리수: 3<br>설명: 광고 노출 시간(초단위)", example = "10")
    @JsonProperty("duration")
    private String duration;

    @Schema(type = "string", description = "순번: 9<br>자리수: 1024<br>설명: 광고 선택시 동작할 페이지/데이터<br>ex) 1:채널 ID, 2:카테고리 ID||앨범 ID||I20/I30||Series YN||Series No, 3:카테고리 ID||I20/I30, 4:외부 URL, 5:링크 주소", example = "5")
    @JsonProperty("ads_url")
    private String advertisementUrl;

    @Schema(type = "string", description = "순번: 10<br>자리수: 14<br>설명: 광고 만료 시간(yyyyMMddHHmmss 포맷, 해당 시간까지 노출)", example = "20281223235900")
    @JsonProperty("e_time")
    private String expiredTime;

    @Schema(type = "string", description = "순번: 11<br>자리수: 2<br>설명: 노출 등급<br>ex) 01:제한 없음, 02:7세 이상, 03:12세 이상, 04:15세 이상, 05:19세 이상", example = "01")
    @JsonProperty("grade")
    private String grade;

    @Schema(type = "string", description = "순번: 12<br>자리수: <br>설명: 이벤트 참여 통계 ID(ID가 있는 경우 이벤트 참여통계 등록 API를 요청한다.)", example = "")
    @JsonProperty("ev_stat_id")
    private String eventId;

    @Schema(type = "string", description = "순번: 13<br>자리수: <br>설명: 순서", example = "")
    @JsonProperty("order")
    private String order;

    @Schema(type = "string", description = "순번: 14<br>자리수: <br>설명: 배경색<br>ex) ffffff, ff0000", example = "ffffff")
    @JsonProperty("bg_color")
    private String backgroundColor;

    @Schema(type = "string", description = "순번: 15<br>자리수: <br>설명: 배경 세로 이미지 URL", example = "")
    @JsonProperty("bg_img_url_v")
    private String verticalBackgroundUrl;

    @Schema(type = "string", description = "순번: 16<br>자리수: <br>설명: 배경 가로 이미지 URL", example = "")
    @JsonProperty("bg_img_url_h")
    private String horizontalBackgroundUrl;

    @Schema(type = "string", description = "순번: 17<br>자리수: <br>설명: 광고 이미지 URL2(신알맹이 이미지)", example = "")
    @JsonProperty("img_url2")
    private String imageUrl2;

    @Schema(type = "string", description = "순번: 18<br>자리수: <br>설명: 추가 내용", example = "")
    @JsonProperty("etc")
    private String etc;

    @Schema(type = "string", description = "순번: 19<br>자리수: <br>설명: 추가 내용2", example = "")
    @JsonProperty("etc2")
    private String etc2;

    @Schema(type = "string", description = "순번: 20<br>자리수: 20<br>설명: 예고편(SH)", example = "M01206F034PPV00SH108.mpg")
    @JsonProperty("trailer_sh")
    private String trailerSh;

    @Schema(type = "string", description = "순번: 21<br>자리수: 20<br>설명: 예고편(HD)", example = "M01206F034PPV00HD108.mpg")
    @JsonProperty("trailer_hd")
    private String trailerHd;

    @Schema(type = "string", description = "순번: 22<br>자리수: 100<br>설명: 신규 뱃지<br>ex) NEW,HOT,ETC (',' 콤마 구분자로 멀티 제공)", example = "NEW,HOT")
    @JsonProperty("badge")
    private String badge;

    public String getAdvertisementId() {
        return StringUtils.defaultString(advertisementId);
    }

    public String getStartTime() {
        return StringUtils.defaultString(startTime);
    }

    public String getDateType() {
        return StringUtils.defaultString(dateType);
    }

    public String getAdvertisementNo() {
        return StringUtils.defaultString(advertisementNo);
    }

    public String getTitle() {
        return StringUtils.defaultString(title);
    }

    public String getImageUrl() {
        return StringUtils.defaultString(imageUrl);
    }

    public String getAdvertisementType() {
        return StringUtils.defaultString(advertisementType);
    }

    public String getDuration() {
        return StringUtils.defaultString(duration);
    }

    public String getAdvertisementUrl() {
        return StringUtils.defaultString(advertisementUrl);
    }

    public String getExpiredTime() {
        return StringUtils.defaultString(expiredTime);
    }

    public String getGrade() {
        return StringUtils.defaultString(grade);
    }

    public String getEventId() {
        return StringUtils.defaultString(eventId);
    }

    public String getOrder() {
        return StringUtils.defaultString(order);
    }

    public String getBackgroundColor() {
        return StringUtils.defaultString(backgroundColor);
    }

    public String getVerticalBackgroundUrl() {
        return StringUtils.defaultString(verticalBackgroundUrl);
    }

    public String getHorizontalBackgroundUrl() {
        return StringUtils.defaultString(horizontalBackgroundUrl);
    }

    public String getImageUrl2() {
        return StringUtils.defaultString(imageUrl2);
    }

    public String getEtc() {
        return etc;
    }

    public String getEtc2() {
        return etc2;
    }

    public String getTrailerSh() {
        return StringUtils.defaultString(trailerSh);
    }

    public String getTrailerHd() {
        return StringUtils.defaultString(trailerHd);
    }

    public String getBadge() {
        return StringUtils.defaultString(badge);
    }

    @Override
    public String toPlainText() {
        return String.join(CommonResponseDto.Separator.COLUMN,
            getAdvertisementNo(), getTitle(), getImageUrl(), getImageUrl(), getAdvertisementType(), getDuration(), getAdvertisementUrl(),
            getExpiredTime(), getGrade(), getEventId(), getOrder(), getBackgroundColor(), getVerticalBackgroundUrl(), getHorizontalBackgroundUrl(),
            getImageUrl2(), getEtc(), getEtc2(), getTrailerSh(), getTrailerHd(), getBadge());
    }
}
