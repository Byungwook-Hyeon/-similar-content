package com.lguplus.fleta.provider.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
class UxHotvodHitcountLogJpaEmRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private UxHotvodHitcountLogJpaEmRepository uxHotvodHitcountLogJpaEmRepository;

    @BeforeEach
    void setup() {

        final Object[] array1 = new Object[] {
                null, null, null, null, BigDecimal.valueOf(0),
                null, null
        };
        final Object[] array2 = new Object[] {
                null, null, null, null, BigDecimal.valueOf(0),
                new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())
        };
        doReturn(query).when(entityManager).createNativeQuery(any());
        doReturn(query).when(query).setHint(any(), any());
        doReturn(List.of(array1, array2).stream()).when(query).getResultStream();
    }

    @Test
    void test_streamAll() {

        assertDoesNotThrow(() -> uxHotvodHitcountLogJpaEmRepository.streamAll().forEach(value -> {}));
    }
}
