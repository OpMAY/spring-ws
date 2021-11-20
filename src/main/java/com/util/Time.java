package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.util.Constant.*;

/**
 * Time에 관련된 Class
 */
public class Time {

    /**
     * @return 현재 시간 FORM에 맞춰 리턴
     * "yyyy-MM-dd HH:mm:ss"
     * "yyyy년 MM월dd일 HH시mm분ss초"
     * "yyyy_MM_dd_HH_mm_ss"
     */
    public static String TimeFormatter(String format) {
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

    public static String TimeFormatterLongToString(String format, long time) {
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.KOREA);
        return dateFormat.format(time);
    }

    public static String TimeFormatterStringToString(String from_format, String to_format, String time) throws ParseException {
        DateFormat from_dateFormat = new SimpleDateFormat(from_format);
        Date target_date = from_dateFormat.parse(time);
        DateFormat to_dateFormat = new SimpleDateFormat(to_format, Locale.KOREA);
        return to_dateFormat.format(target_date);
    }

    /**
     * @return 현재 시간 + 1day를 늘려서 Stamp로 리턴
     * 현재 Stamp에서 하루를 늘린 Stamp
     */
    public static Date LongTimeStamp() {
        Date currentDate = new Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.YEAR, 0);
        c.add(Calendar.MONTH, 0);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 0);
        c.add(Calendar.MINUTE, 0);
        c.add(Calendar.SECOND, 0);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return currentDatePlusOne;
    }

    /**
     * @return 현재 시간 + i day를 늘려서 Stamp로 리턴
     * 현재 Stamp에서 i day를 늘린 Stamp
     */
    public static Date LongTimeStamp(int i) {
        Date currentDate = new Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.YEAR, 0);
        c.add(Calendar.MONTH, 0);
        c.add(Calendar.DATE, i); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 0);
        c.add(Calendar.MINUTE, 0);
        c.add(Calendar.SECOND, 0);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return currentDatePlusOne;
    }

    /**
     * @return 임의의 Date에 + i day를 늘려서 Stamp로 리턴
     * 임의의 Stamp에서 i day를 늘린 Stamp
     */
    public static Date LongTimeStamp(Date date, int i) {
        Date currentDate = date;
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.YEAR, 0);
        c.add(Calendar.MONTH, 0);
        c.add(Calendar.DATE, i); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 0);
        c.add(Calendar.MINUTE, 0);
        c.add(Calendar.SECOND, 0);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return currentDatePlusOne;
    }

    /**
     * @return Time에 관한 String을 리턴
     * 받은 long 타입의 시간과 현재 시간을 비교해서 얼마나 차이가 있을까 text로 표현하는 함수
     */
    public static String formatTimePastToString(long regTime) throws ParseException {
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg;
        if (diffTime < SEC) {
            msg = "방금 전";
        } else if ((diffTime /= SEC) < MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= MIN) < HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= HOUR) < DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= DAY) < MONTH) {
            msg = (diffTime) + "달 전";
        } else {
            msg = TimeFormatterLongToString("MM월 dd일(E) a HH:mm", regTime);
        }
        return msg;
    }
}
