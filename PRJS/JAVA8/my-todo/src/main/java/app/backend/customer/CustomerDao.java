package app.backend.customer;

import app.backend.BaseDao;
import app.backend.SystemSettings;
import org.jdbi.v3.core.Handle;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDao extends BaseDao {

    private Map<String, Object> getBindMap(Customer customer) {
        Map<String, Object> item = new HashMap<>();

        item.put("id", customer.getId().trim());
        item.put("fullName", customer.getFullName().trim());
        item.put("phone", customer.getPhone().trim());
        item.put("email", customer.getEmail().trim());
        // !!!  Important Note dates are saves a iso String in UTC Timezone

        if (customer.getDateOfBirth() == null) {
            customer.setDateOfBirth(SystemSettings.EMPTY_DATE);
        }
        item.put("dateOfBirth", customer.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE));

        item.put("notes", customer.getNotes().trim());
        return item;
    }

    boolean save(Customer customer) {
        String sql = "INSERT INTO customer " +
                " ( id, full_name, phone, email, date_of_birth,  notes) " +
                " VALUES (:id, :fullName, :phone, :email, :dateOfBirth, :notes) ";
        Map<String, Object> item = getBindMap(customer);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    boolean update(Customer customer) {
        String sql = "UPDATE customer  SET " +
                " full_name = :fullName , " +
                " phone = :phone, " +
                " email = :email, " +
                " date_of_birth = :dateOfBirth, " +
                " notes = :notes " +
                " WHERE  id = :id";

        Map<String, Object> item = getBindMap(customer);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    boolean delete(Customer customer) {
        String sql = "DELETE FROM customer WHERE  id = :id";
        Map<String, Object> item = getBindMap(customer);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    List<Customer> selectAll() {
        String sql = "SELECT * FROM customer ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .map(new CustomerMapper())
                        .list()
        );

    }

    Customer select(String id) {
        String sql = "SELECT * FROM customer WHERE id = :id ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .map(new CustomerMapper())
                        .first()
        );

    }


}