package com.lasalle.automation.vueling.reservation.domain;

import java.util.Calendar;
import java.util.Date;

public class CalendarDTO {

    public static Date fromString(String dateKey) {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, getNumberOfDays(dateKey));

        return calendar.getTime();
    }

    public static int getNumberOfDays(String dateKey) {
        switch (dateKey){
            case "NEXT_WEEK":
                return 8;
            case "TWO_WEEKS_FROM_NOW":
                return 15;
            case "THREE_WEEKS_FROM_NOW":
                return 20;
        }
        return 0;
    }
}
