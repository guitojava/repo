package app.backend.staff;

import app.backend.SystemSettings;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StaffMapper implements RowMapper<Staff> {

    @Override
    public Staff map(ResultSet rs, StatementContext ctx) throws SQLException {
        var staff = new Staff();
        staff.setId(rs.getString("id"));
        staff.setFullName(rs.getString("full_name"));
        staff.setPhone(rs.getString("phone"));
        staff.setEmail(rs.getString("email"));
        staff.setColor(rs.getString("color"));
        LocalDate dobFromDB = LocalDate.parse(rs.getString("date_of_birth"), DateTimeFormatter.ISO_LOCAL_DATE);
        if (!dobFromDB.equals(SystemSettings.EMPTY_DATE)) {
            staff.setDateOfBirth(dobFromDB);
        }

        staff.setNotes(rs.getString("notes"));
        return staff;
    }
}
