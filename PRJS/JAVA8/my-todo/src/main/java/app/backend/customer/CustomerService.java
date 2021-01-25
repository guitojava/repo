package app.backend.customer;

import app.backend.appointment.Appointment;

import java.io.Serializable;
import java.util.List;


public class CustomerService implements Serializable {

    CustomerDao dao = new CustomerDao();

    public List<Customer> selectAll() {
        return dao.selectAll();
    }

    public Customer select(String id) {
        return dao.select(id);
    }

    public boolean save(Customer customer) {
        return dao.save(customer);
    }

    public boolean update(Customer customer) {
        return dao.update(customer);
    }

    public boolean delete(Customer customer) {
        return dao.delete(customer);
    }

}

