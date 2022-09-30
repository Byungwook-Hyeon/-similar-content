package com.lguplus.fleta.data.entity;

import com.lguplus.fleta.data.entity.id.SimilarContentMetaId;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Table(name = "TB_M_VOD", schema = "SMARTUX")
public class SimilarContentMetaEntity implements Serializable {

    @Id
    @Column(name = "id")
    private String ids;

    @Column(name = "name")
    private String name;

    @Column(name = "typ")
    private String type;

    @Column(name = "img_file_name")
    private String imgFileName;

    @Column(name = "parent_cat_id")
    private String parentCatId;

    @Column(name = "auth_yn")
    private String authYn;

    @Column(name = "cha_num")
    private String chaNum;

    @Column(name = "cat_level")
    private String catLevel;

    @Column(name = "price")
    private String price;

    @Column(name = "exist_sub_cat")
    private String existSubCat;

    @Column(name = "pr_info")
    private String prInfo;

    @Column(name = "runtime")
    private String runtime;

    @Column(name = "is_51_ch")
    private String is51Ch;

    @Column(name = "is_new")
    private String isNew;

    @Column(name = "is_update")
    private String isUpdate;

    @Column(name = "is_hot")
    private String isHot;

    @Column(name = "is_caption")
    private String isCaption;

    @Column(name = "is_hd")
    private String isHd;

    @Column(name = "is_continous_play")
    private String isContinousPlay;

    @Column(name = "point")
    private String point;

    @Column(name = "sub_cnt")
    private String subCnt;

    @Column(name = "close_yn")
    private String closeYn;

    @Column(name = "svod_yn")
    private String svodYn;

    @Column(name = "t3d_yn")
    private String t3dYn;

    @Column(name = "service_gb")
    private String serviceGb;

    @Column(name = "filter_gb")
    private String filterGb;

    @Column(name = "pps_yn")
    private String ppsYn;

    @Column(name = "cat_desc")
    private String catDesc;

    @Column(name = "is_order")
    private String isOrder;

    @Column(name = "no_cache")
    private String noCache;

    @Column(name = "category_file_name")
    private String categoryFileName;

    @Column(name = "ppm_yn")
    private String ppmYn;

    @Column(name = "ppm_prod_id")
    private String ppmProdId;

    @Column(name = "terr_ch")
    private String terrCh;

    @Column(name = "overseer_name")
    private String overseerName;

    @Column(name = "actor")
    private String actor;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "onair_date")
    private String onairdate;

    @Column(name = "series_desc")
    private String seriesDesc;

    @Column(name = "cat_gb")
    private String catGb;

    @Column(name = "cat_ver")
    private String catVer;

    @Column(name = "sample_yn")
    private String sampleYn;

    @Column(name = "sample_cat_id")
    private String sampleCatId;

    @Column(name = "vod_server1")
    private String vodServer1;

    @Column(name = "vod_file_name1")
    private String vodFileName1;

    @Column(name = "vod_server2")
    private String vodServer2;

    @Column(name = "vod_file_name2")
    private String vodFileName2;

    @Column(name = "vod_server3")
    private String vodServer3;

    @Column(name = "vod_file_name3")
    private String vodFileName3;

    @Column(name = "thumbnail_file_name")
    private String thumbnailFileName;

    @Column(name = "sample_album_id")
    private String sampleAlbumId;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "ser_cat_id")
    private String serCatId;

    @Column(name = "rear_hd")
    private String rearHd;

    @Column(name = "series_no")
    private String seriesNo;

    @Column(name = "kids_file_name")
    private String kidsFileName;

    @Column(name = "point_watcha")
    private String pointWatcha;

    @Column(name = "rindex")
    private String rindex;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "belonging_name")
    private String belongingName;

    @Column(name = "licensing_window_end")
    private String licensingWindowEnd;

    @Column(name = "total_cnt")
    private String totalCnt;

    @Column(name = "visit_flag")
    private String visitFlag;

    @Column(name = "cat_id")
    private String catId;

    @Column(name = "vr_type")
    private String vrType;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "season_year")
    private String seasonYear;

    @Column(name = "round_nm")
    private String roundNm;

    @Column(name = "director")
    private String director;

    @Column(name = "player")
    private String player;

    @Column(name = "studio")
    private String studio;

    @Column(name = "album_4d_yn")
    private String album4dYn;

    @Column(name = "genre_large")
    private String genreLarge;

    @Column(name = "genre_mid")
    private String genreMid;

    @Column(name = "genre_small")
    private String genreSmall;

    @Column(name = "ufx_yn")
    private String ufxYn;
}
