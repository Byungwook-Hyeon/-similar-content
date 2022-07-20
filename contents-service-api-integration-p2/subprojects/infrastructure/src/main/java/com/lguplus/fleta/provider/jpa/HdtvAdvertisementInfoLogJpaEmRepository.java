package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.HdtvAdvertisementInfoLogDto;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Repository
public class HdtvAdvertisementInfoLogJpaEmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Stream<HdtvAdvertisementInfoLogDto> streamAll() {

        return entityManager.createNativeQuery(
                "/* SVC.Contents.HdtvAdvertisementInfoLogJpaEmRepository.streamAll.01 */ " +
                        "SELECT A.ADS_NO, A.TITLE, A.ROL_TIME, A.ADS_TYPE, A.FILE_NAME, A.SAVE_FILE_NAME, " +
                        "       A.LINK_URL, A.S_TIME, A.E_TIME, A.R_ID, A.R_TIME, A.LIVE_YN, A.GRADE, A.ACT_DT, " +
                        "       A.ACT_ID, A.ACT_IP, A.ACT_GBN, A.EV_STAT_ID, A.ADS_ID, A.BG_COLOR, A.BG_FILE_NAME_V, " +
                        "       A.BG_SAVE_FILE_NAME_V, A.BG_FILE_NAME_H, A.BG_SAVE_FILE_NAME_H, A.ADS_ORDER, " +
                        "       A.ADS_DATE_TYPE, A.FILE_NAME2, A.SAVE_FILE_NAME2, A.PRODUCT_CODE, A.CHARGE_TYPE, " +
                        "       A.DISPLAY_SERVICE, A.ETC, A.ETC2, A.OS_GB, A.LINK_SERVICE, A.ETC3, A.ETC4, " +
                        "       A.AWS_IMG_URL, A.AWS_IMG_FILENAME " +
                        "  FROM SMARTUX.PT_HDTV_ADS_INFO_LOG A ")
                .setHint(QueryHints.HINT_FETCH_SIZE, 100000)
                .getResultStream()
                .map(value -> {
                    final Object[] array = (Object[])value;
                    return HdtvAdvertisementInfoLogDto.builder()
                            .advertisementNumber(((BigDecimal)array[0]).intValue())
                            .title((String)array[1])
                            .rollingTime(array[2] == null ? null : ((BigDecimal)array[2]).intValue())
                            .advertisementType(array[3] == null ? null : ((BigDecimal)array[3]).intValue())
                            .filename((String)array[4])
                            .saveFilename((String)array[5])
                            .linkUrl((String)array[6])
                            .startTime(toLocalDateTime((Timestamp)array[7]))
                            .endTime(toLocalDateTime((Timestamp)array[8]))
                            .registerer((String)array[9])
                            .registerTime(toLocalDateTime((Timestamp)array[10]))
                            .live("Y".equals(array[11]))
                            .grade((String)array[12])
                            .actionDate(toLocalDateTime((Timestamp)array[13]))
                            .actor((String)array[14])
                            .actorIp((String)array[15])
                            .actionGubun((String)array[16])
                            .eventStatisticsId(array[17] == null ? null : ((BigDecimal)array[17]).intValue())
                            .advertisementId((String)array[18])
                            .backgroundColor((String)array[19])
                            .portraitBackgroundFilename((String)array[20])
                            .portraitBackgroundSaveFilename((String)array[21])
                            .landscapeBackgroundFilename((String)array[22])
                            .landscapeBackgroundSaveFilename((String)array[23])
                            .advertisementOrder(array[24] == null ? null : ((BigDecimal)array[24]).intValue())
                            .advertisementDateType((String)array[25])
                            .filename2((String)array[26])
                            .saveFilename2((String)array[27])
                            .productCode((String)array[28])
                            .chargeType((String)array[29])
                            .displayService((String)array[30])
                            .etc((String)array[31])
                            .etc2((String)array[32])
                            .osGubun((String)array[33])
                            .linkService((String)array[34])
                            .etc3((String)array[35])
                            .etc4((String)array[36])
                            .awsImageUrl((String)array[37])
                            .awsImageFilename((String)array[38])
                            .build();
                });
    }

    private LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
