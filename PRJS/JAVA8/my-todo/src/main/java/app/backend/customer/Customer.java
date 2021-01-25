package app.backend.customer;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {

    private String id;
    private String fullName;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private String notes;


    public Customer(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle(){

        if (  getDateOfBirth() != null ) {
            return getFullName() + " (" + getDateOfBirth().toString()  +  ")     [ " + getPhone() + " ]    " + email;
        }else {
            return getFullName() + "  [  "+ getPhone()  + "  ]   " +  email ;
        }


    }


    public Customer() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(fullName, customer.fullName) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth) &&
                Objects.equals(notes, customer.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, phone, email, dateOfBirth, notes);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", notes='" + notes + '\'' +
                '}';
    }
}
