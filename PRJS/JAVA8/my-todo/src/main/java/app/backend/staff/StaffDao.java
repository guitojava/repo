package app.backend.staff;

import app.backend.BaseDao;
import app.backend.SystemSettings;
import org.jdbi.v3.core.Handle;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StaffDao extends BaseDao {

    private Map<String, Object> getBindMap(Staff staff) {
        Map<String, Object> item = new HashMap<>();

        item.put("id", staff.getId().trim());
        item.put("fullName", staff.getFullName().trim());
        item.put("phone", staff.getPhone().trim());
        item.put("email", staff.getEmail().trim());
        item.put("color", staff.getColor().trim());

        // !!!  Important Note dates are saves a iso String in UTC Timezone

        if (staff.getDateOfBirth() == null) {
            staff.setDateOfBirth(SystemSettings.EMPTY_DATE);
        }
        item.put("dateOfBirth", staff.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE));

        item.put("notes", staff.getNotes());
        return item;
    }

    boolean save(Staff staff) {
        String sql = "INSERT INTO staff " +
                " ( id, full_name, phone, email, color, date_of_birth,  notes) " +
                " VALUES (:id, :fullName, :phone, :email, :color, :dateOfBirth, :notes) ";
        Map<String, Object> item = getBindMap(staff);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    boolean update(Staff staff) {
        String sql = "UPDATE staff  SET " +
                " full_name = :fullName , " +
                " phone = :phone, " +
                " email = :email, " +
                " color = :color, " +
                " date_of_birth = :dateOfBirth, " +
                " notes = :notes " +
                " WHERE  id = :id";

        Map<String, Object> item = getBindMap(staff);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    boolean delete(Staff staff) {
        String sql = "DELETE FROM staff WHERE  id = :id";
        Map<String, Object> item = getBindMap(staff);
        try (Handle handle = jdbi.open()) {
            handle.createUpdate(sql)
                    .bindMap(item)
                    .execute();
        }
        return true;
    }

    List<Staff> selectAll() {
        String sql = "SELECT * FROM staff ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .map(new StaffMapper())
                        .list()
        );

    }

    Staff select(String id) {
        String sql = "SELECT * FROM staff WHERE id = :id ";
        Optional<Staff>   staffOptional =   jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .map(new StaffMapper())
                        .findOne());

        if (  staffOptional.isPresent()){
            return staffOptional.get();
        }
        return null;
    }


    boolean exists(String id) {
        String sql = "SELECT count(*) FROM staff WHERE id = :id ";
        Integer found = jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapTo(Integer.class)
                        .first()) ;
        return found > 0  ;
    }

}