package com.lguplus.fleta;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ExtendWith(SpringExtension.class)
class HotVodUtilsTest {

//    public static String DEFAULT_HOTVOD_BADGE_LIST = "신규|인기|연령1|연령2|연령3|추천1|추천2|추천3|기타1|기타2|기타3";
    public static String DEFAULT_HOTVOD_BADGE_LIST = "신규|인기|연령1|연령2|연령3|추천1|추천2|추천3|기타1|기타2|기타3";
    String hotvodBadgeStr = DEFAULT_HOTVOD_BADGE_LIST;

    List<TestDto> tests = new ArrayList<>();

    @BeforeEach
    void init() {

        TestDto t1 = TestDto.builder().hitCnt("11").regDate("2020-01-22 15:53:10").siteName("Absddg").rowNum("0").build();
        TestDto t2 = TestDto.builder().hitCnt("33").regDate("2019-03-08 16:20:44").siteName("bbsddg").rowNum("3").build();
        TestDto t3 = TestDto.builder().hitCnt("55").regDate("2020-01-22 15:53:45").siteName("Acsddg").rowNum("2").build();
        TestDto t4 = TestDto.builder().hitCnt("77").regDate("2019-03-11 11:25:06").siteName("dbsddg").rowNum("5").build();
        TestDto t5 = TestDto.builder().hitCnt("22").regDate("2020-11-16 09:46:36").siteName("fbsddg").rowNum("4").build();
        tests.add(t1);
        tests.add(t2);
        tests.add(t3);
        tests.add(t4);
        tests.add(t5);

    }

    @Test
    void binaryStr() {

        String value = "33";
        String binaryStr = Integer.toBinaryString(Integer.parseInt(value));
        log.debug("getHotvodList() - {}:{}", "화제 동영상 목록 조회", binaryStr);

        String binaryBadge = setAddstr(binaryStr, hotvodBadgeStr.split("\\|").length);
        log.debug("getHotvodList() - {}:{}-{}", "화제 동영상 목록 조회", binaryBadge, hotvodBadgeStr.split("\\|").length);
        log.debug("getHotvodList() - {}:{}", "화제 동영상 목록 조회", ( new StringBuffer(binaryBadge) ).reverse().toString());
    }


    @Test
    void toBinaryStringTest() {

        String binaryStr = toBinaryString("");
        log.debug("getHotvodList() - {}:{}", "화제 동영상 목록 조회", binaryStr);

        String badgeData = toBinaryString("99");
        log.debug("getHotvodList() - {}:{}", "화제 동영상 목록 조회", badgeData);
        if (badgeData.length() != hotvodBadgeStr.split("\\|").length) {
            badgeData = setAddstr("0", hotvodBadgeStr.split("\\|").length);
        }
        log.debug("getHotvodList() - {}:{}", "화제 동영상 목록 조회", badgeData);

    }

    public String toBinaryString(String value) {
        try {
            String hotvodBadgeStr = DEFAULT_HOTVOD_BADGE_LIST;
            if(StringUtils.isBlank(value)) {
                value = "0";
            }

            String binaryStr = Integer.toBinaryString(Integer.parseInt(value));
            String binaryBadge = setAddstr(binaryStr, hotvodBadgeStr.split("\\|").length);
            return ( new StringBuffer(binaryBadge) ).reverse().toString();
        } catch (Exception e) {
            return setAddstr("0", 11);
        }

    }


    public String setAddstr(String value, int strLen) {
        while (value.length() < strLen) {
            value = "0" + value;
        }
        return value;
    }


    @Test
    void filtering_Test() {
//        Set<String> set = new HashSet<String>();
//        set.add("C");
//        set.add("M");
//        set.add("V");
//        log.debug("filtering_Test() - {}:{}", "화제 동영상 목록 조회", set);

        String type_cate = "C|M|V";
        Set<String> set = Arrays.stream(type_cate.split("\\|"))
                .collect(Collectors.toSet());
        log.debug("filtering_Test() - {}:{}", "화제 동영상 목록 조회", set);

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("type", "A");
        map.put("Name", "A_A");
        list.add(map);
        map = new HashMap<>();
        map.put("type", "C");
        map.put("Name", "C_C");
        list.add(map);
        map = new HashMap<>();
        map.put("type", "C");
        map.put("Name", "C_2");
        list.add(map);
        map = new HashMap<>();
        map.put("type", "M");
        map.put("Name", "M_M");
        list.add(map);
        log.debug("filtering_Test() - {}:{}", "화제 동영상 목록 조회", list);
        List<Map<String, Object>> listOutput =
                list.stream()
                        .filter(e -> set.contains(e.get("type")))
                        .collect(Collectors.toList());

        log.debug("filtering_Test() - {}:{}", "화제 동영상 목록 조회", listOutput);

    }


    @Getter
    @Setter
    @Builder
    public static class TestDto {
        private String hitCnt;
        private String regDate;
        private String siteName;
        private String rowNum;
    }


    @Test
    void whenComparingWithComparator_thenSortedByNameDesc() {

        Comparator<TestDto> hitCompare
                = Comparator.comparing(
                TestDto::getHitCnt, (s1, s2) -> {
                    return s2.compareTo(s1);
                });//.reversed();

        tests.stream().sorted(hitCompare)
                .forEach(
                        record -> {
                            log.debug("getHitCnt() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Comparator<TestDto> regCompare
                = Comparator.comparing(
                TestDto::getRegDate, (s1, s2) -> {
                    return s2.compareTo(s1);
                }).reversed();

        tests.stream().sorted(regCompare)
                .forEach(
                        record -> {
                            log.debug("getRegDate() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Comparator<TestDto> siteCompare
                = Comparator.comparing(
                TestDto::getSiteName, (s1, s2) -> {
                    return s2.compareTo(s1);
                }).reversed();

        tests.stream().sorted(siteCompare)
                .forEach(
                        record -> {
                            log.debug("getSiteName() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Comparator<TestDto> rowCompare
                = Comparator.comparing(
                TestDto::getRowNum, (s1, s2) -> {
                    return s2.compareTo(s1);
                }).reversed();

        tests.stream().sorted(rowCompare)
                .forEach(
                        record -> {
                            log.debug("getRowNum() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });

    }


    private class orderCompare implements Comparator<TestDto> {
        @Override
        public int compare(TestDto arg0, TestDto arg1) {
            return Integer.parseInt(arg1.getRowNum()) > Integer.parseInt(arg0.getRowNum()) ? -1 : Integer.parseInt(arg1.getRowNum()) < Integer.parseInt(arg0.getRowNum()) ? 1 : 0;
        }
    }

    /**
     * 조회수 정렬
     * @author JKJ
     */
    private class hitCompare implements Comparator<TestDto> {
        @Override
        public int compare(TestDto arg0, TestDto arg1) {
            int cnt0 = Integer.parseInt(arg0.getHitCnt());
            int cnt1 = Integer.parseInt(arg1.getHitCnt());
            return cnt0 > cnt1 ? -1 : cnt0 < cnt1 ? 1 : 0;
        }
    }


    /**
     * 등록일 정렬
     * @author JKJ
     */
    private class dateCompare implements Comparator<TestDto> {
        @Override
        public int compare(TestDto arg0, TestDto arg1) {
            return arg1.getRegDate().compareTo(arg0.getRegDate());
        }
    }

    /**
     * 사이트 정렬
     * @author JKJ
     */
    private class siteCompare implements Comparator<TestDto> {
        @Override
        public int compare(TestDto arg0, TestDto arg1) {
            return arg0.getSiteName().compareTo(arg1.getSiteName());
        }
    }



    @Test
    void whenComparingWithComparator_asis_sorted() {

        Collections.sort(tests, new orderCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("orderCompare() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Collections.sort(tests, new hitCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("hitCompare() - {}: record={}", record.getHitCnt(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Collections.sort(tests, new dateCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("dateCompare() - {}: record={}", record.getRegDate(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Collections.sort(tests, new siteCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("siteCompare() - {}: record={}", record.getSiteName(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });

    }


    @Test
    void whenComparingWithComparator_tobe_sorted() {

        Comparator<TestDto> hitCompare = Comparator.comparing(
                TestDto::getHitCnt).reversed();

        Comparator<TestDto> dateCompare = Comparator.comparing(
                TestDto::getRegDate).reversed();

        Comparator<TestDto> rowCompare = Comparator.comparing(
                TestDto::getRowNum);

        Comparator<TestDto> siteCompare = Comparator.comparing(
                TestDto::getSiteName);


        Collections.sort(tests, new orderCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("orderCompare() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });

        tests.stream().sorted(rowCompare)
                .forEach(
                        record -> {
                            log.debug("orderCompare_tobe() - {}: record={}", record.getRowNum(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Collections.sort(tests, new hitCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("hitCompare() - {}: record={}", record.getHitCnt(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });
        tests.stream().sorted(hitCompare)
                .forEach(
                        record -> {
                            log.debug("orderCompare_tobe() - {}: record={}", record.getHitCnt(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });


        Collections.sort(tests, new dateCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("dateCompare() - {}: record={}", record.getRegDate(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });
        tests.stream().sorted(dateCompare)
                .forEach(
                        record -> {
                            log.debug("orderCompare_tobe() - {}: record={}", record.getRegDate(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });

        Collections.sort(tests, new siteCompare());
        tests.stream()
                .forEach(
                        record -> {
                            log.debug("siteCompare() - {}: record={}", record.getSiteName(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });
        tests.stream().sorted(siteCompare)
                .forEach(
                        record -> {
                            log.debug("orderCompare_tobe() - {}: record={}", record.getSiteName(), ToStringBuilder.reflectionToString(record, ToStringStyle.JSON_STYLE));
                        });

    }
}