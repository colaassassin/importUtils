package com.example.demo.logs;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class PaydayAdjuster implements TemporalAdjuster {
    public Temporal adjustInto(Temporal input) {
        LocalDate date = LocalDate.from(input);
        int day;
        if (date.getDayOfMonth() < 15) {
            day = 15;
        } else {
            day = date.with(TemporalAdjusters.lastDayOfMonth())
                    .getDayOfMonth();
            System.out.println(day);
        }
        date = date.withDayOfMonth(day);
        System.out.println(date);
        System.out.println(date.getDayOfWeek());
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
            System.out.println(date + " " + date.getDayOfWeek());
        }
        return input.with(date);
    }


    @Test
    public void payDay() throws Exception {
        TemporalAdjuster adjuster = new PaydayAdjuster();
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(10, date.with(adjuster).getDayOfMonth()));
        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(31, date.with(adjuster).getDayOfMonth()));
    }
}



