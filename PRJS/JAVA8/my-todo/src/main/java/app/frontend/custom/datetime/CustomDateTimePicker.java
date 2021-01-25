package app.frontend.custom.datetime;

import app.backend.SystemSettings;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * see https://vaadin.com/components/vaadin-custom-field/java-examples
 */
public class CustomDateTimePicker extends CustomField<LocalDateTime> {

    private final DatePicker datePicker = new DatePicker();
    private final TimePicker timePicker = new TimePicker();
    private boolean dateOnly;

    public CustomDateTimePicker(String label) {
        setLabel(label);
        datePicker.setLocale( SystemSettings.LOCALE);
        timePicker.setLocale( SystemSettings.LOCALE);
        timePicker.setStep(Duration.ofMinutes(30) );
        timePicker.setClearButtonVisible(true);
        timePicker.setMin("09:00");
        timePicker.setMax("22:00");
        add(datePicker, timePicker);
    }

    @Override
    protected LocalDateTime generateModelValue() {
        final LocalDate date = datePicker.getValue();
        final LocalTime time = timePicker.getValue();

        if (date != null) {
            if (dateOnly || time == null) {
                return date.atStartOfDay();
            }
            return LocalDateTime.of(date, time);
        }
        return null;
    }

    @Override
    protected void setPresentationValue(LocalDateTime newPresentationValue) {
        datePicker.setValue(newPresentationValue != null ? newPresentationValue.toLocalDate() : null);
        timePicker.setValue(newPresentationValue != null ? newPresentationValue.toLocalTime() : null);
    }

    public void setDateOnly(boolean dateOnly) {
        this.dateOnly = dateOnly;
        timePicker.setVisible(!dateOnly);
    }
}