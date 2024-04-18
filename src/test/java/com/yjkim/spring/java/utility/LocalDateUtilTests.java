package com.yjkim.spring.java.utility;

import com.yjkim.spring.java.utility.data.date.DateConvertUtil;
import com.yjkim.spring.java.utility.data.date.LocalDateTimeUtil;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class LocalDateUtilTests
{
    @Test
    void getDayOfWeekStartBySunTest ()
    {
        Date date = new Date();
        Calendar calendar = DateConvertUtil.convertDateToCalendar(date);
        LocalDate localDate = DateConvertUtil.convertDateToLocalDate(date);

        int calendarWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfWeekStartBySun = LocalDateTimeUtil.getDayOfWeekValueStartBySun(localDate);

        Assertions.assertThat(calendarWeek).isEqualTo(dayOfWeekStartBySun);
    }

    @Test
    void diffTest ()
    {
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2024, 2, 10, 23, 59, 59);

        Assertions.assertThat(LocalDateTimeUtil.diff(ChronoUnit.YEARS, date1, date2)).isEqualTo(1);
        Assertions.assertThat(LocalDateTimeUtil.diff(ChronoUnit.MONTHS, date1, date2)).isEqualTo(1);
        Assertions.assertThat(LocalDateTimeUtil.diff(ChronoUnit.DAYS, date1, date2)).isEqualTo(9);
        Assertions.assertThat(LocalDateTimeUtil.diff(ChronoUnit.HOURS, date1, date2)).isEqualTo(23);
        Assertions.assertThat(LocalDateTimeUtil.diff(ChronoUnit.MINUTES, date1, date2)).isEqualTo(59);
        Assertions.assertThat(LocalDateTimeUtil.diff(ChronoUnit.SECONDS, date1, date2)).isEqualTo(59);
    }

}
