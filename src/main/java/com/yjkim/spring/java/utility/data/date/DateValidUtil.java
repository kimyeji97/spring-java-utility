package com.yjkim.spring.java.utility.data.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * 날짜 유효성 유틸리티
 */
public class DateValidUtil
{
    /**
     * 문자열이 해당 날짜 포맷으로 변환가능한지 체크
     *
     * @param value
     * @param format {@link SimpleDateFormat}의 날짜 포맷
     * @return
     */
    public static boolean isParseableFormat (String value, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(value);
            return true;
        } catch (ParseException ex)
        {
            return false;
        }
    }

    /**
     * 문자열이 해당 날짜시간 포멧으로 변환 가능한지 체크
     *
     * @param value
     * @param format {@link DateTimeFormatter} 날짜시간 포맷
     * @return
     */
    public static boolean isParseableDateTimeFormat (String value, String format)
    {
        try
        {
            DateTimeFormatter.ofPattern(format);
            return true;
        } catch (IllegalArgumentException ex)
        {
            return false;
        }
    }
}
