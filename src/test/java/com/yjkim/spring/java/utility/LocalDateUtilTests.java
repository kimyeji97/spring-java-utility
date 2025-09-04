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

}
