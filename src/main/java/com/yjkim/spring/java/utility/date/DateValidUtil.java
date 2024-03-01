package com.yjkim.spring.java.utility.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 날짜 유효성 유틸리티
 */
public class DateValidUtil
{
    /**
     * 문자열이 해당 날짜 포맷으로 변환가능한지 체크
     *
     * @param value
     * @param format
     * @return
     */
    public static boolean isParseableFormat(String value, String format)
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
}
