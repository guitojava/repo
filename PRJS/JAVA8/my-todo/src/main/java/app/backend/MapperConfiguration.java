package app.backend;

import app.backend.appointment.AppointmentMapper;
import app.backend.customer.CustomerMapper;
import app.backend.staff.StaffMapper;
import org.apache.catalina.mapper.Mapper;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperConfiguration {

    public static List<RowMapper<?>> rowMappers() {
        List<RowMapper<?>> rowMappers = new ArrayList<>();
        // ADD ROW MAPPERS HERE

        rowMappers.add(new AppointmentMapper());
        rowMappers.add(new CustomerMapper());
        rowMappers.add(new StaffMapper());

        //....


        return rowMappers;
    }

}
