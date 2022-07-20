package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.HdtvAdvertisementMasterLogDto;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.stream.Stream;

@Repository
public class HdtvAdvertisementMasterLogJpaEmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Stream<HdtvAdvertisementMasterLogDto> streamAll() {

        return entityManager.createNativeQuery(
                "/* SVC.Contents.HdtvAdvertisementMasterLogJpaEmRepository.streamAll.01 */ " +
                        "SELECT A.ADS_ID, A.ADS_NM, A.LIVE_CNT, A.REG_DT, A.ACT_DT, A.ACT_ID, A.ACT_IP, A.ACT_GB, " +
                        "       A.MOD_DT, A.REG_ID, A.SERVICE_TYPE " +
                        "  FROM SMARTUX.PT_HDTV_ADS_MASTER_LOG A ")
                .setHint(QueryHints.HINT_FETCH_SIZE, 100000)
                .getResultStream()
                .map(value -> {
                    final Object[] array = (Object[])value;
                    return HdtvAdvertisementMasterLogDto.builder()
                            .advertisementId((String)array[0])
                            .advertisementName((String)array[1])
                            .liveCount(((BigDecimal)array[2]).intValue())
                            .registerDate(array[3] == null ? null : ((Timestamp)array[3]).toLocalDateTime())
                            .actionDate(array[4] == null ? null : ((Timestamp)array[4]).toLocalDateTime())
                            .actor((String)array[5])
                            .actorIp((String)array[6])
                            .actionGubun((String)array[7])
                            .modifyDate(array[8] == null ? null : ((Timestamp)array[8]).toLocalDateTime())
                            .registerer((String)array[9])
                            .serviceType((Character)array[10])
                            .build();
                });
    }
}
