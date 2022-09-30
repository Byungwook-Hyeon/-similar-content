package com.lguplus.fleta.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@JsonPropertyOrder({
        "result_type", "id", "name", "type", "img_url", "img_file_name", "parent_cat_id", "auth_yn",
        "cha_num", "cat_level", "price", "exist_sub_cat", "pr_info", "runtime", "is_51_ch",
        "is_new", "is_update", "is_hot", "is_caption", "is_hd", "is_continous_play",
        "point", "sub_cnt", "close_yn", "svod_yn", "is_3d", "service_gb", "filter_gb",
        "pps_yn", "cat_desc", "is_order", "no_cache", "category_file_name", "ppm_yn", "ppm_prod_id",
        "terr_ch", "overseer_name", "actor", "release_date", "onair_date", "series_desc", "cat_gb",
        "cat_ver", "sample_yn", "sample_cat_id", "vod_server1", "vod_file_name1", "vod_server2",
        "vod_file_name2", "vod_server3", "vod_file_name3", "thumbnail_file_name", "sample_album_id",
        "ser_cat_id", "rear_hd", "series_no", "kids_file_name", "authorize_pview", "link_time", "still_img_url",
        "vr_type", "director", "sub_title", "season_year", "round_nm", "player", "studio", "album_4d_yn", "ufx_yn",
        "genre_large", "genre_mid", "genre_small"
})
public class SimilarContentDto implements Serializable {

    @JsonProperty("result_type")
    private String resultType; // CAT/ALB

    @JsonProperty("id")
    private String ids; // 카테고리/앨범ID

    @JsonProperty("name")
    private String name; // 카테고리/앨범이름

    @JsonProperty("type")
    private String type; // 카테고리 TYPE

    @JsonProperty("img_url")
    private String imgUrl; // 대표이미지 URL

    @JsonProperty("img_file_name")
    private String imgFileName; // 카테고리 패키지 이미지 파일명

    @JsonProperty("parent_cat_id")
    private String parentCatId;  // 상위카테고리 ID(IMCS)

    @JsonProperty("auth_yn")
    private String authYn;  // 카테고리 무조건 연령 인증

    @JsonProperty("cha_num")
    private String chaNum;  // 채널 번호

    @JsonProperty("cat_level")
    private String catLevel;  // 카테고리 레벨 정보

    @JsonProperty("price")
    private String price;  // 유/무료

    @JsonProperty("exist_sub_cat")
    private String existSubCat;  // 하위 카테고리 유무

    @JsonProperty("pr_info")
    private String prInfo;  // 나이제한

    @JsonProperty("runtime")
    private String runtime;  // 상영시간

    @JsonProperty("is_51_ch")
    private String is51Ch;  // 5.1ch

    @JsonProperty("is_new")
    private String isNew;  // 신규 카테고리 등록 여부

    @JsonProperty("is_update")
    private String isUpdate;  // 카테고리의 등록업데이트 여부 (콘텐츠 일경우 N)

    @JsonProperty("is_hot")
    private String isHot;  // HOT 상품 여부

    @JsonProperty("is_caption")
    private String isCaption;  // 자막유무

    @JsonProperty("is_hd")
    private String isHd;  // HD 영상 구분

    @JsonProperty("is_continous_play")
    private String isContinousPlay;  // 컨텐츠 연속 재생 가능 여부

    @JsonProperty("point")
    private String point;  // 평점

    @JsonProperty("sub_cnt")
    private String subCnt;  // 하위 카테고리 및 앨범 개수

    @JsonProperty("close_yn")
    private String closeYn;  // 종영작 여부

    @JsonProperty("svod_yn")
    private String svodYn;  // SVOD여부(하위카테고리 전체가 SVOD)

    @JsonProperty("is_3d")
    private String t3dYn;  // 3D여부

    @JsonProperty("service_gb")
    private String serviceGb;  // 서비스 구분

    @JsonProperty("filter_gb")
    private String filterGb;  // 필터구분

    @JsonProperty("pps_yn")
    private String ppsYn;  // PPS여부(하위카테고리 전체가 PPS임)

    @JsonProperty("cat_desc")
    private String catDesc;  // 카테고리 설명

    @JsonProperty("is_order")
    private String isOrder;  // 카테고리 정렬 기능

    @JsonProperty("no_cache")
    private String noCache;  // 캐쉬파일 사용여부

    @JsonProperty("category_file_name")
    private String categoryFileName;  // 추가 카테고리 이미지

    @JsonProperty("ppm_yn")
    private String ppmYn;  // PPM여부(하위카테고리가 전체가 월정액임)

    @JsonProperty("ppm_prod_id")
    private String ppmProdId;  // 월정액 상품코드

    @JsonProperty("terr_ch")
    private String terrCh;  // 지상파채널

    @JsonProperty("overseer_name")
    private String overseerName;  // 감독

    @JsonProperty("actor")
    private String actor;  // 출연

    @JsonProperty("release_date")
    private String releaseDate;  // 개봉일

    @JsonProperty("onair_date")
    private String onairdate;  // 방영일

    @JsonProperty("series_desc")
    private String seriesDesc;  // 회차 설명

    @JsonProperty("cat_gb")
    private String catGb;  // 카테고리매체구분

    @JsonProperty("cat_ver")
    private String catVer; // 카테고리 버전

    @JsonProperty("sample_yn")
    private String sampleYn;  // 동영상 Thumbnail 여부

    @JsonProperty("sample_cat_id")
    private String sampleCatId;  // 동영상 Thumbnail 카테고리ID

    @JsonProperty("vod_server1")
    private String vodServer1;  // vod서버 1

    @JsonProperty("vod_file_name1")
    private String vodFileName1; // 동영상 Thumbnail 파일명

    @JsonProperty("vod_server2")
    private String vodServer2; // vod서버 2

    @JsonProperty("vod_file_name2")
    private String vodFileName2;  // 동영상 Thumbnail 파일명

    @JsonProperty("vod_server3")
    private String vodServer3;  // vod서버 3

    @JsonProperty("vod_file_name3")
    private String vodFileName3;  // 동영상 Thumbnail 파일명

    @JsonProperty("thumbnail_file_name")
    private String thumbnailFileName;  // 썸네일 이미지 파일명

    @JsonProperty("sample_album_id")
    private String sampleAlbumId;  // 동영상 Thumbnail 앨범ID

//    @JsonProperty("poster_url")
//    private String posterUrl;

    @JsonProperty("ser_cat_id")
    private String serCatId;  // 시리즈 카테고리ID

    @JsonProperty("rear_hd")
    private String rearHd;  // Real HD 여부

    @JsonProperty("series_no")
    private String seriesNo;  // 회차

    @JsonProperty("kids_file_name")
    private String kidsFileName;  // 키즈 카테고리 이미지(FILE1/bFILE2/bFILE3)

    @JsonProperty("authorize_pview")
    private String authorizePView;  // imcs편성(JT005), tv하이라이트(JT004) 일때 미리보기 결과용

    @JsonProperty("link_time")
    private String linkTime;  // 이어보기(JT204)인 경우 시청시간

    @JsonProperty("still_img_url")
    private String stillImgUrl;  // 스틸이미지URL

    @JsonProperty("vr_type")
    private String vrType;  // VR컨텐츠 타입

    @JsonProperty("director")
    private String director;  // 선수명(골프에서만 사용)

    @JsonProperty("sub_title")
    private String subTitle;  // 대회명(골프에서만 사용)

    @JsonProperty("season_year")
    private String seasonYear;  // 대회년도(골프에서만 사용)

    @JsonProperty("round_nm")
    private String roundNm;  // 라운드명(골프에서만 사용)

    @JsonProperty("player")
    private String player;  // 출연자 /골프에서 사용시 (선수명)

    @JsonProperty("studio")
    private String studio;  // 스튜디오 / 골프에서 사용시 (홀정보)

    @JsonProperty("album_4d_yn")
    private String album4dYn;  // 4D VOD 여부

    @JsonProperty("ufx_yn")
    private String ufxYn;  // 영화 월정액 여부 추가

    // 장르 추가
    @JsonProperty("genre_large")
    private String genreLarge;

    @JsonProperty("genre_mid")
    private String genreMid;

    @JsonProperty("genre_small")
    private String genreSmall;

//    @JsonProperty("point_watcha")
//    private String pointWatcha;
//
//    @JsonProperty("rindex")
//    private String rindex;
//
//    @JsonProperty("reg_date")
//    private String regDate;
//
//    @JsonProperty("belonging_name")
//    private String belongingName;
//
//    @JsonProperty("licensing_window_end")
//    private String licensingWindowEnd;
//
//    @JsonProperty("total_cnt")
//    private String totalCnt;
//
//    @JsonProperty("visit_flag")
//    private String visitFlag;
//
//    @JsonProperty("cat_id")
//    private String catId;
}
