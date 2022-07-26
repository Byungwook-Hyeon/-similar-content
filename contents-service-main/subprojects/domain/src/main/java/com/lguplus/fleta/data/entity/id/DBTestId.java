package com.lguplus.fleta.data.entity.id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DBTestId implements Serializable {

    private String ids;
    private String name = "";
    private String type;
    private String imgFileName;
    private String parentCatId;
    private String authYn;
    private String chaNum;
    private String catLevel;
    private String price;
    private String existSubCat;
    private String prInfo;
    private String runtime;
    private String is51Ch;
    private String isNew;
    private String isUpdate;
    private String isHot;
    private String isCaption;
    private String isHd;
    private String isContinousPlay;
    private String point;
    private String subCnt;
    private String closeYn;
    private String svodYn;
    private String t3dYn;
    private String serviceGb;
    private String filterGb;
    private String ppsYn;
    private String catDesc;
    private String isOrder;
    private String noCache;
    private String categoryFileName;
    private String ppmYn;
    private String ppmProdId;
    private String terrCh;
    private String overseerName;
    private String actor;
    private String releaseDate;
    private String onairdate;
    private String seriesDesc;
    private String catGb;
    private String catVer;
    private String sampleYn;
    private String sampleCatId;
    private String vodServer1;
    private String vodFileName1;
    private String vodServer2;
    private String vodFileName2;
    private String vodServer3;
    private String vodFileName3;
    private String thumbnailFileName;
    private String sampleAlbumId;
    private String posterUrl;
    private String serCatId;
    private String rearHd;
    private String seriesNo;
    private String kidsFileName;
    private String pointWatcha;
    private String rindex;
    private String regDate;
    private String belongingName;
    private String licensingWindowEnd;
    private String totalCnt;
    private String visitFlag;
    private String catId;
    private String vrType;
    private String subTitle;
    private String seasonYear;
    private String roundNm;
    private String director;
    private String player;
    private String studio;
    private String album4dYn;
    private String genreLarge;
    private String genreMid;
    private String genreSmall;
    private String ufxYn;
}

