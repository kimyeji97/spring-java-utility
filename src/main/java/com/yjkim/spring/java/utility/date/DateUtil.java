package com.yjkim.spring.java.utility.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Date 유틸리티
 *
 * import 라이브러리
 *    - https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
 */
public class DateUtil
{
    /**
     * 해당 날짜의 년도
     *
     * @param date
     * @return
     */
    public static int getYear(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.YEAR);
    }

    /**
     * 해당 날짜의 월
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.MONTH) + 1;
    }

    /**
     * 해당 날짜의 일
     *
     * @param date
     * @return
     */
    public static int getDay(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.DATE);
    }

    /**
     * 해당 날짜의 시
     *
     * @param date
     * @return
     */
    public static int getHour(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 해당 날짜의 분
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.MINUTE);
    }

    /**
     * 해당 날짜의 초
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.SECOND);
    }

    /**
     * 해당 날짜의 밀리초
     *
     * @param date
     * @return
     */
    public static int getMilliSecond(Date date)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        return tcal.get(Calendar.MILLISECOND);
    }

    /**
     * 월을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Date yearAdd(Date date, int amount)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        tcal.add(Calendar.YEAR, amount);
        return tcal.getTime();
    }

    /**
     * 년을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Date monthAdd(Date date, int amount)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        tcal.add(Calendar.MONTH, amount);
        return tcal.getTime();
    }

    /**
     * 일을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Date dayAdd(Date date, int amount)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        tcal.add(Calendar.DATE, amount);
        return tcal.getTime();
    }

    /**
     * 시간을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Date hourAdd(Date date, int amount)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        tcal.add(Calendar.HOUR_OF_DAY, amount);
        return tcal.getTime();
    }

    /**
     * 분을 지정된 양만큼 더하거나 뺀다.
     *
     * @param amount int
     * @return DateUtil
     */
    public static Date minuteAdd(Date date, int amount)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        tcal.add(Calendar.MINUTE, amount);
        return tcal.getTime();
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 반환한다.
     *
     * @param value   String
     * @param pattern String
     * @return Date
     * @throws ParseException
     */
    public static Date parseDate(String value, String pattern) throws ParseException
    {
        Calendar tcal = Calendar.getInstance();
        if (value == null || "".equals(value.trim()))
        {
            return tcal.getTime();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        tcal.setTime(formatter.parse(value));
        return tcal.getTime();
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
    public static Date parseDate(String value, String pattern, Locale locale) throws ParseException
    {
        Calendar tcal = Calendar.getInstance();
        if (value == null || "".equals(value.trim()))
        {
            return tcal.getTime();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        tcal.setTime(formatter.parse(value));
        return tcal.getTime();
    }

    /**
     * 날자값을 지정된 패턴으로 출력한다.
     *
     * @param date    Date
     * @param pattern String
     * @return String
     */
    public static String formatDate(Date date, String pattern)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(tcal.getTime());
    }

    /**
     * 날자값을 지정된 패턴으로 출력한다.
     *
     * @param date    Date
     * @param pattern String
     * @param locale  Locale
     * @return String
     */
    public static String formatDate(Date date, String pattern, Locale locale)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        return formatter.format(tcal.getTime());
    }

    /**
     * 지정된 년도와 월의 마지막일을 반환한다.
     *
     * @param year  int
     * @param month int
     * @return int
     */
    public static int getLastDayOfMonth(int year, int month)
    {
        Calendar tcal = Calendar.getInstance();
        tcal.set(Calendar.DATE, 1);
        tcal.set(Calendar.YEAR, year);
        tcal.set(Calendar.MONTH, month - 1);
        return tcal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 특정 범위 안에서 {@param minutes}분 단위 Date 객체를 리스트로 구한다.
     *
     * @param start   시작 Date
     * @param end     끝 Date
     * @param minutes 분 단위
     * @return {@param minutes} 단위로 나눈 Date 리스트
     */
    public static List<Date> getDatesInMinutes(Date start, Date end, int minutes)
    {
        long startTime = start.getTime();
        long endTime = end.getTime();

        long periodTime = minutes * Calendar.MINUTE;

        List<Date> listStatDt = new ArrayList<>();
        for (long time = startTime; time <= endTime; time += periodTime)
        {
            listStatDt.add(new Date(time));
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
    public static Date getDateByWeekAndDayNum(Integer year, int weekNum, int dayNum)
    {

        if (ObjectUtils.isEmpty(year))
        {
            year = DateUtils.toCalendar(new Date()).get(Calendar.YEAR);
        }

        Calendar cal = Calendar.getInstance();
        cal.setWeekDate(year, weekNum, dayNum);

        return cal.getTime();
    }

    /**
     * 특정 날짜가 현재 연도의 몇주차인지 구한다.
     *
     * @param date 특정 날짜
     * @return 현재 연도의 해당 주차
     */
    public static Integer getWeekNumByDate(Date date)
    {
        if (ObjectUtils.isEmpty(date))
        {
            return null;
        }

        Calendar cal = DateUtils.toCalendar(date);

        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 지정된 필드의 값에 대한 날짜 차이 값을 구한다.
     *
     * @param field Calendar
     * @param date  Date
     * @return long
     */
    public static long diff(int field, Date date)
    {
        return DateUtil.diff(field, new Date(), date);
    }

    /**
     * date1 - date2의 차이 값을 구한다.
     *
     * @param field Calender
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long diff(int field, Date date1, Date date2)
    {
        long date1Time = DateUtils.truncate(date1, field).getTime();
        long date2Time = DateUtils.truncate(date2, field).getTime();
        long diffTime = date1Time - date2Time;

        return switch (field)
            {
                case Calendar.MILLISECOND -> diffTime;
                case Calendar.MONTH ->
                {
                    int year1 = DateUtil.getYear(date1);
                    int year2 = DateUtil.getYear(date2);
                    int month1 = DateUtil.getMonth(date1);
                    int month2 = DateUtil.getMonth(date2);
                    if (year1 == year2)
                    {
                        yield month1 - month2;
                    }
                    yield ((year1 - year2 - 1) * 12L) + (12 - month2) + month1;
                }
                case Calendar.YEAR ->
                {

                    int year1 = DateUtil.getYear(date1);
                    int year2 = DateUtil.getYear(date2);
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
    public static int getAge(String birthDayStr, String pattern)
    {
        try
        {
            return DateUtil.getAge(DateUtils.parseDate(birthDayStr, pattern));
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
    public static int getAge(Date birthDay)
    {
        return DateUtil.getAge(DateConvertUtil.convertDateToLocalDate(birthDay));
    }

    /**
     * LocalDate 객체로 만 나이 구하기
     *
     * @param birthDay
     * @return
     */
    public static int getAge(LocalDate birthDay)
    {
        return DateUtil.getAge(birthDay.getYear(), birthDay.getMonthValue(), birthDay.getDayOfMonth());
    }

    /**
     * 년,월,일로 만 나이 구하기
     *
     * @param birthY
     * @param birthM
     * @param birthD
     * @return
     */
    public static int getAge(int birthY, int birthM, int birthD)
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
