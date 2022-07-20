package com.lguplus.fleta.data.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.lguplus.fleta.data.entity.id.SimilarContentId;
import com.lguplus.fleta.data.entity.id.SimilarMetaId;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Entity
@Table(name = "VODUSER.TB_M_VOD")
@IdClass(SimilarMetaId.class)
public class SimilarMetaEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "typ")
    private String type;

    @Id
    @Column(name = "img_file_name")
    private String imgFileName;

    @Id
    @Column(name = "parent_cat_id")
    private String parentCatId;

    @Id
    @Column(name = "auth_yn")
    private String authYn;

    @Id
    @Column(name = "cha_num")
    private String chaNum;

    @Id
    @Column(name = "cat_level")
    private String catLevel;

    @Id
    @Column(name = "price")
    private String price;

    @Id
    @Column(name = "exist_sub_cat")
    private String existSubCat;

    @Id
    @Column(name = "pr_info")
    private String prInfo;

    @Id
    @Column(name = "runtime")
    private String runtime;

    @Id
    @Column(name = "is_51_ch")
    private String is51Ch;

    @Id
    @Column(name = "is_new")
    private String isNew;

    @Id
    @Column(name = "is_update")
    private String isUpdate;

    @Id
    @Column(name = "is_hot")
    private String isHot;

    @Id
    @Column(name = "is_caption")
    private String isCaption;

    @Id
    @Column(name = "is_hd")
    private String isHd;

    @Id
    @Column(name = "is_continous_play")
    private String isContinuousPlay;

    @Id
    @Column(name = "point")
    private String point;

    @Id
    @Column(name = "sub_cnt")
    private String subCnt;

    @Id
    @Column(name = "close_yn")
    private String closeYn;

    @Id
    @Column(name = "svod_yn")
    private String svodYn;

    @Id
    @Column(name = "t3d_yn")
    private String t3dYn;

    @Id
    @Column(name = "service_gb")
    private String serviceGb;

    @Id
    @Column(name = "filter_gb")
    private String filterGb;

    @Id
    @Column(name = "pps_yn")
    private String ppsYn;

    @Id
    @Column(name = "cat_desc")
    private String catDesc;

    @Id
    @Column(name = "is_order")
    private String isOrder;

    @Id
    @Column(name = "no_cache")
    private String noCache;

    @Id
    @Column(name = "category_file_name")
    private String categoryFileName;

    @Id
    @Column(name = "ppm_yn")
    private String ppmYn;

    @Id
    @Column(name = "ppm_prod_id")
    private String ppmProdId;

    @Id
    @Column(name = "terr_ch")
    private String terrCh;

    @Id
    @Column(name = "overseer_name")
    private String overseerName;

    @Id
    @Column(name = "actor")
    private String actor;

    @Id
    @Column(name = "release_date")
    private String releaseDate;

    @Id
    @Column(name = "onair_date")
    private String onairdate;

    @Id
    @Column(name = "series_desc")
    private String seriesDesc;

    @Id
    @Column(name = "cat_gb")
    private String catGb;

    @Id
    @Column(name = "cat_ver")
    private String catVer;

    @Id
    @Column(name = "sample_yn")
    private String sampleYn;

    @Id
    @Column(name = "sample_cat_id")
    private String sampleCatId;

    @Id
    @Column(name = "vod_server1")
    private String vodServer1;

    @Id
    @Column(name = "vod_file_name1")
    private String vodFileName1;

    @Id
    @Column(name = "vod_server2")
    private String vodServer2;

    @Id
    @Column(name = "vod_file_name2")
    private String vodFileName2;

    @Id
    @Column(name = "vod_server3")
    private String vodServer3;

    @Id
    @Column(name = "vod_file_name3")
    private String vodFileName3;

    @Id
    @Column(name = "thumbnail_file_name")
    private String thumbnailFileName;

    @Id
    @Column(name = "sample_album_id")
    private String sampleAlbumId;

    @Id
    @Column(name = "poster_url")
    private String posterUrl;

    @Id
    @Column(name = "ser_cat_id")
    private String serCatId;

    @Id
    @Column(name = "rear_hd")
    private String rearHd;

    @Id
    @Column(name = "series_no")
    private String seriesNo;

    @Id
    @Column(name = "kids_file_name")
    private String kidsFileName;

    @Id
    @Column(name = "point_watcha")
    private String pointWatcha;

    @Id
    @Column(name = "rindex")
    private String rindex;

    @Id
    @Column(name = "reg_date")
    private String regDate;

    @Id
    @Column(name = "belonging_name")
    private String belongingName;

    @Id
    @Column(name = "licensing_window_end")
    private String licensingWindowEnd;

    @Id
    @Column(name = "total_cnt")
    private String totalCnt;

    @Id
    @Column(name = "visit_flag")
    private String visitFlag;

    @Id
    @Column(name = "cat_id")
    private String catId;

    @Id
    @Column(name = "vr_type")
    private String vrType;

    @Id
    @Column(name = "sub_title")
    private String subTitle;

    @Id
    @Column(name = "season_year")
    private String seasonYear;

    @Id
    @Column(name = "round_nm")
    private String roundNm;

    @Id
    @Column(name = "director")
    private String director;

    @Id
    @Column(name = "player")
    private String player;

    @Id
    @Column(name = "studio")
    private String studio;

    @Id
    @Column(name = "album_4d_yn")
    private String album4dYn;

    @Id
    @Column(name = "genre_large")
    private String genreLarge;

    @Id
    @Column(name = "genre_mid")
    private String genreMid;

    @Id
    @Column(name = "genre_small")
    private String genreSmall;

    @Id
    @Column(name = "ufx_yn")
    private String ufxYn;
}
