package com.yjkim.spring.java.utility.data.date;

import org.apache.commons.lang3.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarUtil
{

    /**
     * Calendar 객체 밀리세컨드 시간 값으로 초기화
     *
     * @param time
     * @return
     */
    public static Calendar initCalendar (long time)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    /**
     * 해당 날짜의 년도
     *
     * @param date
     * @return
     */
    public static int getYear (Calendar date)
    {
        return date.get(Calendar.YEAR);
    }

    /**
     * 해당 날짜의 월
     *
     * @param date
     * @return
     */
    public static int getMonth (Calendar date)
    {

        return date.get(Calendar.MONTH) + 1;
    }

    /**
     * 해당 날짜의 일
     *
     * @param date
     * @return
     */
    public static int getDay (Calendar date)
    {

        return date.get(Calendar.DATE);
    }

    /**
     * 해당 날짜의 시
     *
     * @param date
     * @return
     */
    public static int getHour (Calendar date)
    {

        return date.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 해당 날짜의 분
     *
     * @param date
     * @return
     */
    public static int getMinute (Calendar date)
    {

        return date.get(Calendar.MINUTE);
    }

    /**
     * 해당 날짜의 초
     *
     * @param date
     * @return
     */
    public static int getSecond (Calendar date)
    {

        return date.get(Calendar.SECOND);
    }

    /**
     * 해당 날짜의 밀리초
     *
     * @param date
     * @return
     */
    public static int getMilliSecond (Calendar date)
    {

        return date.get(Calendar.MILLISECOND);
    }

    /**
     * 월을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Calendar yearAdd (Calendar date, int amount)
    {
        date.add(Calendar.YEAR, amount);
        return date;
    }

    /**
     * 년을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Calendar monthAdd (Calendar date, int amount)
    {
        date.add(Calendar.MONTH, amount);
        return date;
    }

    /**
     * 일을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Calendar dayAdd (Calendar date, int amount)
    {
        date.add(Calendar.DATE, amount);
        return date;
    }

    /**
     * 시간을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Calendar hourAdd (Calendar date, int amount)
    {
        date.add(Calendar.HOUR_OF_DAY, amount);
        return date;
    }

    /**
     * 분을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Calendar minuteAdd (Calendar date, int amount)
    {
        date.add(Calendar.MINUTE, amount);
        return date;
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 반환한다.
     *
     * @param value   String
     * @param pattern String
     * @return Date
     * @throws ParseException
     */
    public static Calendar parseDate (String value, String pattern) throws ParseException
    {
        Calendar date = Calendar.getInstance();
        if (value == null || "".equals(value.trim()))
        {
            return date;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        date.setTime(formatter.parse(value));
        return date;
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 세팅한다.
     *
     * @param value   String
     * @param pattern String
     * @param locale  Locale
     * @return Date
     * @throws ParseException
     */
    public static Calendar parseDate (String value, String pattern, Locale locale) throws ParseException
    {
        Calendar date = Calendar.getInstance();
        if (value == null || value.trim().isEmpty())
        {
            return date;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        date.setTime(formatter.parse(value));
        return date;
    }

    /**
     * 날자값을 지정된 패턴으로 출력한다.
     *
     * @param date    Date
     * @param pattern String
     * @return String
     */
    public static String formatDate (Date date, String pattern)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date.getTime());
    }

    /**
     * 날자값을 지정된 패턴으로 출력한다.
     *
     * @param date    Date
     * @param pattern String
     * @param locale  Locale
     * @return String
     */
    public static String formatDate (Calendar date, String pattern, Locale locale)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        return formatter.format(date.getTime());
    }

    /**
     * 지정된 년도와 월의 마지막일을 반환한다.
     *
     * @param year  int
     * @param month int
     * @return int
     */
    public static int getLastDayOfMonth (int year, int month)
    {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, 1);
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        return date.getActualMaximum(Calendar.DATE);
    }

    /**
     * 특정 범위 안에서 {@param minutes}분 단위 Date 객체를 리스트로 구한다.
     *
     * @param start   시작 Date
     * @param end     끝 Date
     * @param minutes 분 단위
     * @return {@param minutes} 단위로 나눈 Date 리스트
     */
    public static List<Calendar> getDatesInMinutes (Calendar start, Calendar end, int minutes)
    {
        long startTime = start.getTimeInMillis();
        long endTime = end.getTimeInMillis();

        long periodTime = (long) minutes * Calendar.MINUTE;

        List<Calendar> listStatDt = new ArrayList<>();
        for (long time = startTime; time <= endTime; time += periodTime)
        {
            listStatDt.add(initCalendar(time));
        }

        return listStatDt;
    }

    /**
     * 특정 년도 {@param weekNum}주차 {@param dayNum}번째 요일 구하기
     *
     * @param year    특정 년도 : null이면 현재 년도에 해당하는 날짜를 구한다.
     * @param weekNum n주차
     * @param dayNum  n번째 요일
     * @return 특정 년도 n주차 n번째 요일에 해당하는 날짜
     */
    public static Calendar getDateByWeekAndDayNum (Integer year, int weekNum, int dayNum)
    {
        if (ObjectUtils.isEmpty(year))
        {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }

        Calendar cal = Calendar.getInstance();
        cal.setWeekDate(year, weekNum, dayNum);
        return cal;
    }

    /**
     * 특정 날짜가 현재 연도의 몇주차인지 구한다.
     *
     * @param date 특정 날짜
     * @return 현재 연도의 해당 주차
     */
    public static Integer getWeekNumByDate (Calendar date)
    {
        if (ObjectUtils.isEmpty(date))
        {
            return null;
        }
        return date.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 지정된 필드의 값에 대한 날짜 차이 값을 구한다.
     *
     * @param field Calendar
     * @param date  Date
     * @return long
     */
    public static long diff (int field, Calendar date)
    {
        return diff(field, Calendar.getInstance(), date);
    }

    /**
     * date1 - date2의 차이 값을 구한다.
     *
     * @param field Calender
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long diff (int field, Calendar date1, Calendar date2)
    {
        long date1Time = date1.getTimeInMillis();
        long date2Time = date2.getTimeInMillis();
        long diffTime = date1Time - date2Time;

        return switch (field)
        {
            case Calendar.MILLISECOND -> diffTime;
            case Calendar.MONTH ->
            {
                int year1 = getYear(date1);
                int year2 = getYear(date2);
                int month1 = getMonth(date1);
                int month2 = getMonth(date2);
                if (year1 == year2)
                {
                    yield month1 - month2;
                }
                yield ((year1 - year2 - 1) * 12L) + (12 - month2) + month1;
            }
            case Calendar.YEAR ->
            {

                int year1 = getYear(date1);
                int year2 = getYear(date2);
                yield year1 - year2;
            }
            default -> diffTime / field;
        };
    }

    /**
     * 문자열 날짜로 만 나이 구하기
     *
     * @param birthDayStr
     * @param pattern
     * @return
     */
    public static int getAge (String birthDayStr, String pattern)
    {
        try
        {
            return getAge(parseDate(birthDayStr, pattern));
        } catch (ParseException e)
        {
            throw new RuntimeException();
        }
    }

    /**
     * Date 객체로 만 나이 구하기
     *
     * @param birthDay
     * @return
     */
    public static int getAge (Calendar birthDay)
    {
        return getAge(getYear(birthDay), getMonth(birthDay), getDay(birthDay));
    }

    /**
     * 년,월,일로 만 나이 구하기
     *
     * @param birthY
     * @param birthM
     * @param birthD
     * @return
     */
    public static int getAge (int birthY, int birthM, int birthD)
    {
        Calendar current = Calendar.getInstance();
        int currentYear = current.get(Calendar.YEAR);
        int currentMonth = current.get(Calendar.MONTH) + 1;
        int currentDay = current.get(Calendar.DAY_OF_MONTH);
        int age = currentYear - birthY;
        if (birthM * 100 + birthD > currentMonth * 100 + currentDay)
        {
            age--;
        }
        return age;
    }
}
