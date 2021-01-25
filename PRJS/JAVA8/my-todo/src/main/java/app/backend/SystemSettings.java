package app.backend;

import org.vaadin.stefan.fullcalendar.Timezone;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

public class SystemSettings {
     public final static Locale LOCALE = new Locale("el", "GR");
     public final static Timezone TIMEZONE = new Timezone(ZoneId.of("UTC") );
     public final static LocalDate EMPTY_DATE = LocalDate.MIN;

}
