package app.backend.staff;

import java.time.LocalDate;
import java.util.Objects;

public class Staff {

    private String id;
    private String fullName;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private String notes;

    private String color;

    public Staff() {
    }

    public Staff(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getColorLabel() {
        return    "<div style=\"background-color:"+color+";\"> selected: <b>"+getFullName()+"</b>  </div>" ;
    }


}
