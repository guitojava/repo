package app.backend.appointment;

import java.io.Serializable;
import java.util.List;


public class AppointmentService implements Serializable {

    AppointmentDao appointmentDao = new AppointmentDao();

    public List<Appointment> selectAll() {
        return appointmentDao.selectAll();
    }

    public List<Appointment> selectAllForCustomer(String customerId) {
        return appointmentDao.selectAllForCustomer( customerId);
    }

    public Appointment select(String id) {
        return appointmentDao.select(id);
    }

    public boolean exists( String id ) {
        return appointmentDao.exists(id);
    }

    public boolean save( Appointment appointment ) {
        return appointmentDao.save(appointment);
    }

    public boolean delete ( Appointment appointment ) {
        return appointmentDao.delete(appointment);
    }

    public boolean deleteByCustomerId( String deleteByCustomerId ) {
        return appointmentDao.deleteByCustomerId(deleteByCustomerId);
    }

    public boolean unassignByStaffId( String staffId ) {
        return appointmentDao.unassignByStaffId(staffId);
    }


}

