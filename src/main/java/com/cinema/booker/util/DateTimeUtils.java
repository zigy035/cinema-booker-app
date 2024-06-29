package com.cinema.booker.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class DateTimeUtils {

    public static LocalDateTime createFromToday(int daysOffset, int hours, int minutes) {
        return LocalDateTime.now()
                .plus(daysOffset, ChronoUnit.DAYS)
                .withHour(hours)
                .withMinute(minutes)
                .withSecond(0)
                .withNano(0);
    }

    public static String getDateAsString(LocalDateTime dateTime) {
        return Optional.ofNullable(dateTime)
                .map(ldt -> ldt.toLocalDate().toString())
                .orElse("null");
    }

    public static String getTimeAsString(LocalDateTime dateTime) {
        return Optional.ofNullable(dateTime)
                .map(ldt -> ldt.toLocalTime().toString())
                .orElse("null");
    }
}
