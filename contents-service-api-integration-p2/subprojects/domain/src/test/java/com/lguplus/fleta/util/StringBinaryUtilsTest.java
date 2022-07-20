package com.lguplus.fleta.util;

import com.lguplus.fleta.exception.stat.StatNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
class StringBinaryUtilsTest {

    @BeforeEach
    void init() {
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void testSetRightAddStr() {

        String result = StringBinaryUtils.setRightAddStr("0", 10);
        log.info("past result >> {}", result);
        Assertions.assertThat(result).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "", "sss"})
    void testToBinaryString(String str) {
        String hotvodBadgeStr = "신규|인기|연령1|연령2|연령3|추천1|추천2|추천3|기타1|기타2|기타3";

        String result = StringBinaryUtils.toBinaryString(str, hotvodBadgeStr, 10);
        log.info("past result >> {}", result);
        Assertions.assertThat(result).isNotNull();
    }

}