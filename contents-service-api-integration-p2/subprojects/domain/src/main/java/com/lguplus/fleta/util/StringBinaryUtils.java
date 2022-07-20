package com.lguplus.fleta.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 문자열 Case 변환 Util
 * @version 1.0
 */
public class StringBinaryUtils {

    private StringBinaryUtils() {

    }

    /**
     * 길이만큼 문자열을 붙인다
     */
    public static String setRightAddStr(String value, int strLen) {
        final StringBuilder buffer = new StringBuilder(value);
        while (buffer.length() < strLen) {
            buffer.insert(0, "0");
        }
        return buffer.toString();
    }

    /**
     * defaultLen = 11
     * hotvodBadgeStr = DEFAULT_HOTVOD_BADGE_LIST = "신규|인기|연령1|연령2|연령3|추천1|추천2|추천3|기타1|기타2|기타3"
     */
    public static String toBinaryString(String value, String hotvodBadgeStr, int defaultLen) {
        String strData = "";
        try {
            if(StringUtils.isBlank(value)) {
                value = "0";
            }
            String binaryStr = Integer.toBinaryString(Integer.parseInt(value));
            String binaryBadge = setRightAddStr(binaryStr, hotvodBadgeStr.split("\\|").length);
            strData = ( new StringBuffer(binaryBadge) ).reverse().toString();
        } catch (Exception e) {
            strData = setRightAddStr("0", defaultLen);
        }
        if (strData.length() != hotvodBadgeStr.split("\\|").length) {
            return StringBinaryUtils.setRightAddStr("0", hotvodBadgeStr.split("\\|").length);
        } else {
            return strData;
        }

    }
}
