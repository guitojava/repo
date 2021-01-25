package app.backend.appointment;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentMapper implements RowMapper<Appointment> {

    @Override
    public Appointment map(ResultSet rs, StatementContext ctx) throws SQLException {
        var appointment = new Appointment();
        appointment.setId(rs.getString("id"));
        appointment.setTitle(rs.getString("title"));
        appointment.setDescription(rs.getString("description"));
        appointment.setColor(rs.getString("color"));
        appointment.setStartDt( LocalDateTime.parse(rs.getString("start_dt"), DateTimeFormatter.ISO_DATE_TIME) );
        appointment.setEndDt( LocalDateTime.parse(rs.getString("end_dt"), DateTimeFormatter.ISO_DATE_TIME) );
        appointment.setCustomerId(rs.getString("customer_id")  );
        appointment.setStaffId(rs.getString("staff_id")  );
        appointment.setNetPrice(rs.getBigDecimal("net_price"));
        appointment.setFinalPrice(rs.getBigDecimal("final_price"));

        return appointment;
    }
}
