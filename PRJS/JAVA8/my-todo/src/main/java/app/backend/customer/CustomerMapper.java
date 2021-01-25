package app.backend.customer;

import app.backend.SystemSettings;
import app.backend.customer.Customer;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
        var customer = new Customer();
        customer.setId(rs.getString("id"));
        customer.setFullName(rs.getString("full_name"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));

        LocalDate dobFromDB =  LocalDate.parse(rs.getString("date_of_birth"), DateTimeFormatter.ISO_LOCAL_DATE );
        if ( ! dobFromDB.equals(SystemSettings.EMPTY_DATE )){
            customer.setDateOfBirth( dobFromDB);
        }

        customer.setNotes(rs.getString("notes"));
        return customer;
    }
}
