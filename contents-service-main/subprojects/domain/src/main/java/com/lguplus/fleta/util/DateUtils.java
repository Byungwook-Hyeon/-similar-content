package com.lguplus.fleta.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class DateUtils {

    private DateUtils() {

        // Do nothing.
    }


    /**
     * date util
     * nDate 와 현재 날짜를 비교하여 년과 일이 현재 날짜보와 같거나 크다면 true 를 돌려준다.
     */
    public static boolean getNewDateCheck(String nDate) {
        boolean returnValue = false;
        if(StringUtils.isBlank(nDate)) {
            return false;
        }

        SimpleDateFormat strSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMdd");
        String localDate = mSimpleDateFormat.format(new Date());
        int compare;

        try {
            Date baseDate = mSimpleDateFormat.parse(localDate);
            Date newDate = strSimpleDateFormat.parse( nDate);

            compare = baseDate.compareTo(newDate);
        } catch (ParseException e) {
            return false;
        }

        if(compare <= 0) {
            returnValue = true;
        }

        return returnValue;
    }


    /**
     * 시작날짜 종료날짜 유효한지 비교하는 함수
     * A == B 이면 0 반환
     * A > B 이면 1 반환
     * A < B dlaus -1 반환
     *
     * 1 today > end
     * 0 today == end
     * -1 today < end
     *
     */
    public static boolean dateCompare(String startDate, String endDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        //노출기간이  없는 컨텐츠의 경우는 skip
        if(StringUtils.isAllBlank(startDate, endDate)) {
            return true;
        }

        String localDate = simpleDateFormat.format(new Date());
        try {
            Date sDate = simpleDateFormat.parse(startDate);
            Date eDate = simpleDateFormat.parse(endDate);
            Date lDate = simpleDateFormat.parse(localDate);

            return sDate.compareTo(lDate) < 0 //sDate 가 과거이면, 0보다 작아야 한다.
                    && eDate.compareTo(lDate) >= 0; //eDate 가 미래이면, +1이거나 0이어야 한다.
        } catch (ParseException e) {
            return false;
        }

    }



    /**
     * 문자열을 받아 pattern에 맞는 유효한 문자열을 반환하는 함수
     */
    public static String strDatePattern(String startDate, String pattern) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( pattern);
        if(StringUtils.isBlank(startDate)) {
            return null;
        }
        try {
            Date sDate = mSimpleDateFormat.parse(startDate);
            return mSimpleDateFormat.format(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 두 SystemDate간의 차이를 초단위로 리턴
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Long dateCompare ( Long date1, Long date2 ) {
        Long diffTime = date1 - date2;
        return diffTime / 1000;
    }

    /**
     * 오늘 날짜를 반환한다. (yyyyMMddHHmmss)
     */
    public static String getTodayFormat() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMMddHHmmss" );
        return sdf.format(dt);
    }


}
