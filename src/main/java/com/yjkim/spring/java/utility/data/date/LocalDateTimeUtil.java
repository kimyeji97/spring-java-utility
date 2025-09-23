package com.yjkim.spring.java.utility.data.date;

import com.yjkim.spring.java.utility.data.number.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

/**
 * {@link LocalDate}, {@link LocalTime}와 {@link LocalDateTime}를 다루는 유틸리티
 */
public class LocalDateTimeUtil
{
    public static final ZoneId ZONE_ASIA_SEOUL = ZoneId.of("Asia/Seoul");
    
    public static long getCurrentTimestamp()
    {
        return Timestamp.valueOf(LocalDateTime.now()).getTime();
    }
    
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
     * @param patterns String
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime (String value, String... patterns)
    {
        RuntimeException exception = null;
        for (String pattern : patterns)
        {
            try
            {
                return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
            } catch (RuntimeException e)
            {
                exception = e;
            }
        }
        if (exception != null)
        {
            throw exception;
        }
        return LocalDateTime.now();
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
     * 두 날짜 간의 차이를 지정된 ChronoUnit 단위로 반환하며, 지정된 모드에 따라 하위 단위를 처리한다.
     *
     * @param field ChronoUnit 차이 계산 단위 (연, 월, 일, 시, 분, 초, 나노초 등)
     * @param date1 첫 번째 날짜
     * @param date2 두 번째 날짜
     * @param mode  반올림 모드 (ROUND, CEIL, FLOOR)
     * @return date2 - date1의 차이를 field 단위로 처리하여 반환 (int)
     *
     * @throws IllegalArgumentException date1, date2, field, mode가 null이거나 지원되지 않는 ChronoUnit
     */
    public static int diff(ChronoUnit field, LocalDateTime date1, LocalDateTime date2, RoundingMode mode)
    {
        if (date1 == null || date2 == null || field == null || mode == null) {
            throw new IllegalArgumentException("date1, date2, field, and mode must not be null");
        }
        
        return switch (field) {
            case YEARS -> {
                Period period = Period.between(date1.toLocalDate(), date2.toLocalDate());
                int years = period.getYears();
                int months = period.getMonths();
                BigDecimal value = new BigDecimal(years).add(new BigDecimal(months).divide(new BigDecimal(12), 10, RoundingMode.HALF_UP));
                yield NumberUtil.scale(value, 1, 0, mode).intValue();
            }
            case MONTHS -> {
                Period period = Period.between(date1.toLocalDate(), date2.toLocalDate());
                int months = period.getYears() * 12 + period.getMonths();
                int days = period.getDays();
                BigDecimal value = new BigDecimal(months).add(new BigDecimal(days).divide(new BigDecimal(30), 10, RoundingMode.HALF_UP));
                yield NumberUtil.scale(value, 1, 0, mode).intValue();
            }
            case DAYS -> {
                Duration duration = Duration.between(date1, date2);
                long days = duration.toDays();
                long hours = duration.minusDays(days).toHours();
                BigDecimal value = new BigDecimal(days).add(new BigDecimal(hours).divide(new BigDecimal(24), 10, RoundingMode.HALF_UP));
                yield NumberUtil.scale(value, 1, 0, mode).intValue();
            }
            case HOURS -> {
                Duration duration = Duration.between(date1, date2);
                long hours = duration.toHours();
                long minutes = duration.minusHours(hours).toMinutes();
                BigDecimal value = new BigDecimal(hours).add(new BigDecimal(minutes).divide(new BigDecimal(60), 10, RoundingMode.HALF_UP));
                yield NumberUtil.scale(value, 1, 0, mode).intValue();
            }
            case MINUTES -> {
                Duration duration = Duration.between(date1, date2);
                long minutes = duration.toMinutes();
                long seconds = duration.minusMinutes(minutes).getSeconds();
                BigDecimal value = new BigDecimal(minutes).add(new BigDecimal(seconds).divide(new BigDecimal(60), 10, RoundingMode.HALF_UP));
                yield NumberUtil.scale(value, 1, 0, mode).intValue();
            }
            case SECONDS -> {
                Duration duration = Duration.between(date1, date2);
                long seconds = duration.getSeconds();
                long nanos = duration.minusSeconds(seconds).toNanos();
                BigDecimal value = new BigDecimal(seconds).add(new BigDecimal(nanos).divide(new BigDecimal(1_000_000_000), 10, RoundingMode.HALF_UP));
                yield NumberUtil.scale(value, 1, 0, mode).intValue();
            }
            case NANOS -> (int) Duration.between(date1, date2).toNanos(); // 나노초 이하 단위 없으므로 반올림 불필요
            default -> throw new IllegalArgumentException("Unsupported ChronoUnit: " + field);
        };
    }
    
    /**
     * 가변 목록에서 가장 작은 날짜/시간 반환
     *
     * @param dateTimes
     * @return
     * @param <T>
     */
    public static <T extends Temporal & Comparable<? super T>> T  findMin(T... dateTimes)
    {
        return LocalDateTimeUtil.findMin(List.of(dateTimes));
    }
    
    /**
     * 리스트에서 가장 작은 날짜/시간 반환
     *
     * @param list
     * @return
     * @param <T>
     */
    public static <T extends Temporal & Comparable<? super T>> T  findMin(List<T> list)
    {
        if (list == null || list.isEmpty()) {
            return null;
        }
        
        return list.stream()
                .filter(Objects::nonNull)
                .min(T::compareTo)
                .orElse(null);
    }
    
    
    
    /**
     * 가변 목록에서 가장 큰 날짜/시간 반환
     *
     * @param dateTimes
     * @return
     * @param <T>
     */
    public static <T extends Temporal & Comparable<? super T>> T  findMax(T... dateTimes)
    {
        return LocalDateTimeUtil.findMax(List.of(dateTimes));
    }
    
    /**
     * 리스트에서 가장 큰 날짜/시간 반환
     *
     * @param list
     * @return
     * @param <T>
     */
    public static <T extends Temporal & Comparable<? super T>> T  findMax(List<T> list)
    {
        if (list == null || list.isEmpty()) {
            return null;
        }
        
        return list.stream()
                .filter(Objects::nonNull)
                .max(T::compareTo)
                .orElse(null);
    }
    
    
    /**
     * 특정 날짜가 임계값 범위 여부 (미만/초과)
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenExclusive(LocalDateTime target, LocalDateTime start, LocalDateTime end)
    {
        return (start == null || target.isAfter(start))
               && (end == null || target.isBefore(end));
    }
    
    /**
     * 특정 날짜가 임계값 범위 여부 (미만/초과)
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenExclusive(LocalDate target, LocalDate start, LocalDate end)
    {
        return (start == null || target.isAfter(start))
               && (end == null || target.isBefore(end));
    }
    
    /**
     * 특정 날짜가 임계값 범위 여부 (미만/초과)
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenExclusive(LocalTime target, LocalTime start, LocalTime end)
    {
        return (start == null || target.isAfter(start))
               && (end == null || target.isBefore(end));
    }
    
    /**
     * 특정 날짜가 두날짜 사이에 있는지 확인 (이상/이하)
     *
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenInclusive(LocalDateTime target, LocalDateTime start, LocalDateTime end)
    {
        return (start == null || target.isEqual(start) || target.isAfter(start))
               && (end == null || target.isEqual(end) || target.isBefore(end));
    }
    
    
    /**
     * 특정 날짜가 두날짜 사이에 있는지 확인 (이상/이하)
     *
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenInclusive(LocalDate target, LocalDate start, LocalDate end)
    {
        return (start == null || target.isEqual(start) || target.isAfter(start))
               && (end == null || target.isEqual(end) || target.isBefore(end));
    }
    
    /**
     * 특정 시각이 두 시각 사이에 있는지 확인 (이상/이하)
     *
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenInclusive(LocalTime target, LocalTime start, LocalTime end) {
        
        return (start == null || target.compareTo(start) == 0 || target.isAfter(start))
               && (end == null || target.compareTo(end) == 0 || target.isBefore(end));
    }
    
    
    /**
     * 오늘이 두날짜 사이에 있는지 확인
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isNowBetweenInclusive(LocalDateTime start, LocalDateTime end)
    {
        return LocalDateTimeUtil.isBetweenInclusive(LocalDateTime.now(), start, end);
    }
    
    
    /**
     * 현재 시각이 시각이 두 시각 사이에 있는지 확인
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isNowBetweenInclusive(LocalTime start, LocalTime end)
    {
        return LocalDateTimeUtil.isBetweenInclusive(LocalTime.now(), start, end);
    }
    
    
    public static LocalDateTime atEndOfDay(LocalDateTime day) {
        LocalDate date = day.toLocalDate();
        return date.atTime(23, 59, 59, 999_999_999); // 23:59:59.999999999
    }
    
    public static LocalDateTime atStartOfDay(LocalDateTime day) {
        LocalDate date = day.toLocalDate();
        return date.atTime(0, 0, 0, 0); // 00:00:00.0
    }
}
