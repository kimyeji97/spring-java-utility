package com.yjkim.spring.java.utility.data.date;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * {@link LocalDate}, {@link LocalTime}와 {@link LocalDateTime}를 다루는 유틸리티
 */
public class LocalDateTimeUtil
{
    public static final ZoneId ZONE_ASIA_SEOUL = ZoneId.of("Asia/Seoul");

    /**
     * 해당 날짜의 Month int 값 반환
     * <pre>
     *     {@link LocalDate}는 {@link Calendar}와 다르게 1월 1 ... 12월 12이다.
     * </pre>
     *
     * @param date
     * @return 1월 0 ... 12월 11
     */
    public static int getMonthValueStartByZero (LocalDate date)
    {
        return date.getMonthValue() - 1;
    }

    /**
     * 해당 날짜의 요일 반환
     * <pre>
     *     {@link LocalDate}는 {@link Calendar}와 다르게 월요일 1 ... 일요일 7이다.
     * </pre>
     *
     * @param date
     * @return 일요일 1 ... 토요일 7
     */
    public static int getDayOfWeekValueStartBySun (LocalDate date)
    {
        return (date.getDayOfWeek().getValue() + 1) % 7;
    }

    /**
     * 타임스템프 시간 값으로 LocalDateTime 객체 반환
     *
     * @param time
     * @return
     */
    public static LocalDateTime getLocalDateTime (long time)
    {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    /**
     * 타밍스템프와 시간 값으로 특정 타임존의 LocalDateTime 객체 반환
     *
     * @param time
     * @param zoneId
     * @return
     */
    public static LocalDateTime getLocalDateTime (long time, ZoneId zoneId)
    {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), zoneId == null ? ZoneId.systemDefault() : zoneId);
    }

    /**
     * 날짜와 시간을 병합
     *
     * @param date
     * @param time
     * @return
     */
    public static LocalDateTime mergeDateAndTime (LocalDate date, LocalTime time)
    {
        return LocalDateTime.of(date, time);
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 반환한다.
     *
     * @param value   String
     * @param pattern String
     * @return LocalDate
     */
    public static LocalDate parseDate (String value, String pattern)
    {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 세팅한다.
     *
     * @param value   String
     * @param pattern String
     * @param locale  Locale
     * @return LocalDate
     */
    public static LocalDate parseDate (String value, String pattern, Locale locale)
    {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 반환한다.
     *
     * @param value   String
     * @param pattern String
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime (String value, String pattern)
    {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 세팅한다.
     *
     * @param value   String
     * @param pattern String
     * @param locale  Locale
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime (String value, String pattern, Locale locale)
    {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 반환한다.
     *
     * @param value   String
     * @param pattern String
     * @return LocalDateTime
     */
    public static LocalTime parseTime (String value, String pattern)
    {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 지정된 문자열의 값을 지정된 패턴으로 해석하여 날자값을 세팅한다.
     *
     * @param value   String
     * @param pattern String
     * @param locale  Locale
     * @return LocalDateTime
     */
    public static LocalTime parseTime (String value, String pattern, Locale locale)
    {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern(pattern, locale));
    }


    /**
     * 날자값을 지정된 패턴으로 출력한다.
     *
     * @param date    Date
     * @param pattern String
     * @return String
     */
    public static String formatDate (LocalDate date, String pattern)
    {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 날자값을 지정된 패턴으로 출력한다.
     *
     * @param date    Date
     * @param pattern String
     * @return String
     */
    public static String formatDate (LocalDateTime date, String pattern)
    {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 시간값을 지정된 패턴으로 출력한다.
     *
     * @param time    time
     * @param pattern String
     * @return String
     */
    public static String formatDate (LocalTime time, String pattern)
    {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 특정 범위 안에서 {@param minutes}분 단위 Date 객체를 리스트로 구한다.
     *
     * @param start   시작 Date
     * @param end     끝 Date
     * @param minutes 분 단위
     * @return {@param minutes} 단위로 나눈 Date 리스트
     */
    public static List<LocalDateTime> getDateTimesInMinutes (LocalDateTime start, LocalDateTime end, int minutes)
    {
        long startTime = Timestamp.valueOf(start).getTime();
        long endTime = Timestamp.valueOf(end).getTime();

        long periodTime = (long) minutes * Calendar.MINUTE;

        List<LocalDateTime> listStatDt = new ArrayList<>();
        for (long time = startTime; time <= endTime; time += periodTime)
        {
            listStatDt.add(getLocalDateTime(time));
        }

        return listStatDt;
    }

    /**
     * LocalDate 객체로 만 나이 구하기
     *
     * @param birthDay
     * @return
     */
    public static int getAge (LocalDate birthDay)
    {
        return getAge(birthDay.getYear(), birthDay.getMonthValue(), birthDay.getDayOfMonth());
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

    /**
     * 두 날짜간의 차이 반환
     *
     * @param field {@link ChronoUnit} 차이 계산할 단위 (연,월,일,시,분,초,나노)
     * @param date1 첫번재 날짜
     * @param date2 두번째 날짜
     * @return
     */
    public static int diff (ChronoUnit field, LocalDateTime date1, LocalDateTime date2)
    {
        return switch (field)
        {
            case YEARS -> Period.between(date1.toLocalDate(), date2.toLocalDate()).getYears();
            case MONTHS -> Period.between(date1.toLocalDate(), date2.toLocalDate()).getMonths();
            case DAYS -> Period.between(date1.toLocalDate(), date2.toLocalDate()).getDays();
            case HOURS -> LocalTime.of(0, 0).plusSeconds(Duration.between(date1, date2).getSeconds()).getHour();
            case MINUTES -> LocalTime.of(0, 0).plusSeconds(Duration.between(date1, date2).getSeconds()).getMinute();
            case SECONDS -> LocalTime.of(0, 0).plusSeconds(Duration.between(date1, date2).getSeconds()).getSecond();
            case NANOS -> LocalTime.of(0, 0).plusSeconds(Duration.between(date1, date2).getSeconds()).getNano();
            default -> throw new IllegalStateException("Unexpected value: " + field);
        };
    }
    
    
    /**
     * 특정 날짜가 두날짜 사이에 있는지 확인
     *
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetween(LocalDateTime target, LocalDateTime start, LocalDateTime end)
    {
        return ( start == null || target.isEqual(start) || target.isAfter(start) )
               && ( end == null || target.isEqual(end) || target.isBefore(end) );
    }
    
    /**
     * 오늘이 두날짜 사이에 있는지 확인
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isNowBetween(LocalDateTime start, LocalDateTime end)
    {
        return LocalDateTimeUtil.isBetween(LocalDateTime.now(), start, end);
    }
}
