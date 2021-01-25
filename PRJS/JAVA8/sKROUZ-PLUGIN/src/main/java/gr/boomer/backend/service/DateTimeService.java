package gr.boomer.backend.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeService {


    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static  String toText(  LocalDateTime datetime  ) {
        return datetime.format(DateTimeFormatter.ISO_DATE_TIME );
    }

    public static LocalDateTime toLocalDateTime(  String text ) {
        return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME );
    }


}
