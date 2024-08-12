package datetimeAPIExamples;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimePractice {
    public void useLocalDate(){
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2002,3,20);
        System.out.println(now + " " + date.getMonth());
//        LocalTime.parse("12-2-2002",)

    }
    public void useLocalTime(){
        LocalTime now = LocalTime.now();
        LocalTime time= LocalTime.of(2,2,2);
        System.out.println(now + " " + time);
        String timeInString = "12:00:00";
        time = LocalTime.parse(timeInString);
        System.out.println(time);
    }
    public void useLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(LocalDateTime.of(LocalDate.now(),LocalTime.now()));
    }
    public void useZonedDateTime(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Istanbul")));
    }
    public void useInstantDurationPeriod(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now());
        Instant stat = Instant.now();
        for(int i=0;i<10000;i++)
            ;
        Instant end = Instant.now();
        Duration d1 = Duration.between(stat,end);
        System.out.println(d1);
        System.out.println(Duration.between(LocalDateTime.now(),LocalDateTime.parse("2007-12-03T10:15:30")));
        System.out.println(Period.between(LocalDate.now(),LocalDate.parse("2002-01-20")));
    }
    public void useDateTimeFormatter(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("20/01/2002", dateTimeFormatter);
        System.out.println(date);

    }
}

