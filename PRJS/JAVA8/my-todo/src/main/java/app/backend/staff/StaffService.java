package app.backend.staff;

import java.io.Serializable;
import java.util.List;


public class StaffService implements Serializable {

    StaffDao dao = new StaffDao();

    public List<Staff> selectAll() {
        return dao.selectAll();
    }

    public Staff select(String id) {
        return dao.select(id);
    }

    public boolean exists(String id) {
        return dao.exists(id);
    }

    public boolean save(Staff customer) {
        return dao.save(customer);
    }

    public boolean update(Staff customer) {
        return dao.update(customer);
    }

    public boolean delete(Staff customer) {
        return dao.delete(customer);
    }

}

