package com.lguplus.fleta.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
class DateUtilsTest {

    @Test
    void testGetNewDateCheck_past() {
        String nDate = "";
        boolean result = DateUtils.getNewDateCheck(nDate);
        Assertions.assertThat(result).isFalse();

        nDate = "2015-02-28 00:00:00";
        result = DateUtils.getNewDateCheck(nDate);
        log.info("past result >> {}", result);
        Assertions.assertThat(result).isFalse();

    }

    @Test
    void testGetNewDateCheck_future() {

        String nDate = "2030-02-28 17:00:00";
        boolean result = DateUtils.getNewDateCheck(nDate);
        log.info("future result >> {}", result);
        Assertions.assertThat(result).isTrue();

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String curDt = mSimpleDateFormat.format(new Date());
        result = DateUtils.getNewDateCheck(curDt);
        log.info("current result >> {}", result);
        Assertions.assertThat(result).isTrue();

    }

    @Test
    void testGetNewDateCheck_ex() {
        String nDate = "";
        boolean result = DateUtils.getNewDateCheck(nDate);
        Assertions.assertThat(result).isFalse();

        nDate = "sdfsfsdfd";
        //mSimpleDateFormat.parse( nDate)
        result = DateUtils.getNewDateCheck(nDate);
        Assertions.assertThat(result).isFalse();

        result = DateUtils.getNewDateCheck(null);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void testDateCompare_null_ex() {

        String sDate = "";
        String eDate = "";
        boolean result = DateUtils.dateCompare(sDate, eDate);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void testDateCompare_past_ex() {
        String sDate = "2015-02-28 00:00:00";
        String eDate = "2015-02-28 00:00:00";

        boolean result = DateUtils.dateCompare(sDate, eDate);
        log.info("past result >> {}", result);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void testDateCompare_future_ex() {
        String sDate = "2030-02-28 00:00:00";
        String eDate = "2030-02-28 00:00:00";

        boolean result = DateUtils.dateCompare(sDate, eDate);
        log.info("future result >> {}", result);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void testDateCompare_current() {
        String sDate = "2015-02-28 00:00:00";
        String eDate = "2030-02-28 00:00:00";

        boolean result = DateUtils.dateCompare(sDate, eDate);
        log.info("current result >> {}", result);
        Assertions.assertThat(result).isTrue();
    }


    @Test
    void testDateCompare_format_ex() {
        String sDate = "sdfsfsdfd";
        String eDate = "sdfsfsdfd";

        boolean result = DateUtils.dateCompare(sDate, eDate);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void testStrDatePattern() {
        String sDate = "2021-05-04 11:22:08";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        String result = DateUtils.strDatePattern(sDate, pattern);
        Assertions.assertThat(result).isEqualTo("2021-05-04 11:22:08");
    }

    @Test
    void testStrDatePattern_null() {
        String pattern = "yyyy-MM-dd HH:mm:ss";

        String result = DateUtils.strDatePattern(null, pattern);
        Assertions.assertThat(result).isNull();
    }

    @Test
    void testStrDatePattern_paser() {
        String sDate = "202105asd041122";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        String result = DateUtils.strDatePattern(sDate, pattern);
        Assertions.assertThat(result).isNull();
    }


    @Test
    void testDateCompare() {
        Long systemTime = System.currentTimeMillis();
        Long useTime = System.currentTimeMillis() - 10;

        Long result = DateUtils.dateCompare(systemTime, useTime);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void testGetTodayFormat() {
        String result = DateUtils.getTodayFormat();
        Assertions.assertThat(result).isNotNull();
    }


}