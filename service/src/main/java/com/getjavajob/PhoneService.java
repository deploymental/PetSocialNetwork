package com.getjavajob;

import com.getjavajob.common.Phone;

import java.sql.SQLException;
import java.util.List;

import static com.getjavajob.ConnectionPool.returnConnection;

public class PhoneService {
    private PhoneDAO phoneDAO;

    public PhoneService(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    public int create(Phone phone) throws SQLException {
        int id = phoneDAO.create(phone);
        returnConnection();
        return id;
    }

    public void edit(Phone phone) throws SQLException {
        phoneDAO.update(phone);
        returnConnection();
    }

    public void remove(Phone phone) throws SQLException {
        phoneDAO.delete(phone.getId());
        returnConnection();
    }

    public List<Phone> getAll() throws SQLException {
        List<Phone> list = phoneDAO.getAll();
        returnConnection();
        return list;
    }

    public List<Phone> getAll(int id) throws SQLException {
        List<Phone> list = phoneDAO.getAll(id);
        returnConnection();
        return list;
    }

    public Phone read(int id) throws SQLException {
        Phone phone = phoneDAO.read(id);
        returnConnection();
        return phone;
    }
}
