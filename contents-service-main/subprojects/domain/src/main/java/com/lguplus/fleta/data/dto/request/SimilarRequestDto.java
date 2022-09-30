package com.lguplus.fleta.data.dto.request;

import com.lguplus.fleta.data.dto.SimilarContentDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.PrintStream;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimilarRequestDto extends SimilarContentDto implements Serializable {

    private String albumId;

    private String mPointWatcha;

    private String mRindex;

    private String mRegDate;

    private String mBelongingName;

    private String mLicensingWindowEnd;

    private String mTotalCnt;

    private String mVisitFlag;

    private String similarContent;

    private String mPosterUrl;

    private String mCatId;
}
