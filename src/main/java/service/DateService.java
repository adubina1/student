package service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateService {

    /**
     * Public holidays.
     */
    private static final Set<LocalDate> HOLIDAYS;

    static {
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 1, 2),
                LocalDate.of(2022, 1, 7),
                LocalDate.of(2022, 3, 8),
                LocalDate.of(2022, 6, 27),
                LocalDate.of(2022, 8, 24),
                LocalDate.of(2022, 12, 25),
                LocalDate.of(2022, 12, 31)
        );
        HOLIDAYS = Collections.unmodifiableSet(new HashSet<>(dates));
    }

    /**
     * Method determines if a date is a business day.
     */
    public static boolean isBusinessDay(LocalDate date) {
        LocalDate d = date;
        DayOfWeek dw = d.getDayOfWeek();
        if (!HOLIDAYS.contains(d)
                && dw != DayOfWeek.SATURDAY
                && dw != DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    public static String dateDifference(LocalDateTime finishTime) {
        String day = "";
        String dateDifferenceText = "";
        LocalDateTime currentTime = LocalDateTime.now();

        LocalDate date1 = LocalDate.now();
        LocalDate date2 = finishTime.toLocalDate();

        int time1 = LocalTime.now().getHour();
        int time2 = finishTime.toLocalTime().getHour();

        if (currentTime.isEqual(finishTime)) {
            dateDifferenceText = "Training is finished right now.";

        } else if (currentTime.isBefore(finishTime)) {
            int days = Period.between(date1, date2).getDays();
            if (days != 0) {
                day = days + " d ";
            }
            int hours;
            if (days != 0) {
                hours = time2 - 10;
            } else if (time2 > time1) {
                hours = time2 - time1;
            } else {
                hours = time1 - time2;
            }
            dateDifferenceText = "Training is not finished. " + day + hourOrHours(hours) + " are left until the end.";

        } else if (currentTime.isAfter(finishTime)) {
            int days = Math.abs((int) ChronoUnit.DAYS.between(date1, date2));
            if (days != 0) {
                day = days + " d ";
            }
            int hours = Math.abs(time2 - time1);
            dateDifferenceText = "Training completed. " + day + hourOrHours(hours) + " have passed since the end.";

        }
        System.out.println(dateDifferenceText);
        return dateDifferenceText;
    }

    private static String hourOrHours(int hours) {
        String hour = null;
        if (hours > 1) {
            hour = hours + " hours";
        } else if (hours == 1) {
            hour = hours + " hour";
        }
        return hour;
    }
}