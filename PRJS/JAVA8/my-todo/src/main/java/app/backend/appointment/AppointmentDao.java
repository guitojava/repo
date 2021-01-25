package app.backend.appointment;

import app.backend.BaseDao;
import org.jdbi.v3.core.Handle;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class AppointmentDao extends BaseDao {


    private Map<String, Object> getBindMap(Appointment appointment) {
        Map<String, Object> item = new HashMap<>();
        item.put("id", appointment.getId().trim());
        item.put("title", appointment.getTitle().trim());
        item.put("description", appointment.getDescription().trim());
        item.put("color", appointment.getColor().trim());
        // !!!  Important Note dates are saves a iso String in UTC Timezone
        item.put("startDt", appointment.getStartDt().format(DateTimeFormatter.ISO_DATE_TIME));
        item.put("endDt", appointment.getEndDt().format(DateTimeFormatter.ISO_DATE_TIME));

        item.put("customerId", appointment.getCustomerId());
        item.put("staffId", appointment.getStaffId());

        item.put("netPrice", appointment.getNetPrice());
        item.put("finalPrice", appointment.getFinalPrice());
        return item;
    }

    boolean save(Appointment appointment) {
        if (!validate(appointment)) {
            return false;
        }

        if (exists(appointment.getId())) {
            return update(appointment);
        } else {
            return create(appointment);
        }
    }

    boolean validate(Appointment appointment) {

        Objects.requireNonNull( appointment  );

        if (appointment.getEndDt().isBefore(appointment.getStartDt())) {
            appointment.setError(" Appointment EndDate is before StartDate   ");
            return false;
        }

        if (! appointment.getEndDt().toLocalDate().equals(appointment.getStartDt().toLocalDate())) {
            appointment.setError(" Appointment not on same day  ");
            return false;
        }

        if (appointment.getCustomerId() == null || appointment.getCustomerId().isEmpty() ) {
            appointment.setError(" Appointment needs a customer ");
            return false;
        }


        return true;
    }

    boolean create(Appointment appointment) {
        String sql = "INSERT INTO appointment " +
                " ( id, title, description, color, start_dt, end_dt, customer_id, staff_id, net_price, final_price) " +
                " VALUES (:id, :title, :description, :color, :startDt, :endDt, :customerId, :staffId, :netPrice, :finalPrice) ";
        Map<String, Object> item = getBindMap(appointment);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    boolean update(Appointment appointment) {

        if (!validate(appointment)) {
            return false;
        }


        String sql = "UPDATE appointment  SET " +
                " title = :title , " +
                " description = :description, " +
                " color = :color, " +
                " start_dt = :startDt, " +
                " end_dt = :endDt, " +
                " customer_id = :customerId, " +
                " staff_id = :staffId, " +
                " net_price = :netPrice, " +
                " final_price = :finalPrice " +
                " WHERE  id = :id";

        Map<String, Object> item = getBindMap(appointment);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    boolean delete(Appointment appointment) {
        String sql = "DELETE FROM appointment WHERE  id = :id";
        Map<String, Object> item = getBindMap(appointment);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }


    boolean deleteByCustomerId(String customerId) {
        String sql = "DELETE FROM appointment WHERE customer_id = :customerId";
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bind("customerId",customerId)
                    .execute();
        }
        return true;
    }


    boolean unassignByStaffId(String staffId) {
        String sql = "UPDATE appointment  SET " +
                " staff_id = NULL " +
                " WHERE  id = :id";
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bind("id",staffId)
                    .execute();
        }
        return true;
    }

    List<Appointment> selectAllForCustomer(  String customerId) {
        String sql = "SELECT * FROM appointment WHERE customer_id = :customerId ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("customerId", customerId)
                        .map(new AppointmentMapper())
                        .list()
        );

    }


    List<Appointment> selectAll() {
        String sql = "SELECT * FROM appointment ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .map(new AppointmentMapper())
                        .list()
        );

    }


    Appointment select(String id) {
        String sql = "SELECT * FROM appointment WHERE id = :id ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .map(new AppointmentMapper())
                        .first()
        );
    }

    boolean exists(String id) {
        String sql = "SELECT count(*) FROM appointment WHERE id = :id ";
        Integer found = jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapTo(Integer.class)
                        .first()) ;

        return found > 0  ;
    }


}