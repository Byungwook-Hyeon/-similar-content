package com.lguplus.fleta.provider.jpa;

import com.lguplus.fleta.data.dto.UxHotvodHitcountLogDto;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.stream.Stream;

@Repository
public class UxHotvodHitcountLogJpaEmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Stream<UxHotvodHitcountLogDto> streamAll() {

        return entityManager.createNativeQuery(
                "/* SVC.Contents.UxHotvodHitcountLogJpaEmRepository.streamAll.01 */ " +
                        "SELECT A.HITCNT_YEAR, A.HITCNT_MONTH, A.HITCNT_DAY, A.CONTENT_ID, A.HIT_CNT, A.REG_DT," +
                        "       A.MOD_DT " +
                        "  FROM SMARTUX.PT_UX_HV_HITCNT_LOG A ")
                .setHint(QueryHints.HINT_FETCH_SIZE, 100000)
                .getResultStream()
                .map(value -> {
                    final Object[] array = (Object[])value;
                    return UxHotvodHitcountLogDto.builder()
                            .year((String)array[0])
                            .month((String)array[1])
                            .day((String)array[2])
                            .contentId((String)array[3])
                            .hitCount(((BigDecimal)array[4]).intValue())
                            .registerDate(array[5] == null ? null : ((Timestamp)array[5]).toLocalDateTime())
                            .modifyDate(array[6] == null ? null : ((Timestamp)array[6]).toLocalDateTime())
                            .build();
                });
    }
}
