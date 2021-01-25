package app.backend.appointment;

import org.vaadin.stefan.fullcalendar.Entry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class Appointment {

    private String id;
    private String title;
    private String description ;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private String color;

    private String customerId;
    private String staffId;

    private BigDecimal netPrice;
    private BigDecimal finalPrice;


    private String error;


    public Entry toCalenderEntry(){
        Entry entry = new Entry(id, title, startDt, endDt, false, true, color, description);
        return entry;
    }

    public Appointment toAppointment(Entry entry){
        Objects.requireNonNull(entry, "entry");

        Appointment appointment = new Appointment();
        appointment.setId( entry.getId() );
        appointment.setTitle( entry.getTitle());
        appointment.setDescription(entry.getDescription());
        appointment.setColor( entry.getColor() );
        appointment.setStartDt(entry.getStart() );
        appointment.setEndDt(entry.getEnd() );

        return appointment;
    }

    public Appointment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDt() {
        return startDt;
    }

    public void setStartDt(LocalDateTime startDt) {
        this.startDt = startDt;
    }

    public LocalDateTime getEndDt() {
        return endDt;
    }

    public void setEndDt(LocalDateTime endDt) {
        this.endDt = endDt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
