package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lguplus.fleta.data.dto.response.CommonResponseDto;
import com.lguplus.fleta.data.dto.response.CommonResponseDto.Separator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//LatestDto 보고 함 Setter 추가해줌.
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
//public class SimilarContentDto implements Serializable, PlainTextibleDto {
public class SimilarContentDto implements Serializable {

    @ApiModelProperty(position = 1, value = "순번: 1<br><br>설명: 카테고리/앨범ID", example = "M012237150PPV00")
    @JsonProperty("id")
    private String id;

    @ApiModelProperty(position = 2, value = "순번: 2<br><br>설명: 카테고리/앨범이름", example = "(6/7) 비디오포털 테스트 배포 2회")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(position = 3, value = "순번: 3<br><br>설명: 카테고리Type")
    @JsonProperty("type")
    private String type;

    @ApiModelProperty(position = 4, value = "순번: 4<br><br>카테고리 패키지 이미지 파일명")
    @JsonProperty("img_file_name")
    private String imgFileName;

    @ApiModelProperty(position = 5, value = "순번: 5<br><br>상위카테고리 ID(IMCS)")
    @JsonProperty("parent_cat_id")
    private String parentCatId;

    @ApiModelProperty(position = 6, value = "순번: 6<br><br>카테고리 무조건 연령 인증")
    @JsonProperty("auth_yn")
    private String authYn;

    @ApiModelProperty(position = 7, value = "순번: 7<br><br>채널 번호")
    @JsonProperty("cha_num")
    private String chaNum;

    @ApiModelProperty(position = 8, value = "순번: 8<br><br>카테고리 레벨 정보")
    @JsonProperty("cat_level")
    private String catLevel;

    @ApiModelProperty(position = 9, value = "순번: 9<br><br>유/무료")
    @JsonProperty("price")
    private String price;

    @ApiModelProperty(position = 10, value = "순번: 10<br><br>하위 카테고리 유무")
    @JsonProperty("exist_sub_cat")
    private String existSubCat;

    @ApiModelProperty(position = 11, value = "순번: 11<br><br>나이제한")
    @JsonProperty("pr_info")
    private String prInfo;

    @ApiModelProperty(position = 12, value = "순번: 12<br><br>상영시간")
    @JsonProperty("runtime")
    private String runtime;

    @ApiModelProperty(position = 13, value = "순번: 13<br><br>5.1ch")
    @JsonProperty("is_51_ch")
    private String is51Ch;

    @ApiModelProperty(position = 14, value = "순번: 14<br><br>신규 카테고리 등록 여부")
    @JsonProperty("is_new")
    private String isNew;

    @ApiModelProperty(position = 15, value = "순번: 15<br><br>카테고리의 등록 업데이트 여부 (컨텐츠일 경우 N)")
    @JsonProperty("is_update")
    private String isUpdate;

    @ApiModelProperty(position = 16, value = "순번: 16<br><br>HOT 상품 여부")
    @JsonProperty("is_hot")
    private String isHot;

    @ApiModelProperty(position = 17, value = "순번: 17<br><br>자막유무")
    @JsonProperty("is_caption")
    private String isCaption;

    @ApiModelProperty(position = 18, value = "순번: 18<br><br>HD 영상 구분")
    @JsonProperty("is_hd")
    private String isHd;

    @ApiModelProperty(position = 19, value = "순번: 19<br><br>컨텐츠 연속 재생 가능 여부")
    @JsonProperty("is_continous_play")
    private String isContinuousPlay;

    @ApiModelProperty(position = 20, value = "순번: 20<br><br>평점")
    @JsonProperty("point")
    private String point;

    @ApiModelProperty(position = 21, value = "순번: 21<br><br>하위 카테고리 및 앨범 개수")
    @JsonProperty("sub_cnt")
    private String subCnt;

    @ApiModelProperty(position = 22, value = "순번: 22<br><br>종영작 여부")
    @JsonProperty("close_yn")
    private String closeYn;

    @ApiModelProperty(position = 23, value = "순번: 23<br><br>SVOD여부(하위카테고리 전체가 SVOD임)")
    @JsonProperty("svod_yn")
    private String svodYn;

    @ApiModelProperty(position = 24, value = "순번: 24<br><br>3d 여부")
    @JsonProperty("t3d_yn")
    private String t3dYn;

    @ApiModelProperty(position = 25, value = "순번: 25<br><br>서비스 구분")
    @JsonProperty("service_gb")
    private String serviceGb;

    @ApiModelProperty(position = 26, value = "순번: 26<br><br>필터구분")
    @JsonProperty("filter_gb")
    private String filterGb;

    @ApiModelProperty(position = 27, value = "순번: 27<br><br>PPS여부")
    @JsonProperty("pps_yn")
    private String ppsYn;

    @ApiModelProperty(position = 28, value = "순번: 28<br><br>카테고리 설명")
    @JsonProperty("cat_desc")
    private String catDesc;

    @ApiModelProperty(position = 29, value = "순번: 29<br><br>카테고리 정렬 기능")
    @JsonProperty("is_order")
    private String isOrder;

    @ApiModelProperty(position = 30, value = "순번: 30<br><br>캐쉬파일 사용여부")
    @JsonProperty("no_cache")
    private String noCache;

    @ApiModelProperty(position = 31, value = "순번: 31<br><br>추가 카테고리 이미지")
    @JsonProperty("category_file_name")
    private String categoryFileName;

    @ApiModelProperty(position = 32, value = "순번: 32<br><br>PPM여부 (하위카테고리 전체가 월정액임)")
    @JsonProperty("ppm_yn")
    private String ppmYn;

    @ApiModelProperty(position = 33, value = "순번: 33<br><br>월정액 상품 코드")
    @JsonProperty("ppm_prod_id")
    private String ppmProdId;

    @ApiModelProperty(position = 34, value = "순번: 34<br><br>지상파 채널")
    @JsonProperty("terr_ch")
    private String terrCh;

    @ApiModelProperty(position = 35, value = "순번: 35<br><br>감독")
    @JsonProperty("overseer_name")
    private String overseerName;

    @ApiModelProperty(position = 36, value = "순번: 36<br><br>출연")
    @JsonProperty("actor")
    private String actor;

    @ApiModelProperty(position = 37, value = "순번: 37<br><br>개봉일")
    @JsonProperty("release_date")
    private String releaseDate;

    @ApiModelProperty(position = 38, value = "순번: 38<br><br>방영일")
    @JsonProperty("onair_date")
    private String onairdate;

    @ApiModelProperty(position = 39, value = "순번: 39<br><br>회차 설명")
    @JsonProperty("series_desc")
    private String seriesDesc;

    @ApiModelProperty(position = 40, value = "순번: 40<br><br>카테고리 매체 구분")
    @JsonProperty("cat_gb")
    private String catGb;

    @ApiModelProperty(position = 41, value = "순번: 41<br><br>카테고리 버전")
    @JsonProperty("cat_ver")
    private String catVer;

    @ApiModelProperty(position = 42, value = "순번: 42<br><br>동영상 Thumbnail 여부")
    @JsonProperty("sample_yn")
    private String sampleYn;

    @ApiModelProperty(position = 43, value = "순번: 43<br><br>동영상 Thumbnail 카테고리ID")
    @JsonProperty("sample_cat_id")
    private String sampleCatId;

    @ApiModelProperty(position = 44, value = "순번: 44<br><br>VOD서버 1")
    @JsonProperty("vod_server1")
    private String vodServer1;

    @ApiModelProperty(position = 45, value = "순번: 45<br><br>동영상 Thumbnail 파일명")
    @JsonProperty("vod_file_name1")
    private String vodFileName1;

    @ApiModelProperty(position = 46, value = "순번: 46<br><br>VOD서버 2")
    @JsonProperty("vod_server2")
    private String vodServer2;

    @ApiModelProperty(position = 47, value = "순번: 47<br><br>동영상 Thumbnail 파일명")
    @JsonProperty("vod_file_name2")
    private String vodFileName2;

    @ApiModelProperty(position = 48, value = "순번: 48<br><br>VOD서버 3")
    @JsonProperty("vod_server3")
    private String vodServer3;

    @ApiModelProperty(position = 49, value = "순번: 49<br><br>동영상 Thumbnail 파일명")
    @JsonProperty("vod_file_name3")
    private String vodFileName3;

    @ApiModelProperty(position = 50, value = "순번: 50<br><br>썸네일 이미지 파일명")
    @JsonProperty("thumbnail_file_name")
    private String thumbnailFileName;

    @ApiModelProperty(position = 51, value = "순번: 51<br><br>동영상 Thumbnail 앨범ID")
    @JsonProperty("sample_album_id")
    private String sampleAlbumId;

    @ApiModelProperty(position = 52, value = "순번: 52<br><br>포스터 URL")
    @JsonProperty("poster_url")
    private String posterUrl;

    @ApiModelProperty(position = 53, value = "순번: 53<br><br>시리즈 카테고리ID")
    @JsonProperty("ser_cat_id")
    private String serCatId;

    @ApiModelProperty(position = 54, value = "순번: 54<br><br>Real HD 여부")
    @JsonProperty("rear_hd")
    private String rearHd;

    @ApiModelProperty(position = 55, value = "순번: 55<br><br>회차")
    @JsonProperty("series_no")
    private String seriesNo;

    @ApiModelProperty(position = 56, value = "순번: 56<br><br>키즈 카테고리 이미지(FILE1/bFILE2/bFILE3")
    @JsonProperty("kids_file_name")
    private String kidsFileName;

    @ApiModelProperty(position = 57, value = "순번: 57<br><br>????")
    @JsonProperty("point_watcha")
    private String pointWatcha;

    @ApiModelProperty(position = 58, value = "순번: 58<br><br>????")
    @JsonProperty("rindex")
    private String rindex;

    @ApiModelProperty(position = 59, value = "순번: 59<br><br>")
    @JsonProperty("reg_date")
    private String regDate;

    @ApiModelProperty(position = 60, value = "순번: 60<br><br>????")
    @JsonProperty("belonging_name")
    private String belongingName;

    @ApiModelProperty(position = 61, value = "순번: 61<br><br>????")
    @JsonProperty("licensing_window_end")
    private String licensingWindowEnd;

    @ApiModelProperty(position = 62, value = "순번: 62<br><br>????")
    @JsonProperty("total_cnt")
    private String totalCnt;

    @ApiModelProperty(position = 62, value = "순번: 62<br><br>????")
    @JsonProperty("visit_flag")
    private String visitFlag;

    @ApiModelProperty(position = 63, value = "순번: 63<br><br>???? 시리즈 카테고리ID")
    @JsonProperty("cat_id")
    private String catId;

    @ApiModelProperty(position = 64, value = "순번: 64<br><br>VR컨텐츠 타입")
    @JsonProperty("vr_type")
    private String vrType;

    @ApiModelProperty(position = 65, value = "순번: 65<br><br>대회명(골프에서만 사용)")
    @JsonProperty("sub_title")
    private String subTitle;

    @ApiModelProperty(position = 66, value = "순번: 66<br><br>대회년도(골프에서만 사용)")
    @JsonProperty("season_year")
    private String seasonYear;

    @ApiModelProperty(position = 67, value = "순번: 67<br><br>라운드명(골프에서만 사용)")
    @JsonProperty("round_nm")
    private String roundNm;

    @ApiModelProperty(position = 68, value = "순번: 68<br><br>선수명(골프에서만 사용)")
    @JsonProperty("director")
    private String director;

    @ApiModelProperty(position = 69, value = "순번: 69<br><br>출연자 / 골프에서 사용시 선수명")
    @JsonProperty("player")
    private String player;

    @ApiModelProperty(position = 70, value = "순번: 70<br><br>스튜디오 / 골프에서 사용시 홀정보")
    @JsonProperty("studio")
    private String studio;

    @ApiModelProperty(position = 71, value = "순번: 71<br><br>4D VOD 여부")
    @JsonProperty("album_4d_yn")
    private String album4dYn;

    @ApiModelProperty(position = 72, value = "순번: 72<br><br>장르추가_Large")
    @JsonProperty("genre_large")
    private String genreLarge;

    @ApiModelProperty(position = 73, value = "순번: 73<br><br>Mid")
    @JsonProperty("genre_mid")
    private String genreMid;

    @ApiModelProperty(position = 74, value = "순번: 74<br><br>장르추가_Small")
    @JsonProperty("genre_small")
    private String genreSmall;

    @ApiModelProperty(position = 75, value = "순번: 75<br><br>?? 영화월정액 여부")
    @JsonProperty("ufx_yn")
    private String ufxYn;

//    public static SimilarContentDto from(SimilarContentDto similarContentDto) {
//        return new SimilarContentDto(similarContentDto.getId(), similarContentDto.getName(),
//                similarContentDto.getImgFileName());
//    }

//    @Override
//    public String toPlainText() {
//        return String.join(Separator.COLUMN, getId(), getName(), getImgFileName());
//    }
}
