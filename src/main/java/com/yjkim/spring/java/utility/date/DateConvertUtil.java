package com.yjkim.spring.java.utility.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date 변환 유틸리티
 */
public class DateConvertUtil
{

    /**
     * Date to LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate convertDateToLocalDate(Date date)
    {
        if (date == null)
        {
            return null;
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date to LocalDate with time zone
     *
     * @param date
     * @param timeZone
     * @return
     */
    public static LocalDate convertDateToLocalDate(Date date, TimeZone timeZone)
    {
        if (date == null)
        {
            return null;
        }

        return date.toInstant().atZone(timeZone.toZoneId()).toLocalDate();
    }

    /**
     * Date to LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date)
    {
        if (date == null)
        {
            return null;
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date to LocalDateTime with time zone
     *
     * @param date
     * @param timeZone
     * @return
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date, TimeZone timeZone)
    {
        if (date == null)
        {
            return null;
        }

        return date.toInstant().atZone(timeZone.toZoneId()).toLocalDateTime();
    }

}
